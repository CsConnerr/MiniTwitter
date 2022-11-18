package com;

public class tUser implements Visitor {

  public void accept(Visitable visitable) {
    visitable.visit(this);
  }

}