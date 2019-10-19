package model.factory.items;

import model.factory.IItemsFactory;
import model.items.healing.Staff;

public class StaffFactory implements IItemsFactory {

  @Override
  public Staff create(String name, int power, int minRange, int maxRange) {
    return new Staff(name,power,minRange,maxRange);
  }

  @Override
  public Staff createGenericItem(String name) {
    return create(name,20,1,5);
  }

  @Override
  public Staff createPowerfulItem(String name) {
    return create(name,30,1,3);
  }

  @Override
  public Staff createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
