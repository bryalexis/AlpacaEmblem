package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * @param item of the enemy
   * @return if the weapon/book is strong against the enemy weapon/book
   */
  boolean isStrongAgainst(IEquipableItem item);

  /**
   * @param item of the enemy
   * @return if the weapon/book is weak against the enemy weapon/book
   */
  boolean isWeakAgainst(IEquipableItem item);

  /**
   * @param item that belongs to the target unit.
   * @return the amount of damage against that item.
   */
  double getEffectAgainst(IEquipableItem item);
}
