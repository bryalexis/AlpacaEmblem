package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.spellbooks.Spirit;

public class SpiritFactory extends AbstractItemsFactory {
  @Override
  public Spirit create(String name, int power, int minRange, int maxRange) {
    return new Spirit(name,power,minRange,maxRange);
  }

  @Override
  public Spirit createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Spirit createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Spirit createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
