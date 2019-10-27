package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.healers.Cleric;

public class ClericFactory extends AbstractUnitsFactory {
  @Override
  public Cleric createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Cleric(hp,movement,location, owner, items);
  }

  @Override
  public Cleric createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner);
  }

  @Override
  public Cleric createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner);
  }

  @Override
  public Cleric createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner);
  }

}
