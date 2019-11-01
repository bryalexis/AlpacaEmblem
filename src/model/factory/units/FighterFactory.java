package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Fighter;

public class FighterFactory extends AbstractUnitsFactory {

  @Override
  public Fighter createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Fighter(hp,movement,location, owner, items);
  }
}
