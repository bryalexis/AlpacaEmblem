package model.items.weapons;
/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
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
  public void takeInAxeAttack(int power){
    double damage = -power + 20;
    getOwner().modifyCurrentHitPoints(Math.min(damage, 0));
  }

  @Override
  public void takeInSpearAttack(int power){
    getOwner().modifyCurrentHitPoints(-power*1.5);
  }

  @Override
  public void takeInSwordAttack(int power){
    getOwner().modifyCurrentHitPoints(-power);
  }

}
