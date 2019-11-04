package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.spellbooks.Darkness;

/**
 * This class creates instances of Darkness spell books
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class DarknessFactory extends AbstractItemsFactory {
  @Override
  public Darkness create(String name, int power, int minRange, int maxRange) {
    return new Darkness(name,power,minRange,maxRange);
  }
}
