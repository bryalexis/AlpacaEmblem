package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.SwordMaster;

public class SwordMasterFactory extends AbstractUnitsFactory {
  @Override
  public SwordMaster createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new SwordMaster(hp,movement,location,owner,items);
  }

  @Override
  public SwordMaster createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner);
  }

  @Override
  public SwordMaster createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner);
  }

  @Override
  public SwordMaster createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner);
  }

}
