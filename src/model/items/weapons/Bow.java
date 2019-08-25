package model.items.weapons;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Bow extends AbstractWeapon {

  /**
   * Creates a new bow.
   * <p>
   * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
   * one.
   *
   * @param name
   *     the name of the bow
   * @param power
   *     the damage power of the bow
   * @param minRange
   *     the minimum range of the bow
   * @param maxRange
   *     the maximum range of the bow
   */
  public Bow(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
    this.minRange = Math.max(minRange, 2);
    this.maxRange = Math.max(maxRange, this.minRange);
  }

  @Override
  public void takeInAxeAttack(Axe axe){
    takeInPhysicalAttack(axe);
  }

  @Override
  public void takeInSpearAttack(Spear spear){
    takeInPhysicalAttack(spear);
  }

  @Override
  public void takeInSwordAttack(Sword sword){
    takeInPhysicalAttack(sword);
  }
}
