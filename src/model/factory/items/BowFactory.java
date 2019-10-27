package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.weapons.Bow;

public class BowFactory extends AbstractItemsFactory {

  @Override
  public Bow create(String name, int power, int minRange, int maxRange) {
    return new Bow(name,power,minRange,maxRange);
  }

  @Override
  public Bow createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Bow createPowerfulItem(String name) {
    return create(name,powerfulPower,2,powerfulMaxRange);
  }

  @Override
  public Bow createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
