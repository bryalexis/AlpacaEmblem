package model.units.magic;

import model.items.IEquipableItem;
import model.items.healing.Staff;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.ISpellsBook;
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
 * This class represents a sorcerer type unit. A sorcerer can only use spells books.
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public class Sorcerer extends AbstractUnit {

  /**
   * Creates a new Sorcerer.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   */
  public Sorcerer(final int hitPoints, final int movement, final Location location,
                  IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  @Override
  public void equipStaff(Staff staff) {
    // Sorcerer can't equip this item
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    if (getItems().contains(darkness)){
      setEquippedItem(darkness);
    }
  }

  @Override
  public void equipLightBook(Light light) {
    if (getItems().contains(light)){
      setEquippedItem(light);
    }
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    if (getItems().contains(spirit)){
      setEquippedItem(spirit);
    }
  }

  @Override
  public void equipAxe(Axe axe) {
    // Sorcerer can't equip this item
  }

  @Override
  public void equipBow(Bow bow) {
    // Sorcerer can't equip this item
  }

  @Override
  public void equipSpear(Spear spear) {
    // Sorcerer can't equip this item
  }

  @Override
  public void equipSword(Sword sword) {
    // Sorcerer can't equip this item
  }


  @Override
  public void attack(IUnit target) {
    if(isAbleToAttack(target)){
      startCombatWith(target);
      ISpellsBook spellsBook = (ISpellsBook) getEquippedItem();
      if(target.hasEquippedItem()){
        spellsBook.throwSpell(target);
      } else {
        target.modifyCurrentHitPoints(- getEquippedItem().getPower() );
      }
      target.counterAttack(this);
    }
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    if(isAbleToAttack(aggressor)){
      ISpellsBook item = (ISpellsBook) getEquippedItem();
      item.throwSpell(aggressor);
    }
    endCombatWith(aggressor);
  }

}
