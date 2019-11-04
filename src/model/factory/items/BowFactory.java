package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.weapons.Bow;

/**
 * This class creates instances of Bows
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class BowFactory extends AbstractItemsFactory {

  @Override
  public Bow create(String name, int power, int minRange, int maxRange) {
    return new Bow(name,power,minRange,maxRange);
  }

}
