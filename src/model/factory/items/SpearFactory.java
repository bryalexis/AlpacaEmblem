package model.factory.items;

import model.factory.IItemsFactory;
import model.items.weapons.Spear;

public class SpearFactory implements IItemsFactory {
  @Override
  public Spear create(String name, int power, int minRange, int maxRange) {
    return new Spear(name,power,minRange,maxRange);
  }

  @Override
  public Spear createGenericItem(String name) {
    return create(name,20,1,5);
  }

  @Override
  public Spear createPowerfulItem(String name) {
    return create(name,30,1,3);
  }

  @Override
  public Spear createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
