package model.factory.units;

import model.factory.IUnitsFactory;
import model.map.Location;
import model.units.carriers.Alpaca;

public class AlpacaFactory implements IUnitsFactory {
  @Override
  public Alpaca createUnit(int hp, int movement, Location location) {
    return new Alpaca(hp,movement,location);
  }

  @Override
  public Alpaca createGenericUnit(Location location) {
    return createUnit(50, 3, location);
  }

  @Override
  public Alpaca createTankUnit(Location location) {
    return createUnit(100, 1, location);
  }

  @Override
  public Alpaca createFastUnit(Location location) {
    return createUnit(30, 5, location);
  }

}
