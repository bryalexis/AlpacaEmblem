package model.tactician.handlers;

import model.tactician.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UnitDieListener implements PropertyChangeListener {
  private Tactician tactician;

  public UnitDieListener(Tactician tactician){
    this.tactician = tactician;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    tactician.getUnits().remove((IUnit) evt.getNewValue());
  }
}
