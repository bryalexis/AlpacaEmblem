package model.units.warriors;

import model.items.IEquipableItem;
import model.items.weapons.Spear;
import model.map.Location;
import model.units.AbstractNonMagic;
import model.units.IUnit;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends AbstractNonMagic {

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

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if (item instanceof Spear) {
      equippedItem = item;
    }
  }

  @Override
  public void attack(IUnit target) {
    target.receiveHeroAttack(this);
  }

  @Override
  public void receiveHeroAttack(Hero hero){
    modifyCurrentHitPoints(-hero.getEquippedItem().getPower());
  }

  @Override
  public void receiveFighterAttack(Fighter fighter) {
    modifyCurrentHitPoints(-fighter.getEquippedItem().getPower()*1.5);
  }

  @Override
  public void receiveSwordMasterAttack(SwordMaster swordMaster) {
    double damage = -swordMaster.getEquippedItem().getPower() + 20;
    modifyCurrentHitPoints(Math.min(damage, 0));
  }



}
