package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.warriors.SwordMaster;

public class SwordMasterFactory implements IUnitsFactory {
  @Override
  public SwordMaster createUnit(int hp, int movement, Location location) {
    return new SwordMaster(hp,movement,location);
  }

  @Override
  public SwordMaster createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public SwordMaster createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public SwordMaster createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
