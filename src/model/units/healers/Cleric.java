package model.units.healers;

import model.items.IEquipableItem;
import model.items.healing.Staff;
import model.map.Location;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit implements IHealer {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric(final int hitPoints, final int movement, final Location location,
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
    if (item instanceof Staff) {
      equippedItem = item;
    }
  }

  @Override
  public void attack(IUnit target){
    endCombatWith(target);
    // A cleric can't attack
  }

  /**
   * Heal another Unit giving HitPoints
   * @param target who will receive the healing
   */
  public void heal(IUnit target){
    target.receiveHealing(this);
  }

}
