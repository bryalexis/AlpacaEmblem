package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.factory.IItemsFactory;
import model.items.weapons.Sword;

public class SwordFactory extends AbstractItemsFactory {
  @Override
  public Sword create(String name, int power, int minRange, int maxRange) {
    return new Sword(name,power,minRange,maxRange);
  }

  @Override
  public Sword createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public Sword createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public Sword createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }

}
