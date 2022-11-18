package com;

public class tPositive implements Visitor {

  public void accept(Visitable visitable) {
    visitable.visit(this);
  }

}



