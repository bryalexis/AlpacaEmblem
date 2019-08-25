package model.items;

import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
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
   * @param distance at the objective
   * @return if the item can attack/heal the objective in terms of distance
   */
  boolean isReachable(double distance);

  /**
   * Sets the owner of the item
   * @param owner of the item
   */
  void setOwner(IUnit owner);

  /**
   * Throws a spell against a target
   * @param target who will receive the spell
   */
  void throwSpell(IUnit target);

  void takeInDarknessSpell(Darkness spell);

  void takeInSpiritSpell(Spirit spell);

  void takeInLightSpell(Light spell);

  void takeInPhysicalAttack(int power);

  void takeInAxeAttack(int power);

  void takeInSpearAttack(int power);

  void takeInSwordAttack(int power);
}
