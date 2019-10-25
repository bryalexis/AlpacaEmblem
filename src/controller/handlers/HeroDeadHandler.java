package controller.handlers;

import controller.GameController;
import model.tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HeroDeadHandler implements PropertyChangeListener {

  private GameController controller;

  public HeroDeadHandler(GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent heroDead) {
    controller.getTacticians().remove((Tactician) heroDead.getNewValue());
  }
}
