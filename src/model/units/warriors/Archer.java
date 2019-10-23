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
import model.tactician.Tactician;
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
   * @param owner
   *     the owner of this unit
   * @param items
   *     the items carried by this unit
   */
  public Archer(final int hitPoints, final int movement, final Location position, Tactician owner,
      final IEquipableItem... items) {
    super(hitPoints, movement, position, 3, owner, items);
  }

  @Override
  public void equipBow(Bow bow) {
    if (getItems().contains(bow)){
      setEquippedItem(bow);
    }
  }

  @Override
  public void useItemOn(IUnit target) {
    if(canUseItemOn(target)){
      startCombatWith(target);
      if(target.hasEquippedItem()){
        getEquippedItem().useOn(target);
      } else {
        target.modifyCurrentHitPoints(- getEquippedItem().getPower() );
      }
      target.counterAttack(this);
    }
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(canUseItemOn(aggressor)){
      getEquippedItem().useOn(aggressor);
    }
    endCombatWith(aggressor);
  }
}
