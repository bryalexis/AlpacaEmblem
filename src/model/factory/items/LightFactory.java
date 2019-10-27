package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.spellbooks.Light;

public class LightFactory extends AbstractItemsFactory {
  @Override
  public Light create(String name, int power, int minRange, int maxRange) {
    return new Light(name,power,minRange,maxRange);
  }

  @Override
  public Light createGenericItem(String name) {
    return create(name, genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Light createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Light createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
