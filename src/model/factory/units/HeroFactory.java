package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.warriors.Hero;

public class HeroFactory implements IUnitsFactory {
  @Override
  public Hero createUnit(int hp, int movement, Location location) {
    return new Hero(hp,movement,location);
  }

  @Override
  public Hero createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public Hero createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public Hero createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
