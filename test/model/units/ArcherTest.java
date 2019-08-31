package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import org.junit.jupiter.api.Test;

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
    archer.addItem(bow);
    bow.equipTo(archer);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    archer.addItem(bow);
    bow.equipTo(archer);
    archer.attack(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-bow.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP);
  }

  @Test
  public void testStrongAttack(){
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


}