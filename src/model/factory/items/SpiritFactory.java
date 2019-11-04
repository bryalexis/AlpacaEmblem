package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.spellbooks.Spirit;

/**
 * This class creates instances of Spirit spell books
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class SpiritFactory extends AbstractItemsFactory {

  @Override
  public Spirit create(String name, int power, int minRange, int maxRange) {
    return new Spirit(name,power,minRange,maxRange);
  }

}
