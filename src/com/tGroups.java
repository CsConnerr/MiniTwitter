package com;

public class tGroups implements Visitor {

  public void accept(Visitable visitable) {
    visitable.visit(this);
  }

}