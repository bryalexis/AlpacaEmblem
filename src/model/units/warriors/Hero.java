package model.units.warriors;

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

  @Override
  public void equipStaff(Staff staff) {
    // Hero can't equip this item.
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    // Hero can't equip this item.
  }

  @Override
  public void equipLightBook(Light light) {
    // Hero can't equip this item.
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    // Hero can't equip this item.
  }

  @Override
  public void equipAxe(Axe axe) {
    // Hero can't equip this item.
  }

  @Override
  public void equipBow(Bow bow) {
    // Hero can't equip this item.
  }

  @Override
  public void equipSpear(Spear spear) {
    if (getItems().contains(spear)){
      setEquippedItem(spear);
    }
  }

  @Override
  public void equipSword(Sword sword) {
    // Hero can't equip this item.
  }

  @Override
  public void attack(IUnit target) {
    if(isAbleToAttack(target)){
      startCombatWith(target);
      Spear spear = (Spear) getEquippedItem();
      if(target.hasEquippedItem()){
        target.getEquippedItem().takeInSpearAttack(spear);
      } else {
        target.modifyCurrentHitPoints(- getEquippedItem().getPower() );
      }
      target.counterAttack(this);
    } else endCombatWith(target);
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(isAbleToAttack(aggressor)){
      aggressor.getEquippedItem().takeInSpearAttack((Spear) getEquippedItem());
    }
    endCombatWith(aggressor);
  }

}
