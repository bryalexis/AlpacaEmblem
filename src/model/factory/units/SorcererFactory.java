package model.factory.units;

import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.magic.Sorcerer;

public class SorcererFactory implements IUnitsFactory {
  @Override
  public Sorcerer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Sorcerer(hp,movement,location, owner, items);
  }

  @Override
  public Sorcerer createGenericUnit(Location location, Tactician owner) {
    return createUnit(100, 3, location, owner);
  }

  @Override
  public Sorcerer createTankUnit(Location location, Tactician owner) {
    return createUnit(200, 1, location, owner);
  }

  @Override
  public Sorcerer createFastUnit(Location location, Tactician owner) {
    return createUnit(70, 5, location, owner);
  }

}
