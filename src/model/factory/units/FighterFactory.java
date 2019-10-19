package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.warriors.Fighter;

public class FighterFactory implements IUnitsFactory {

  @Override
  public Fighter createUnit(int hp, int movement, Location location) {
    return new Fighter(hp,movement,location);
  }

  @Override
  public Fighter createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public Fighter createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public Fighter createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
