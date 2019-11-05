package model.items.weapons;

import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 * @version 2.5
 */
public class Sword extends AbstractWeapon {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the Sword
   * @param power
   *     the base damage of the sword
   * @param minRange
   *     the minimum range of the sword
   * @param maxRange
   *     the maximum range of the sword
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipSword(this);
  }

  @Override
  public void takeInAxeAttack(Axe axe){
    takeInWeakAttack(axe.getPower());
  }

  @Override
  public void takeInSpearAttack(Spear spear){
    takeInStrongAttack(spear.getPower());
  }

  @Override
  public void takeInSwordAttack(Sword sword){
    takeInNormalAttack(sword.getPower());
  }

  @Override
  public void attack(IUnit target) {
    target.getEquippedItem().takeInSwordAttack(this);
  }

}
