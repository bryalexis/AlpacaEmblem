package model.factory.items;

import model.factory.IItemsFactory;
import model.items.weapons.Sword;

public class SwordFactory implements IItemsFactory {
  @Override
  public Sword create(String name, int power, int minRange, int maxRange) {
    return new Sword(name,power,minRange,maxRange);
  }

  @Override
  public Sword createGenericItem(String name) {
    return create(name,20,1,5);
  }

  @Override
  public Sword createPowerfulItem(String name) {
    return create(name,30,1,3);
  }

  @Override
  public Sword createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
