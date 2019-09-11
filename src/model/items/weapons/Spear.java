package model.items.weapons;

import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractWeapon {

  /**
   * Creates a new Spear item
   *
   * @param name
   *     the name of the Spear
   * @param power
   *     the damage of the spear
   * @param minRange
   *     the minimum range of the spear
   * @param maxRange
   *     the maximum range of the spear
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipSpear(this);
    setOwner(unit);
  }

  @Override
  public void takeInAxeAttack(Axe axe){
    getOwner().modifyCurrentHitPoints(-axe.getPower()*1.5);
  }

  @Override
  public void takeInSpearAttack(Spear spear){
    getOwner().modifyCurrentHitPoints(-spear.getPower());
  }

  @Override
  public void takeInSwordAttack(Sword sword){
    double damage = -sword.getPower() + 20;
    getOwner().modifyCurrentHitPoints(Math.min(damage, 0));
  }

  @Override
  public void useOn(IUnit target) {
    target.getEquippedItem().takeInSpearAttack(this);
  }

}
