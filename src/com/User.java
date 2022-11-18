package com;

import java.util.ArrayList;
import java.util.List;

// Implements the Composite pattern to represent a group of users
// Implements the Observer pattern to notify users of changes to the Subject
public class User extends Subject implements Composite, Observer {

  private int totalTweets = 0;
  private int totalUsers = 0;
  private int positiveTwts = 0;
  private final String ID;
  private final List<User> followers;
  private final List<String> following;
  private final List<String> Feed;
  private final List<String> Tweets;
  private final String[] positiveWords = {"slay", "ate", "amazing", "as you should", "good",
      "perfect"};

  private String tweet;


  public User(String ID) {
    this.ID = ID;
    followers = new ArrayList();
    following = new ArrayList();
    Feed = new ArrayList();
    Tweets = new ArrayList();
    totalUsers = totalUsers++;
  }

  public void following(String user) { //add user to following list
    following.add(user);
  }

  public void tweet(String tweet) {
    totalTweets++;
    Tweets.add(tweet);
    this.tweet = tweet;
    updateObservers(tweet);
    Feed.add(0, ID + ": " + tweet);
    for (String word : positiveWords) {
      if (tweet.toLowerCase().contains(word)) {
        positiveTwts++;
      }
    }


  }

  @Override
  public String getID() {
    return ID;
  }

  @Override
  public void update(Subject subject) {
    if (subject instanceof User) {
      Feed.add("[" + ((User) subject).getID() + "] - " + tweet);
    }
  }

  public List<String> getTweets() {
    return Tweets;
  }

  public int getTotalTweets() {
    return totalTweets;
  }


  public int getTotalPositive() {
    return positiveTwts;
  }

  public List<String> getFeed() {
    return Feed;
  }

  public List<String> getFollowing() {
    return following;
  }

  public List<User> getFollowers() {
    return followers;
  }


  public void updateFeed(String message) {
    Feed.add(message);
  }

  @Override
  public String toString() {
    return ID;
  }

}
