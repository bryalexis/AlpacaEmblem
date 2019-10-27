package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Fighter;

public class FighterFactory extends AbstractUnitsFactory {

  @Override
  public Fighter createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Fighter(hp,movement,location, owner, items);
  }

  @Override
  public Fighter createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner);
  }

  @Override
  public Fighter createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner);
  }

  @Override
  public Fighter createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner);
  }

}
