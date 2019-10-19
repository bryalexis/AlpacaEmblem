package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.warriors.Archer;


public class ArcherFactory implements IUnitsFactory {
  @Override
  public Archer createUnit(int hp, int movement, Location location) {
    return new Archer(hp,movement,location);
  }

  @Override
  public Archer createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public Archer createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public Archer createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
