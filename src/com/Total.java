package com;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Total implements Visitable {

  ArrayList<User> users;

  private double totalTweets = 0;
  private double totalPos = 0;
  private double percentage = 0;

  public Total(ArrayList<User> users) {
    this.users = users;

    for (User u : users) {
      totalTweets = totalTweets + u.getTotalTweets();
      totalPos = totalPos + u.getTotalPositive();
      percentage = (totalPos / totalTweets) * 100;
    }
  }


  @Override
  public void visit(tTweets tTweets) {

  }

  @Override
  public void visit(tUser tUser) {

  }

  @Override
  public void visit(tGroups tGroups) {

  }

  @Override
  public void visit(tPositive tpositive) {
    JOptionPane.showMessageDialog(null,
        percentage + "% of the tweets are positive.",
        "Positive Percentage", JOptionPane.INFORMATION_MESSAGE);

  }


}
