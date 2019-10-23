package model.items;

import model.items.healing.Staff;
import model.map.Location;
import model.units.healers.Cleric;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for staffs
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class StaffTest extends AbstractTestHealing {

  private Staff staff;
  private Staff wrongStaff;
  private Cleric cleric;

  /**
   * Sets which item is going to be tested
   */
  @Override
  public void setTestItem() {
    expectedName = "Common staff";
    expectedPower = 5;
    expectedMinRange = 1;
    expectedMaxRange = 1;
    staff = new Staff(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongStaff = new Staff("Wrong staff", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 5, new Location(0, 0),null);
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongStaff;
  }

  /**
   * @return the item being tested
   */
  @Override
  public IEquipableItem getTestItem() {
    return staff;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  public void giveHitPointsTest() {
    Staff staff = new Staff("spear", 10, 1,2);
    IUnit unit = getTestUnit();
    unit.modifyCurrentHitPoints(- unit.getMaxHitPoints()+10);

    staff.useOn(unit);
    assertEquals(Math.min(20,unit.getMaxHitPoints()), unit.getCurrentHitPoints());
  }

}
