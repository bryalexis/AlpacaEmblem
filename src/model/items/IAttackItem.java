package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>attack items</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Bryan Ortiz P
 * @since 2.2
 * @version 2.2
 */
public interface IAttackItem extends IEquipableItem{

  /**
   * The owner of the weapon attacks another unit
   * @param target who will receive the attack
   */
  void attack(IUnit target);

  /**
   * It tells if a the item can attack the target unit
   * If the owner of the unit that owns the item is also the target
   * unit, the attack is refused.
   * @param target unit that will receive the attack
   * @return if the attack can be made.
   */
  boolean allowedToAttack(IUnit target);
}
