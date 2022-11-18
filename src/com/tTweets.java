package com;

public class tTweets implements Visitor {

  public void accept(Visitable visitable) {
    visitable.visit(this);
  }

}
