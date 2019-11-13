package model.items.healing;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.IHealingItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between healing items.
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
public abstract class AbstractHealingItem extends AbstractItem implements IHealingItem {

  /**
   * Constructor for a default item without any special behaviour.
   *
   * @param name
   *     the name of the item
   * @param power
   *     the power of the item (this could be the amount of damage or healing the item does)
   * @param minRange
   *     the minimum range of the item
   * @param maxRange
   *     the maximum range of the item
   */
  AbstractHealingItem(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void takeInMagicalAttack(IEquipableItem item) {
    takeInStrongAttack(item.getPower());
  }

  @Override
  public void takeInDarknessSpell(Darkness spell) {
    takeInMagicalAttack(spell);
  }

  @Override
  public void takeInSpiritSpell(Spirit spell) {
    takeInMagicalAttack(spell);
  }

  @Override
  public void takeInLightSpell(Light spell) {
    takeInMagicalAttack(spell);
  }

  @Override
  public void takeInPhysicalAttack(IEquipableItem item) {
    takeInNormalAttack(item.getPower());
  }

  @Override
  public void takeInAxeAttack(Axe axe) {
    takeInPhysicalAttack(axe);
  }

  @Override
  public void takeInSpearAttack(Spear spear) {
    takeInPhysicalAttack(spear);
  }

  @Override
  public void takeInSwordAttack(Sword sword) {
    takeInPhysicalAttack(sword);
  }

  @Override
  public boolean allowedToHeal(IUnit target){
    if(getOwner() == null) return true;
    boolean nullTacticians = target.getOwner() == null || getOwner().getOwner() == null;
    boolean sameTacticians = target.getOwner() == getOwner().getOwner();
    return  nullTacticians || sameTacticians;
  }

  @Override
  public void useOn(IUnit unit){
    if(allowedToHeal(unit)) heal(unit);
  }
}
