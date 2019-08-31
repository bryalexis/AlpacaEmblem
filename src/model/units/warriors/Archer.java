package model.units.warriors;

import model.items.healing.Staff;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.IEquipableItem;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This class represents an <i>Archer</i> type unit.
 * <p>
 * This kind of unit <b>can only use bows</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Archer extends AbstractUnit {

  /**
   * Creates a new archer
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param position
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int hitPoints, final int movement, final Location position,
      final IEquipableItem... items) {
    super(hitPoints, movement, position, 3, items);
  }

  @Override
  public void equipStaff(Staff staff) {
    // Archer can't equip this item.
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    // Archer can't equip this item.
  }

  @Override
  public void equipLightBook(Light light) {
    // Archer can't equip this item.
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    // Archer can't equip this item.
  }

  @Override
  public void equipAxe(Axe axe) {
    // Archer can't equip this item.
  }

  @Override
  public void equipBow(Bow bow) {
    if (getItems().contains(bow)){
      setEquippedItem(bow);
    }
  }

  @Override
  public void equipSpear(Spear spear) {
    // Archer can't equip this item.
  }

  @Override
  public void equipSword(Sword sword) {
    // Archer can't equip this item.
  }

  @Override
  public void attack(IUnit target) {
    if(isAbleToAttack(target)){
      startCombatWith(target);
      if(target.hasEquippedItem()){
        target.getEquippedItem().takeInPhysicalAttack(getEquippedItem());
      } else {
        target.modifyCurrentHitPoints(- getEquippedItem().getPower() );
      }
      target.counterAttack(this);
    }
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(isAbleToAttack(aggressor)){
      startCombatWith(aggressor);
      aggressor.getEquippedItem().takeInPhysicalAttack(getEquippedItem());
    } else{
      endCombatWith(aggressor);
    }
  }
}
