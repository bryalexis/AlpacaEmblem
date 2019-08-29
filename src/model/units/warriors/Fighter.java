package model.units.warriors;

import model.items.weapons.Axe;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location,
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
    if (item instanceof Axe) {
      setEquippedItem(item);
    }
  }

  @Override
  public void attack(IUnit target) {
    if(isAbleToAttack(target)){
      startCombatWith(target);
      target.getEquippedItem().takeInAxeAttack((Axe) getEquippedItem());
      target.counterAttack(this);
    }
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(isAbleToAttack(aggressor)){
      startCombatWith(aggressor);
      aggressor.getEquippedItem().takeInAxeAttack((Axe) getEquippedItem());
    } else endCombatWith(aggressor);
  }

}
