package com;

public class Count implements Visitor {

  Visitor[] counter;


  public Count() {
    counter = new Visitor[]{new tGroups(), new tUser(), new tTweets(), new tPositive()};
  }

  public void accept(Visitable visitable) {
    for (int i = 0; i < counter.length; i++) {
      counter[i].accept(visitable);
    }
  }

}
