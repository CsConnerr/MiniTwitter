package com;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TotalTweets implements Visitable {

  ArrayList<User> users;


  private double totalTweets = 0;


  public TotalTweets(ArrayList<User> users) {
    this.users = users;

    for (User u : users) {
      totalTweets = totalTweets + u.getTotalTweets();

    }
  }


  @Override
  public void visit(tTweets tTweets) {
    JOptionPane.showMessageDialog(null, totalTweets + " Tweets Total",
        "Show Tweet Total", JOptionPane.INFORMATION_MESSAGE);
// open JOptionPane then stop do not open anything else

  }

  @Override
  public void visit(tUser tUser) {

  }

  @Override
  public void visit(tGroups tGroups) {

  }

  @Override
  public void visit(tPositive tPositive) {

  }


}
