package model.factory.items;

import model.factory.IItemsFactory;
import model.items.spellbooks.Darkness;

public class DarknessFactory implements IItemsFactory {
  @Override
  public Darkness create(String name, int power, int minRange, int maxRange) {
    return new Darkness(name,power,minRange,maxRange);
  }

  @Override
  public Darkness createGenericItem(String name) {
    return create(name,20,1,5);
  }

  @Override
  public Darkness createPowerfulItem(String name) {
    return create(name,30,1,3);
  }

  @Override
  public Darkness createLongDistanceItem(String name) {
    return create(name,10,1,10);
  }
}
