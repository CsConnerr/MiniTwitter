package com;

import java.util.ArrayList;
import java.util.List;

// Implements the Composite pattern to represent a group of users
public class Group implements Composite {

  private final String ID;
  private final List<User> users = new ArrayList();
  private int totalGroups = 0;

  public Group(String ID) {
    this.ID = ID;
    totalGroups++;
  }

  @Override
  public String getID() {
    return ID;
  }

  public int getTotalGroups() {
    return totalGroups;
  }

  @Override
  public String toString() {
    return ID;
  }
}
