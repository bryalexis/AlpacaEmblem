package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.weapons.Sword;

/**
 * This class creates instances of Swords
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class SwordFactory extends AbstractItemsFactory {

  @Override
  public Sword create(String name, int power, int minRange, int maxRange) {
    return new Sword(name,power,minRange,maxRange);
  }

}
