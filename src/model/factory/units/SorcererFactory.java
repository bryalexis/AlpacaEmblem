package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.magic.Sorcerer;

public class SorcererFactory extends AbstractUnitsFactory {
  @Override
  public Sorcerer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Sorcerer(hp,movement,location, owner, items);
  }

  @Override
  public Sorcerer createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner);
  }

  @Override
  public Sorcerer createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner);
  }

  @Override
  public Sorcerer createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner);
  }

}
