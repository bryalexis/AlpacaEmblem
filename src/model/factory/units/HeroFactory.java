package model.factory.units;

import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Hero;

public class HeroFactory implements IUnitsFactory {
  @Override
  public Hero createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Hero(hp,movement,location, owner, items);
  }

  @Override
  public Hero createGenericUnit(Location location, Tactician owner) {
    return createUnit(100, 3, location, owner);
  }

  @Override
  public Hero createTankUnit(Location location, Tactician owner) {
    return createUnit(200, 1, location, owner);
  }

  @Override
  public Hero createFastUnit(Location location, Tactician owner) {
    return createUnit(70, 5, location, owner);
  }

}
