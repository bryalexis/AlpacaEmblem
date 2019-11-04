package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.magic.Sorcerer;

/**
 * This class creates instances of Sorcerers
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class SorcererFactory extends AbstractUnitsFactory {
  @Override
  public Sorcerer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Sorcerer(hp,movement,location, owner, items);
  }
}
