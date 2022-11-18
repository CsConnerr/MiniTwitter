package com;

// Implements the Visitor pattern
// The Visitor pattern is used when you have to perform the same action on many objects of different types of classes
public interface Visitor {

  void accept(Visitable visitable);
}
