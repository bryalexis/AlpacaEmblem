package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.healers.Cleric;

public class ClericFactory implements IUnitsFactory {
  @Override
  public Cleric createUnit(int hp, int movement, Location location) {
    return new Cleric(hp,movement,location);
  }

  @Override
  public Cleric createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public Cleric createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public Cleric createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
