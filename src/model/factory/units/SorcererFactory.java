package model.factory.units;

import model.factory.AbstractUnitsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.units.magic.Sorcerer;

public class SorcererFactory extends AbstractUnitsFactory {
  @Override
  public Sorcerer createUnit(int hp, int movement, Location location, Tactician owner, IEquipableItem... items) {
    return new Sorcerer(hp,movement,location, owner, items);
  }
}
