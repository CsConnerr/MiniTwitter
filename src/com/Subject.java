package com;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

  private final List<User> observers = new ArrayList<User>();

  public void attach(User user) {
    observers.add(user);
  }
  public List<User> getObservers() {
    return observers;
  }

  // Update all observers of a change to the Subject (tweet)
  public void updateObservers(String tweet) {
    for (Observer observer : observers) {
      observer.update(this);
    }
  }
}
