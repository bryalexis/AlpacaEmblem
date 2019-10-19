package model.factory.items;

import model.factory.IItemsFactory;
import model.items.weapons.Bow;

public class BowFactory implements IItemsFactory {

  @Override
  public Bow create(String name, int power, int minRange, int maxRange) {
    return new Bow(name,power,minRange,maxRange);
  }

  @Override
  public Bow createGenericItem(String name) {
    return create(name,15,2,8);
  }

  @Override
  public Bow createPowerfulItem(String name) {
    return create(name,20,2,6);
  }

  @Override
  public Bow createLongDistanceItem(String name) {
    return create(name,10,2,10);
  }
}
