package model.factory.items;

import model.factory.IItemsFactory;
import model.items.spellbooks.Spirit;

public class SpiritFactory implements IItemsFactory {
  @Override
  public Spirit create(String name, int power, int minRange, int maxRange) {
    return new Spirit(name,power,minRange,maxRange);
  }

  @Override
  public Spirit createGenericItem(String name) {
    return create(name,20,1,5);
  }

  @Override
  public Spirit createPowerfulItem(String name) {
    return create(name,30,1,3);
  }

  @Override
  public Spirit createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
