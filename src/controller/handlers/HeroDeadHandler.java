package controller.handlers;

import controller.GameController;
import model.tactician.Tactician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class handles events when a Hero Die
 * @author Bryan Ortiz P
 * @since 2.5
 * @version 2.5
 */
public class HeroDeadHandler implements PropertyChangeListener {

  private GameController controller;

  /**
   * Creates a new HeroDead Handler
   * @param controller of the game
   */
  public HeroDeadHandler(GameController controller) {
    this.controller = controller;
  }

  /**
   * Deletes the Tactician from the game
   * @param heroDead gives a reference of the tactician
   */
  @Override
  public void propertyChange(PropertyChangeEvent heroDead) {
    controller.getTacticians().remove((Tactician) heroDead.getNewValue());
    controller.endTurn();
  }
}
