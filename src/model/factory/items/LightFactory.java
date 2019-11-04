package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.spellbooks.Light;

/**
 * This class creates instances of Light spell books
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class LightFactory extends AbstractItemsFactory {

  @Override
  public Light create(String name, int power, int minRange, int maxRange) {
    return new Light(name,power,minRange,maxRange);
  }

}
