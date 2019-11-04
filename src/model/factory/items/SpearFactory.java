package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.weapons.Spear;

/**
 * This class creates instances of Spears
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class SpearFactory extends AbstractItemsFactory {

  @Override
  public Spear create(String name, int power, int minRange, int maxRange) {
    return new Spear(name,power,minRange,maxRange);
  }

}
