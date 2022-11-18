package com;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TotalUser implements Visitable {


  ArrayList<User> users;


  public TotalUser(ArrayList<User> users) {
    this.users = users;


  }


  @Override
  public void visit(tTweets tTweets) {

  }

  @Override
  public void visit(tUser tUser) {
    JOptionPane.showMessageDialog(null, "There are a total of " + users.size() + " users.",
        "Show User Total", JOptionPane.INFORMATION_MESSAGE);

  }

  @Override
  public void visit(tGroups tGroups) {

  }

  @Override
  public void visit(tPositive tPositive) {

  }

}
