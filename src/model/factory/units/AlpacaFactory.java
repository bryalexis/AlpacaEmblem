package model.factory.units;

import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.carriers.Alpaca;

public class AlpacaFactory implements IUnitsFactory {
  @Override
  public Alpaca createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Alpaca(hp,movement,location, owner, items);
  }

  @Override
  public Alpaca createGenericUnit(Location location, Tactician owner) {
    return createUnit(100, 3, location, owner);
  }

  @Override
  public Alpaca createTankUnit(Location location, Tactician owner) {
    return createUnit(200, 1, location, owner);
  }

  @Override
  public Alpaca createFastUnit(Location location, Tactician owner) {
    return createUnit(70, 5, location, owner);
  }

}
