package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.weapons.Axe;

/**
 * This class creates instances of Axes
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class AxeFactory extends AbstractItemsFactory {

  @Override
  public Axe create(String name, int power, int minRange, int maxRange) {
    return new Axe(name,power,minRange,maxRange);
  }

}
