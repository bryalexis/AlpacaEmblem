package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Archer;

/**
 * This class creates instances of Archers
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class ArcherFactory extends AbstractUnitsFactory {
  @Override
  public Archer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Archer(hp,movement,location, owner, items);
  }
}
