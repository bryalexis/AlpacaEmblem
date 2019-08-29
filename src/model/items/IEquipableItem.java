package model.items;

import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
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
   * The owner of the item receives a default Magical Attack
   * @param item that sends the magical attack
   */
  void takeInMagicalAttack(IEquipableItem item);

  /**
   * The owner of the item receives a Darkness spell.
   * @param spell that was sent
   */
  void takeInDarknessSpell(Darkness spell);

  /**
   * The owner of the item receives a Spirit spell.
   * @param spell that was sent
   */
  void takeInSpiritSpell(Spirit spell);

  /**
   * The owner of the item receives a Light spell.
   * @param spell that was sent
   */
  void takeInLightSpell(Light spell);

  /**
   * The owner of the item receives a default Physical Attack
   * @param item that sends the physical attack
   */
  void takeInPhysicalAttack(IEquipableItem item);

  /**
   * The owner of the item is attacked with an Axe.
   * @param axe the weapon
   */
  void takeInAxeAttack(Axe axe);

  /**
   * The owner of the item is attacked with a Spear.
   * @param spear the weapon
   */
  void takeInSpearAttack(Spear spear);

  /**
   * The owner of the item is attacked with a Sword.
   * @param sword the weapon
   */
  void takeInSwordAttack(Sword sword);

  /**
   * Updates the maximum range
   * @param value new maximum range
   */
  void setMaxRange(int value);

  /**
   * Updates the minimum range
   * @param value new minimum range
   */
  void setMinRange(int value);


}
