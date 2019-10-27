package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.weapons.Axe;

public class AxeFactory extends AbstractItemsFactory {

  @Override
  public Axe create(String name, int power, int minRange, int maxRange) {
    return new Axe(name,power,minRange,maxRange);
  }

  @Override
  public Axe createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Axe createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Axe createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
