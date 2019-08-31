package model.units.carriers;

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
 * This class represents an <i>Alpaca</i> type unit.
 * <p>
 * This are a special kind of unit that can carry an unlimited amount of items but can't use any of
 * them.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Alpaca extends AbstractUnit {

  /**
   * Creates a new Alpaca.
   *
   * @param hitPoints
   *     the amount of damage this unit can receive
   * @param movement
   *     number of cells the unit can move
   * @param location
   *     current position of the unit
   */
  public Alpaca(final int hitPoints, final int movement, final Location location,
      final IEquipableItem... items) {
    super(hitPoints, movement, location, Integer.MAX_VALUE, items);
  }

  @Override
  public void equipStaff(Staff staff) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipLightBook(Light light) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipAxe(Axe axe) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipBow(Bow bow) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipSpear(Spear spear) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void equipSword(Sword sword) {
    // Method body intentionally left empty
    // Alpaca can't equip any item
  }

  @Override
  public void attack(IUnit target) {
    // Alpaca can't attack
  }

  @Override
  public void counterAttack(IUnit aggressor) {
    endCombatWith(aggressor);
    // Alpaca can't attack
  }

}