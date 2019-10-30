package model.items;

import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between attack items.
 *
 * @author Bryan Ortiz P.
 * @since 2.2
 * @version 2.2
 */
public abstract class AbstractAttackItem extends AbstractItem implements IAttackItem{

  /**
   * Constructor for a default attack item.
   *
   * @param name
   *     the name of the weapon
   * @param power
   *     the power of the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public AbstractAttackItem(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public boolean allowedToAttack(IUnit target){
    boolean nullTacticians = target.getOwner() == null || getOwner().getOwner() == null;
    boolean differentTacticians = target.getOwner() != getOwner().getOwner();
    return  nullTacticians || differentTacticians;
  }

  @Override
  public void useOn(IUnit target) {
    if(allowedToAttack(target))
    attack(target);
  }
}
