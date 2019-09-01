package model.units;

import model.units.healers.Cleric;
import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    bow.equipTo(archer);
    assertNull(archer.getEquippedItem());
    archer.addItem(bow);
    archer.addItem(sword);
    bow.equipTo(archer);
    assertEquals(bow, archer.getEquippedItem());
    sword.equipTo(archer);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){

    archer.addItem(bow);
    bow.equipTo(archer);

    // Bow vs Staff
    Cleric cleric = new Cleric(50, 2, field.getCell(0, 0));
    cleric.addItem(staff);
    staff.equipTo(cleric);
    archer.attack(cleric);
    assertEquals(archer.getMaxHitPoints(), archer.getCurrentHitPoints()); // No counter
    assertFalse(archer.getInCombat());

    // Bow vs No Item
    archer.attack(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-bow.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP);

  }

  @Test
  public void testStrongAttack(){
    // Bow vs Magic Item
    Sorcerer target = new Sorcerer(50, 2, field.getCell(1, 1));
    target.addItem(spirit);
    spirit.equipTo(target);
    archer.addItem(bow);
    bow.equipTo(archer);
    archer.attack(target);
    double expectedHP = target.getMaxHitPoints()-bow.getPower()*1.5;
    double currentHP = target.getCurrentHitPoints();
    assertEquals(expectedHP,currentHP);
    assertEquals(archer.getMaxHitPoints() - spirit.getPower()*1.5,
           archer.getCurrentHitPoints() );
  }

  @Override
  public IUnit getEquippedTestUnit() {
    archer.addItem(bow);
    bow.equipTo(archer);
    return archer;
  }
}