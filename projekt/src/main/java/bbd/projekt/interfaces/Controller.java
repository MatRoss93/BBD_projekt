package bbd.projekt.interfaces;


import bbd.projekt.controllers.StartWindowController;

public class Controller extends Object {

  protected StartWindowController startWindowController;

  public StartWindowController getStartWindowController() {
    return startWindowController;
  }

  public void setStartWindowController(StartWindowController startWindowController) {
    this.startWindowController = startWindowController;
  }  
}
