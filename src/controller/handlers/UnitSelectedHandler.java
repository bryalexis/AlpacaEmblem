package controller.handlers;

import controller.GameController;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UnitSelectedHandler implements PropertyChangeListener {

  private GameController controller;

  public UnitSelectedHandler(GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent selectedUnitEVT) {
    controller.selectUnit((IUnit) selectedUnitEVT.getNewValue());
  }
}