package model.items;

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
  public boolean isStrongAgainst(IEquipableItem item){
    return super.isStrongAgainst(item) || item instanceof Spear;
  }

  @Override
  public boolean isWeakAgainst(IEquipableItem item){
    return super.isStrongAgainst(item) || item instanceof Sword;
  }
}
