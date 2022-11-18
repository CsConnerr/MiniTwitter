package com;

// The Observer interface is the interface that all observers must implement.
// The Observer pattern uses this interface to notify observers of changes to the Subject.
public interface Observer {

  // update is called when the Subject changes
  void update(Subject subject);
}
