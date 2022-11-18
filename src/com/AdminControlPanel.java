package com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class AdminControlPanel extends JFrame {

  private static AdminControlPanel instance = null;
  private final List<Group> groups;
  private final ArrayList<User> users;
  private final ArrayList<String> userNames;
  private final ArrayList<String> groupNames;
  private final HashMap<String, UserView> userViews = new HashMap<String, UserView>();
  Count C = new Count();
  private JTree tree;
  private JPanel contentPane;
  private DefaultMutableTreeNode root;
  private DefaultTreeModel treeModel;
  private String sUser;


  private AdminControlPanel() {
    users = new ArrayList();
    groups = new ArrayList();
    userNames = new ArrayList();
    groupNames = new ArrayList();
    groups.add(new Group("Root"));
    adminPanel();

  }


  public static AdminControlPanel getInstance() {
    if (instance == null) {
      instance = new AdminControlPanel();
    }
    return instance;
  }

  public void adminPanel() {
    root = new DefaultMutableTreeNode("Root");
    treeModel = new DefaultTreeModel(root);
    tree = new JTree(treeModel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 535, 350);
    setTitle("Twitter");
    contentPane = new JPanel();
    contentPane.setBackground(new Color(29, 161, 242));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);

    JScrollPane tView = new JScrollPane(tree);
    tView.setBounds(12, 13, 200, 300);
    contentPane.add(tView);

    // Let's a user add a user to the tree
    JTextArea UserTA = new JTextArea("Add Username");
    UserTA.setBounds(220, 13, 150, 55);
    UserTA.setRows(5);
    UserTA.setColumns(5);
    UserTA.setLineWrap(true);
    contentPane.add(UserTA);

    // Allows a user to add a user by clicking a button and adding the user to the tree
    JButton AddUserB = new JButton("Add User");
    AddUserB.setBounds(375, 13, 155, 55);
    AddUserB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (UserTA.getText().equals("")) {

          JOptionPane.showMessageDialog(null, "Enter Username", "ERROR",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          if (!userNames.contains(UserTA.getText())) {
            if (tree.getSelectionPath() == null) {
              User user = new User(UserTA.getText());
              DefaultMutableTreeNode unode = new DefaultMutableTreeNode(user);
              users.add(user);
              userNames.add(UserTA.getText());
              userViews.put(user.getID(), new UserView(user, userNames, users, userViews));
              root.add(unode);
            } else {
              DefaultMutableTreeNode Node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
              if (Node == root) {
                User user = new User(UserTA.getText());
                DefaultMutableTreeNode unode = new DefaultMutableTreeNode(user);
                users.add(user);
                userNames.add(UserTA.getText());
                userViews.put(user.getID(), new UserView(user, userNames, users, userViews));
                root.add(unode);
              }
              if (Node.getUserObject() instanceof Group) {
                User user = new User(UserTA.getText());
                DefaultMutableTreeNode unode = new DefaultMutableTreeNode(user);
                users.add(user);
                userNames.add(UserTA.getText());
                userViews.put(user.getID(), new UserView(user, userNames, users, userViews));
                Node.add(unode);
              }
              if (Node.getUserObject() instanceof User) {
                User user = new User(UserTA.getText());
                DefaultMutableTreeNode unode = new DefaultMutableTreeNode(user);
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) Node.getParent();
                users.add(user);
                userNames.add(UserTA.getText());
                userViews.put(user.getID(), new UserView(user, userNames, users, userViews));
                parent.add(unode);
              }
            }
          } else {
            JOptionPane.showMessageDialog(null, "This Username Already Exists!", "ERROR",
                JOptionPane.INFORMATION_MESSAGE);
          }
        }
        treeModel.reload(root);
        Nodes(tree, 0, tree.getRowCount());
        UserTA.setText("");

      }
    });
    contentPane.add(AddUserB);

    // Allows a user add a group to the tree
    JTextArea groupText = new JTextArea("Add Group");
    groupText.setBounds(220, 75, 150, 55);
    groupText.setRows(5);
    groupText.setColumns(5);
    groupText.setLineWrap(true);
    contentPane.add(groupText);

    // Allows a user to add a group by clicking a button and adding the group to the tree
    JButton AddGroupB = new JButton("Add Group");
    AddGroupB.setBounds(375, 75, 155, 55);
    AddGroupB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (groupText.getText().equals("")) {
          JOptionPane.showMessageDialog(null, "Enter Group Name", "ERROR",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          if (!groupNames.contains(groupText.getText())) {
            if (tree.getSelectionPath() == null) {
              Group group = new Group(groupText.getText());
              DefaultMutableTreeNode gnode = new DefaultMutableTreeNode(group);
              groups.add(group);
              groupNames.add(groupText.getText());
              root.add(gnode);
            } else {
              DefaultMutableTreeNode Node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
              if (Node == root) {
                Group group = new Group(groupText.getText());
                DefaultMutableTreeNode gnode = new DefaultMutableTreeNode(group);
                groups.add(group);
                groupNames.add(groupText.getText());
                root.add(gnode);
              } else if (groupNames.contains(Node.getUserObject())) {
                Group group = new Group(groupText.getText());
                DefaultMutableTreeNode gnode = new DefaultMutableTreeNode(group);
                groups.add(group);
                groupNames.add(groupText.getText());
                Node.add(gnode);
              } else if (userNames.contains(Node.getUserObject().toString())) {
                Group group = new Group(groupText.getText());
                DefaultMutableTreeNode gnode = new DefaultMutableTreeNode(group);
                groups.add(group);
                groupNames.add(groupText.getText());
                Node.add(gnode);
              }
            }
          } else {
            JOptionPane.showMessageDialog(null, "Group Name already exists", "ERROR",
                JOptionPane.INFORMATION_MESSAGE);
          }
        }
        treeModel.reload(root);
        Nodes(tree, 0, tree.getRowCount());
        groupText.setText("");
      }
    });
    contentPane.add(AddGroupB);

    // Allows a user to open a user view by clicking a button
    JButton OpenUserB = new JButton("Open User View");
    OpenUserB.setBounds(220, 135, 305, 50);
    OpenUserB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (tree.getSelectionPath() == null) {
          JOptionPane.showMessageDialog(null, "Select a User", "ERROR",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          DefaultMutableTreeNode Node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
          if (Node.getUserObject() instanceof Group || Node.getUserObject().toString()
              .equals("Root")) {
            JOptionPane.showMessageDialog(null, "Please select a user to view.", "User View Error",
                JOptionPane.INFORMATION_MESSAGE);
          }
          if (Node.getUserObject() instanceof User) {
            sUser = Node.getUserObject().toString();
            User user = new User(sUser);
            UserView userView = userViews.get(sUser);
            userView.setVisible(true);
            userView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          }
        }
      }
    });
    contentPane.add(OpenUserB);

    // Tells the user how many users are in the tree
    JButton totalUsers = new JButton("User Total");
    totalUsers.setBounds(220, 185, 155, 55);
    totalUsers.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        TotalUser tUsers = new TotalUser(users);
        C.accept(tUsers);
      }
    });
    contentPane.add(totalUsers);

    // Tells the user how many groups there are in the tree
    JButton totalGroups = new JButton("Group Total");
    totalGroups.setBounds(375, 185, 155, 55);
    totalGroups.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        TotalGroup tGroup = new TotalGroup(groups);
        C.accept(tGroup);

      }
    });
    contentPane.add(totalGroups);

    // Tells the user the total amount of tweets in the tree
    JButton totalTwts = new JButton("Total Tweets");
    totalTwts.setBounds(220, 250, 155, 55);
    totalTwts.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        TotalTweets twts = new TotalTweets(users);
        C.accept(twts);
      }
    });
    contentPane.add(totalTwts);

    JButton totalPos = new JButton("Total Positive Tweets");
    totalPos.setBounds(375, 250, 155, 55);
    totalPos.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Total pos = new Total(users);
        C.accept(pos);

      }
    });
    contentPane.add(totalPos);
  }

  private void Nodes(JTree tree, int index, int row) {
    for (int i = index; i < row; ++i) {
      tree.expandRow(i);
    }
  }
}
