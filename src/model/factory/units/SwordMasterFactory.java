package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.SwordMaster;

public class SwordMasterFactory extends AbstractUnitsFactory {
  @Override
  public SwordMaster createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new SwordMaster(hp,movement,location,owner,items);
  }
}
