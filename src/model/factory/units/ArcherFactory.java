package model.factory.units;

import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Archer;


public class ArcherFactory implements IUnitsFactory {
  @Override
  public Archer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Archer(hp,movement,location, owner, items);
  }

  @Override
  public Archer createGenericUnit(Location location, Tactician owner) {
    return createUnit(100, 3, location, owner );
  }

  @Override
  public Archer createTankUnit(Location location, Tactician owner) {
    return createUnit(200, 1, location, owner );
  }

  @Override
  public Archer createFastUnit(Location location, Tactician owner) {
    return createUnit(70, 5, location, owner );
  }

}
