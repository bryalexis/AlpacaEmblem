package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.spellbooks.Darkness;

public class DarknessFactory extends AbstractItemsFactory {
  @Override
  public Darkness create(String name, int power, int minRange, int maxRange) {
    return new Darkness(name,power,minRange,maxRange);
  }

  @Override
  public Darkness createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Darkness createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Darkness createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
