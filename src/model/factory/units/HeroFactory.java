package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Hero;

public class HeroFactory extends AbstractUnitsFactory {
  @Override
  public Hero createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Hero(hp,movement,location, owner, items);
  }

  @Override
  public Hero createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner);
  }

  @Override
  public Hero createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner);
  }

  @Override
  public Hero createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner);
  }

}
