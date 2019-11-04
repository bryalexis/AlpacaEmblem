package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Fighter;

/**
 * This class creates instances of Fighters
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class FighterFactory extends AbstractUnitsFactory {

  @Override
  public Fighter createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Fighter(hp,movement,location, owner, items);
  }
}
