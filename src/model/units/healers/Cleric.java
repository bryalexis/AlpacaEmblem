package model.units.healers;

import model.items.IEquipableItem;
import model.items.healing.Staff;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;
import model.tactician.Tactician;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Cleric extends AbstractUnit {

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Cleric(final int hitPoints, final int movement, final Location location, Tactician owner,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, owner, items);
  }

  @Override
  public void equipStaff(Staff staff) {
    if (getItems().contains(staff)){
      setEquippedItem(staff);
    }
  }

  /**
   * Clerics heal other units
   * @param target to be healed
   */
  @Override
  public void useItemOn(IUnit target){
    startCombatWith(target);
    if(canUseItemOn(target)) {
      getEquippedItem().useOn(target);
    }
    endCombatWith(target);
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    // Cleric can't attack
    endCombatWith(aggressor);
  }

}
