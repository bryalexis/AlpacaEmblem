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
import model.tactician.Tactician;
import model.units.AbstractUnit;
import model.units.IUnit;

import java.beans.PropertyChangeListener;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 * @version 2.5
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
  public Hero(final int hitPoints, final int movement, final Location location, Tactician owner,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, owner, items);
  }

  @Override
  public void equipSpear(Spear spear) {
    if (getItems().contains(spear)){
      setEquippedItem(spear);
    }
  }

  @Override
  public void useItemOn(IUnit target) {
    if(canUseItemOn(target)){
      startCombatWith(target);
      getEquippedItem().useOn(target);
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

  @Override
  public void addHeroDeadListener(PropertyChangeListener heroDiePCL){
    getIsDeadPCS().addPropertyChangeListener(heroDiePCL);
  }

}
