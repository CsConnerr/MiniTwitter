package com;

import java.util.List;
import javax.swing.JOptionPane;

public class TotalGroup implements Visitable {

  List<Group> groups;
  private double tgrp = 0;

  public TotalGroup(List<Group> groups) {
    this.groups = groups;

    for (Group g : groups) {

      tgrp = groups.size() - 1;
    }
  }


  public void visit(tTweets tTweets) {

  }


  public void visit(tUser tUser) {

  }

  @Override
  public void visit(tGroups tGroups) {
    JOptionPane.showMessageDialog(null,
        "There are " + tgrp + " groups", "Show Group Total",
        JOptionPane.INFORMATION_MESSAGE);


  }


  public void visit(tPositive tPositive) {

  }


}
