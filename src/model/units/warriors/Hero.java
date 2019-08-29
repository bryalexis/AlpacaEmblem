package model.units.warriors;

import model.items.IEquipableItem;
import model.items.weapons.Spear;
import model.map.Location;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Hero(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if (item instanceof Spear) {
      setEquippedItem(item);
    }
  }

  @Override
  public void attack(IUnit target) {
    if(isAbleToAttack(target)){
      startCombatWith(target);
      target.getEquippedItem().takeInSpearAttack((Spear) getEquippedItem());
      target.counterAttack(this);
    } else endCombatWith(target);
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(isAbleToAttack(aggressor)){
      startCombatWith(aggressor);
      aggressor.getEquippedItem().takeInSpearAttack((Spear) getEquippedItem());
    } else endCombatWith(aggressor);
  }

}
