package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.carriers.Alpaca;

public class AlpacaFactory extends AbstractUnitsFactory {
  @Override
  public Alpaca createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    if (location.getUnit()==null) return new Alpaca(hp,movement,location, owner, items);
    return null;
  }

  @Override
  public Alpaca createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner);
  }

  @Override
  public Alpaca createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner);
  }

  @Override
  public Alpaca createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner);
  }

}
