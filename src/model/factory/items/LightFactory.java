package model.factory.items;

import model.factory.IItemsFactory;
import model.items.spellbooks.Light;

public class LightFactory implements IItemsFactory {
  @Override
  public Light create(String name, int power, int minRange, int maxRange) {
    return new Light(name,power,minRange,maxRange);
  }

  @Override
  public Light createGenericItem(String name) {
    return create(name,30,1,5);
  }

  @Override
  public Light createPowerfulItem(String name) {
    return create(name,50,1,3);
  }

  @Override
  public Light createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
