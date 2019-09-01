package model.units;

import model.units.magic.Sorcerer;
import model.units.warriors.Fighter;
import model.units.warriors.Hero;
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

  @Test
  public void testStrongAttack(){
    swordMaster.addItem(sword);
    sword.equipTo(swordMaster);
    double current;
    double expected;

    // Sword vs Magic Item
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 1));
    sorcerer.addItem(light);
    light.equipTo(sorcerer);

    swordMaster.attack(sorcerer);
    double expectedSorcererHP = sorcerer.getMaxHitPoints()-sword.getPower()*1.5;
    double currentSorcererHP = sorcerer.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP);
    expected = swordMaster.getMaxHitPoints() - light.getPower()*1.5;
    current = swordMaster.getCurrentHitPoints();
    assertEquals(expected, current); // Check counter

    // Sword vs Axe
    Fighter fighter = new Fighter(50, 2, field.getCell(0, 2));
    fighter.addItem(axe);
    axe.equipTo(fighter);

    swordMaster.attack(fighter);
    double expectedHeroHP = fighter.getMaxHitPoints() - sword.getPower()*1.5;
    double currentHeroHP = fighter.getCurrentHitPoints();
    assertEquals(expectedHeroHP,currentHeroHP);
    expected = current + Math.min(-axe.getPower()+20,0);
    current = swordMaster.getCurrentHitPoints();
    assertEquals(expected, current); // Check counter
  }

  @Override
  public IUnit getEquippedTestUnit() {
    swordMaster.addItem(sword);
    sword.equipTo(swordMaster);
    return swordMaster;
  }
}