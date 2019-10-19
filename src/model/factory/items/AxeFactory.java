package model.factory.items;

import model.factory.IItemsFactory;
import model.items.weapons.Axe;

public class AxeFactory implements IItemsFactory {
  @Override
  public Axe create(String name, int power, int minRange, int maxRange) {
    return new Axe(name,power,minRange,maxRange);
  }

  @Override
  public Axe createGenericItem(String name) {
    return create(name,20,1,5);
  }

  @Override
  public Axe createPowerfulItem(String name) {
    return create(name,30,1,3);
  }

  @Override
  public Axe createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
