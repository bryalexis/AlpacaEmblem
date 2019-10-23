package model.factory.units;

import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.healers.Cleric;

public class ClericFactory implements IUnitsFactory {
  @Override
  public Cleric createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Cleric(hp,movement,location, owner, items);
  }

  @Override
  public Cleric createGenericUnit(Location location, Tactician owner) {
    return createUnit(50, 3, location, owner);
  }

  @Override
  public Cleric createTankUnit(Location location, Tactician owner) {
    return createUnit(100, 1, location, owner);
  }

  @Override
  public Cleric createFastUnit(Location location, Tactician owner) {
    return createUnit(30, 5, location, owner);
  }

}
