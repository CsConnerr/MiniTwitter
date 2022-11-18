package com;

public interface Visitable {


  void visit(tTweets tTweets);

  void visit(tUser tUser);

  void visit(tGroups tGroups);

  void visit(tPositive tPositive);


}