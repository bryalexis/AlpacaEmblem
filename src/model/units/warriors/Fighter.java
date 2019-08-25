package model.units.warriors;

import model.items.weapons.Axe;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.AbstractNonMagic;
import model.units.IUnit;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractNonMagic {

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
      equippedItem = item;
    }
  }

  @Override
  public void attack(IUnit target) {
    target.receiveFighterAttack(this);
  }

  @Override
  public void receiveHeroAttack(Hero hero){
    double damage = -hero.getEquippedItem().getPower() + 20;
    modifyCurrentHitPoints(Math.min(damage, 0));
  }

  @Override
  public void receiveFighterAttack(Fighter fighter){
    modifyCurrentHitPoints(-fighter.getEquippedItem().getPower());
  }

  @Override
  public void receiveSwordMasterAttack(SwordMaster swordMaster){
    modifyCurrentHitPoints(-swordMaster.getEquippedItem().getPower()*1.5);
  }

}
