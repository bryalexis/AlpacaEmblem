package model.builder.units;

import model.builder.IUnitsFactory;
import model.map.Location;
import model.units.IUnit;

public class HeroFactory implements IUnitsFactory {
  @Override
  public IUnit createGenericUnit(Location location) {
    return null;
  }

  @Override
  public IUnit createTankUnit(Location location) {
    return null;
  }

  @Override
  public IUnit createFastUnit(Location location) {
    return null;
  }
}
