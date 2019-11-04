package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.carriers.Alpaca;

/**
 * This class creates instances of Alpacas
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class AlpacaFactory extends AbstractUnitsFactory {
  @Override
  public Alpaca createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Alpaca(hp,movement,location, owner, items);
  }
}
