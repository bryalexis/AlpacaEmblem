package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.SwordMaster;

/**
 * This class creates instances of SwordMasters
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class SwordMasterFactory extends AbstractUnitsFactory {
  @Override
  public SwordMaster createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new SwordMaster(hp,movement,location,owner,items);
  }
}
