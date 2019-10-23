package model.units;

import model.items.weapons.Axe;
import model.units.healers.Cleric;
import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import model.units.warriors.Fighter;
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
    archer = new Archer(50, 2, field.getCell(0, 0),null);
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
    archer.equipItem(bow);
    assertNull(archer.getEquippedItem());
    archer.addItem(bow);
    archer.addItem(sword);
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
    archer.equipItem(sword);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){

    archer.addItem(bow);
    archer.equipItem(bow);

    // Bow vs Staff
    Cleric cleric = new Cleric(50, 2, field.getCell(0, 0),null);
    cleric.addItem(staff);
    cleric.equipItem(staff);
    archer.useItemOn(cleric);
    assertEquals(archer.getMaxHitPoints(), archer.getCurrentHitPoints(),0.01); // No counter
    assertFalse(archer.getInCombat());

    // Bow vs No Item
    archer.useItemOn(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-bow.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);

    Fighter fighter = new Fighter(50, 2, field.getCell(2, 0),null);
    Axe axe = new Axe("owo", 10, 0, 1);
    fighter.addItem(axe);

    // Counter out of range.
    archer.useItemOn(fighter);
    assertEquals(fighter.getMaxHitPoints() - 10, fighter.getCurrentHitPoints());
    assertEquals(archer.getMaxHitPoints(), archer.getCurrentHitPoints());

  }

  @Test
  public void testStrongAttack(){
    // Bow vs Magic Item
    Sorcerer target = new Sorcerer(50, 2, field.getCell(1, 1),null);
    target.addItem(spirit);
    target.equipItem(spirit);
    archer.addItem(bow);
    archer.equipItem(bow);
    archer.useItemOn(target);
    double expectedHP = target.getMaxHitPoints()-bow.getPower()*1.5;
    double currentHP = target.getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);
    assertEquals(archer.getMaxHitPoints() - spirit.getPower()*1.5,
           archer.getCurrentHitPoints(),0.01);
  }

  @Override
  public IUnit getEquippedTestUnit() {
    archer.addItem(bow);
    archer.equipItem(bow);
    return archer;
  }

}