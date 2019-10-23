package model.factory.units;

import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Fighter;

public class FighterFactory implements IUnitsFactory {

  @Override
  public Fighter createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Fighter(hp,movement,location, owner, items);
  }

  @Override
  public Fighter createGenericUnit(Location location, Tactician owner) {
    return createUnit(50, 3, location, owner);
  }

  @Override
  public Fighter createTankUnit(Location location, Tactician owner) {
    return createUnit(100, 1, location, owner);
  }

  @Override
  public Fighter createFastUnit(Location location, Tactician owner) {
    return createUnit(30, 5, location, owner);
  }

}
