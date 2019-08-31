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
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipStaff(Staff staff) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipLightBook(Light light) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipAxe(Axe axe) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipBow(Bow bow) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipSpear(Spear spear) {
    // SwordMaster can't equip this item.
  }

  @Override
  public void equipSword(Sword sword) {
    if (getItems().contains(sword)){
      setEquippedItem(sword);
    }
  }
  
  @Override
  public void attack(IUnit target) {
    if(isAbleToAttack(target)){
      startCombatWith(target);
      target.getEquippedItem().takeInSwordAttack((Sword) getEquippedItem());
      target.counterAttack(this);
    }
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(isAbleToAttack(aggressor)){
      startCombatWith(aggressor);
      aggressor.getEquippedItem().takeInSwordAttack((Sword) getEquippedItem());
    } else endCombatWith(aggressor);
  }
  
}
