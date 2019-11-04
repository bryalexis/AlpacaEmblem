package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Hero;

/**
 * This class creates instances of Heroes
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class HeroFactory extends AbstractUnitsFactory {
  @Override
  public Hero createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Hero(hp,movement,location, owner, items);
  }
}
