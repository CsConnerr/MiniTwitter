package com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class UserView extends JFrame {

  private final User user;
  private final ArrayList<User> users;
  private final HashMap<String, UserView> userViews;
  private final ArrayList<String> userIDs;
  private JPanel contentPane;
  private JList followingList;
  private JList FeedList;
  private DefaultListModel<String> followingModel;
  private DefaultListModel<String> feedModel;

  private long lastUpdated = 0;


  public UserView(User user, ArrayList<String> userIDs, ArrayList<User> users,
      HashMap<String, UserView> userViews) {
    this.userIDs = userIDs;
    this.user = user;
    this.users = users;
    this.userViews = userViews;
    this.setTitle(user.getID() + "'s Profile");
    userPanel();
  }

  public void userPanel() {
    followingList = new JList(user.getFollowers().toArray());
    FeedList = new JList(user.getFeed().toArray());

    followingModel = new DefaultListModel<String>();
    followingModel.addElement("Following: ");
    feedModel = new DefaultListModel<String>();
    feedModel.addElement("Feed: ");

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 420, 481);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(29, 161, 242));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);

    JTextArea userID = new JTextArea();
    userID.setBounds(33, 24, 253, 83);
    contentPane.add(userID);

    JScrollPane followingScroll = new JScrollPane(followingList);
    followingScroll.setBounds(33, 121, 362, 113);
    contentPane.add(followingScroll);

    JButton followB = new JButton("Follow");
    followB.setBounds(298, 24, 97, 84);
    contentPane.add(followB);
    followB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (!userIDs.contains(userID.getText())) {
          JOptionPane.showMessageDialog(null, "User does not exist");
          return;
        }
        if (user.getFollowing().contains(userID.getText())) {
          JOptionPane.showMessageDialog(null, "Already following user");
        }
        if (user.getID().equals(userID.getText())) {
          JOptionPane.showMessageDialog(null, "Cannot follow yourself");
        } else {
          followingModel.addElement(userID.getText());
          followingList.setModel(followingModel);
          user.following(userID.getText());
          for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(userID.getText())) {
              users.get(i).attach(user);
            }
          }
        }
        userID.setText("");
      }
    });

    JTextArea sendTwt = new JTextArea();
    sendTwt.setBounds(33, 244, 253, 83);
    contentPane.add(sendTwt);

    JScrollPane FeedScroll = new JScrollPane(FeedList);
    FeedScroll.setBounds(33, 336, 362, 113);
    contentPane.add(FeedScroll);

    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
    Date date = new Date(user.getCreationTime());
    String CreationTime = formatter.format(date);

    JLabel Creation = new JLabel("Creation Time: " + CreationTime);
    Creation.setBounds(33, 0, 362, 16);
    contentPane.add(Creation);

    JLabel updatel = new JLabel("Last Updated: " + user.getLastUpdated());
    updatel.setBounds(200, 0, 362, 16);
    contentPane.add(updatel);

    JButton twtB = new JButton("Tweet");
    twtB.setBounds(298, 244, 97, 84);
    contentPane.add(twtB);
    setContentPane(contentPane);
    twtB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Tweets can only be 140 characters long just like Twitter
        if (sendTwt.getText().length() > 140) {
          JOptionPane.showMessageDialog(null, "Tweet is too long");
        }
        if (sendTwt.getText().length() == 0) {
          JOptionPane.showMessageDialog(null, "Tweet is empty");
        } else {

          user.tweet(sendTwt.getText());

          long time = System.currentTimeMillis();
          lastUpdated = time;
          user.setLastUpdated(time);

          feedModel.insertElementAt(user.getFeed().get(0), 1);
          FeedList.setModel(feedModel);

          // Uses the observer pattern to update the feed of all followers
          List<User> followers = user.getObservers();
          String twt = user.getFeed().get(0);
          for (int i = 0; i < followers.size(); i++) {
            User user = followers.get(i);
            UserView userView = userViews.get(user.getID());
            user.updateFeed(twt);

            userView.feedModel.insertElementAt(twt, 1);
            userView.FeedList.setModel(userView.feedModel);
            userView.revalidate();
            userView.repaint();
          }
          sendTwt.setText("");

          revalidate();
          repaint();

          Date date = new Date(user.getLastUpdated());
          String Updatetime = formatter.format(date);

          updatel.setText("Last Updated: " + Updatetime);


        }
      }
    });
  }
}

