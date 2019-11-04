package controller.handlers;

import controller.GameController;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class handles events when a unit is selected
 * @author Bryan Ortiz P
 * @since 2.5
 * @version 2.5
 */
public class UnitSelectedHandler implements PropertyChangeListener {

  private GameController controller;

  /**
   * Creates a new unitSelected handler.
   * @param controller of the game.
   */
  public UnitSelectedHandler(GameController controller) {
    this.controller = controller;
  }

  /**
   * Makes the controller sets the same unit as selected.
   * @param selectedUnitEVT gives the unit selected.
   */
  @Override
  public void propertyChange(PropertyChangeEvent selectedUnitEVT) {
    controller.selectUnit((IUnit) selectedUnitEVT.getNewValue());
  }
}