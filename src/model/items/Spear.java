package model.items;

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
  public boolean isStrongAgainst(IEquipableItem item){
    return super.isStrongAgainst(item) || item instanceof Sword;
  }

  @Override
  public boolean isWeakAgainst(IEquipableItem item){
    return item instanceof Axe;
  }
}
