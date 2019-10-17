package model.items.weapons;

import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 * @version 2.2
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipAxe(this);
    setOwner(unit);
  }

  @Override
  public void takeInAxeAttack(Axe axe){
    takeInNormalAttack(axe.getPower());
  }

  @Override
  public void takeInSpearAttack(Spear spear){
    takeInWeakAttack(spear.getPower());
  }

  @Override
  public void takeInSwordAttack(Sword sword){
    takeInStrongAttack(sword.getPower());
  }

  @Override
  public void useOn(IUnit target) {
    target.getEquippedItem().takeInAxeAttack(this);
  }

}
