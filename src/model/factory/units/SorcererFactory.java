package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.magic.Sorcerer;

public class SorcererFactory implements IUnitsFactory {
  @Override
  public Sorcerer createUnit(int hp, int movement, Location location) {
    return new Sorcerer(hp,movement,location);
  }

  @Override
  public Sorcerer createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public Sorcerer createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public Sorcerer createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
