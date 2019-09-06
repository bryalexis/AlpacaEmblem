package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import model.units.warriors.Fighter;
import model.units.warriors.Hero;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.addItem(axe);
    axe.equipTo(fighter);
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    fighter.addItem(axe);
    axe.equipTo(fighter);
    fighter.attack(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-axe.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);
  }

  @Test
  public void testStrongAttack(){
    fighter.addItem(axe);
    axe.equipTo(fighter);
    double current;
    double expected;

    // Axe vs Magic Item
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 1));
    sorcerer.addItem(spirit);
    spirit.equipTo(sorcerer);

    fighter.attack(sorcerer);
    double expectedSorcererHP = sorcerer.getMaxHitPoints()-axe.getPower()*1.5;
    double currentSorcererHP = sorcerer.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP,0.01);
    expected = fighter.getMaxHitPoints() - spirit.getPower()*1.5;
    current = fighter.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter

    // Axe vs Spear
    Hero hero = new Hero (50, 2, field.getCell(0, 2));
    hero.addItem(spear);
    spear.equipTo(hero);

    fighter.attack(hero);
    double expectedHeroHP = hero.getMaxHitPoints() - axe.getPower()*1.5;
    double currentHeroHP = hero.getCurrentHitPoints();
    assertEquals(expectedHeroHP,currentHeroHP,0.01);
    expected = current + Math.min(-spear.getPower()+20,0);
    current = fighter.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter
  }

  @Override
  public IUnit getEquippedTestUnit() {
    fighter.addItem(axe);
    axe.equipTo(fighter);
    return fighter;
  }

}