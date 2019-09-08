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

  @Override
  public void equipStaff(Staff staff) {
    if (getItems().contains(staff)){
      setEquippedItem(staff);
    }
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    // Cleric can't equip this item
  }

  @Override
  public void equipLightBook(Light light) {
    // Cleric can't equip this item
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    // Cleric can't equip this item
  }

  @Override
  public void equipAxe(Axe axe) {
    // Cleric can't equip this item
  }

  @Override
  public void equipBow(Bow bow) {
    // Cleric can't equip this item
  }

  @Override
  public void equipSpear(Spear spear) {
    // Cleric can't equip this item
  }

  @Override
  public void equipSword(Sword sword) {
    // Cleric can't equip this item
  }

  @Override
  public void heal(IUnit target){
    startCombatWith(target);
    if(isAbleToAttack(target)) {
      ((Staff) getEquippedItem()).giveHitPoints(target);
    }
    endCombatWith(target);
  }

  @Override
  public void attack(IUnit target){
    // A cleric can't attack
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    // Cleric can't attack
    endCombatWith(aggressor);
  }

}
