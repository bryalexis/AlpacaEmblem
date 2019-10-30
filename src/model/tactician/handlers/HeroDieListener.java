package model.tactician.handlers;

import model.tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeroDieListener implements PropertyChangeListener {
  private Tactician tactician;

  public HeroDieListener(Tactician tactician){
    this.tactician = tactician;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    tactician.fireHeroDeath();
  }
}