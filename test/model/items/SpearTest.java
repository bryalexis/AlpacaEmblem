package model.items;

import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;
import model.units.warriors.Hero;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for spears
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SpearTest extends AbstractTestWeapon {

  private Spear javelin;
  private Spear wrongSpear;
  private Hero hero;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Javelin";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 3;
    javelin = new Spear(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpear = new Spear("Wrong spear", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 5, new Location(0, 0),null);
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpear;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return javelin;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Test
  public void takeInAxeAttackTest() {
    Axe axe = new Axe("axe", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInAxeAttack(axe);
    assertEquals(Math.max(unit.getMaxHitPoints() - 15,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInSpearAttackTest() {
    Spear spear = new Spear("spear", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInSpearAttack(spear);
    assertEquals(Math.max(unit.getMaxHitPoints() - 10,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInSwordAttackTest() {
    Sword sword = new Sword("sword", 40, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInSwordAttack(sword);
    assertEquals(Math.max(unit.getMaxHitPoints() - (40-20),0), unit.getCurrentHitPoints());
  }
}
