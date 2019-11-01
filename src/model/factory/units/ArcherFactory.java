package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.units.warriors.Archer;


public class ArcherFactory extends AbstractUnitsFactory {
  @Override
  public Archer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Archer(hp,movement,location, owner, items);
  }
}
