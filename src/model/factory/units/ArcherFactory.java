package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Archer;


public class ArcherFactory extends AbstractUnitsFactory {
  @Override
  public Archer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    if (location.getUnit()==null) return new Archer(hp,movement,location, owner, items);
    return null;
  }

  @Override
  public Archer createGenericUnit(Location location, Tactician owner) {
    return createUnit(genericHP, genericMovement, location, owner );
  }

  @Override
  public Archer createTankUnit(Location location, Tactician owner) {
    return createUnit(tankHP, tankMovement, location, owner );
  }

  @Override
  public Archer createFastUnit(Location location, Tactician owner) {
    return createUnit(fastHP, fastMovement, location, owner );
  }

}
