package model.factory;

import model.items.IEquipableItem;

/**
 * This interface represents the items factory.
 * The signature of all the common methods that a items factory can execute by itself are defined here.
 * @author Bryan Ortiz P
 * @since 2.2
 * @version 2.2
 */
public interface IItemsFactory {

  /**
   * Creates a new equippable Item
   * @param name of he item
   * @param power of the item
   * @param maxRange of the item
   * @param minRange of the item
   * @return the item
   */
  IEquipableItem create(String name, int power, int minRange, int maxRange);

  /**
   * Creates a balanced item (medium power and medium range)
   * @param name of the item
   * @return the item
   */
  IEquipableItem createGenericItem(String name);

  /**
   * Creates an item with high power and short range
   * @param name of the item
   * @return the item
   */
  IEquipableItem createPowerfulItem(String name);

  /**
   * Creates an item with low power and high range
   * @param name of the item
   * @return the item
   */
  IEquipableItem createLongDistanceItem(String name);
}
