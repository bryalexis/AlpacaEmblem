package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.healers.Cleric;
import model.units.warriors.Archer;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.addItem(staff);
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
    cleric.addItem(spear);
    cleric.equipItem(spear);
    assertEquals(staff, cleric.getEquippedItem());
  }

  @Override
  public IUnit getEquippedTestUnit() {
    cleric.addItem(staff);
    cleric.equipItem(staff);
    return cleric;
  }

  @Test
  public void counterTest(){
    IUnit unit = getEquippedTestUnit();
    Archer target = new Archer(50, 2, field.getCell(0, 2));
    target.addItem(bow);
    target.equipItem(bow);
    unit.counterAttack(target);
    assertEquals(target.getMaxHitPoints(), target.getCurrentHitPoints(),0.01);
  }
}