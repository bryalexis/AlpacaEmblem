package model.items.spellbooks;

import model.units.IUnit;

/**
 * This class represents a <i>Light</i> book item.
 * <p>
 * Light is a spell book of light type.
 * Light spells are strong against darkness and weak against spirit.
 *
 * @author Bryan Ortiz P
 * @since 1.1
 * @version 2.2
 */
public class Light extends AbstractBook {

  /**
   * Creates a new Light book item.
   *
   * @param name
   *     the name of the Light Book
   * @param power
   *     the base damage of the Light Book
   * @param minRange
   *     the minimum range of the spells
   * @param maxRange
   *     the maximum range of the spells
   */
  public Light(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  @Override
  public void equipTo(IUnit unit) {
    unit.equipLightBook(this);
    setOwner(unit);
  }

  @Override
  public void useOn(IUnit target) {
    target.getEquippedItem().takeInLightSpell(this);
  }

  @Override
  public void takeInDarknessSpell(Darkness spell){
    takeInWeakAttack(spell.getPower());
  }

  @Override
  public void takeInSpiritSpell(Spirit spell){
    takeInStrongAttack(spell.getPower());
  }

  @Override
  public void takeInLightSpell(Light spell){
    takeInNormalAttack(spell.getPower());
  }
}
