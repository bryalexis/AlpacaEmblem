package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.weapons.Spear;

public class SpearFactory extends AbstractItemsFactory {
  @Override
  public Spear create(String name, int power, int minRange, int maxRange) {
    return new Spear(name,power,minRange,maxRange);
  }

  @Override
  public Spear createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Spear createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Spear createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
