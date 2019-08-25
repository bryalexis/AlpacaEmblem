package model.units.warriors;

import model.items.IEquipableItem;
import model.items.weapons.Sword;
import model.map.Location;
import model.units.AbstractNonMagic;
import model.units.IUnit;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractNonMagic {

  public SwordMaster(final int hitPoints, final int movement, final Location location,
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
    if (item instanceof Sword) {
      equippedItem = item;
    }
  }

  @Override
  public void attack(IUnit target) {
    target.receiveSwordMasterAttack(this);
  }

  @Override
  public void receiveHeroAttack(Hero hero) {
    modifyCurrentHitPoints(-hero.getEquippedItem().getPower()*1.5);
  }

  @Override
  public void receiveFighterAttack(Fighter fighter) {
    double damage = -fighter.getEquippedItem().getPower() + 20;
    modifyCurrentHitPoints(Math.min(damage, 0));
  }

  @Override
  public void receiveSwordMasterAttack(SwordMaster swordMaster) {
    modifyCurrentHitPoints(-swordMaster.getEquippedItem().getPower());
  }
}
