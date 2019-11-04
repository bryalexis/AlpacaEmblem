package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.units.healers.Cleric;

/**
 * This class creates instances of Clerics
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class ClericFactory extends AbstractUnitsFactory {
  @Override
  public Cleric createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Cleric(hp,movement,location, owner, items);
  }
}
