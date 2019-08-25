package model.items.weapons;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
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
  public void takeInAxeAttack(int power){
    getOwner().modifyCurrentHitPoints(-power);
  }

  @Override
  public void takeInSpearAttack(int power){
    double damage = -power + 20;
    getOwner().modifyCurrentHitPoints(Math.min(damage, 0));
  }

  @Override
  public void takeInSwordAttack(int power){
    getOwner().modifyCurrentHitPoints(-power*1.5);
  }

}
