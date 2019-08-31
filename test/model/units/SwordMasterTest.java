package model.units;

import model.units.warriors.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

  private SwordMaster swordMaster;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return swordMaster;
  }

  @Test
  @Override
  public void equipSwordTest() {
    assertNull(swordMaster.getEquippedItem());
    swordMaster.addItem(sword);
    sword.equipTo(swordMaster);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    swordMaster.addItem(sword);
    sword.equipTo(swordMaster);
    swordMaster.attack(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-sword.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP);
  }
}