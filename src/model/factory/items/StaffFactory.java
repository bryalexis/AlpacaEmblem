package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.healing.Staff;

public class StaffFactory extends AbstractItemsFactory {

  @Override
  public Staff create(String name, int power, int minRange, int maxRange) {
    return new Staff(name,power,minRange,maxRange);
  }

  @Override
  public Staff createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Staff createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Staff createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
