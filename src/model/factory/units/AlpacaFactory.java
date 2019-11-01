package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.units.carriers.Alpaca;

public class AlpacaFactory extends AbstractUnitsFactory {
  @Override
  public Alpaca createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Alpaca(hp,movement,location, owner, items);
  }
}
