package model.tactician.handlers;

import model.tactician.Tactician;
import model.units.IUnit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class handles events when a Unit die
 * @author Bryan Ortiz P
 * @since 2.5
 * @version 2.5
 */
public class UnitDieListener implements PropertyChangeListener {
  private Tactician tactician;

  /**
   * Creates a new Unit Die Listener
   * @param tactician owner of the unit
   */
  public UnitDieListener(Tactician tactician){
    this.tactician = tactician;
  }

  /**
   * Deletes the unit from the Tactician's units list
   * @param evt unit's death
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    tactician.getUnits().remove((IUnit) evt.getNewValue());
  }
}
