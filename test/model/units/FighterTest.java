package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.magic.Sorcerer;
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
    assertEquals(expectedHP,currentHP);
  }

  @Test
  public void testStrongAttack(){
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 1));
    sorcerer.addItem(spirit);
    spirit.equipTo(sorcerer);

    Hero hero = new Hero (50, 2, field.getCell(0, 2));
    hero.addItem(spear);
    spear.equipTo(hero);

    fighter.addItem(axe);
    axe.equipTo(fighter);

    fighter.attack(sorcerer);
    double expectedSorcererHP = sorcerer.getMaxHitPoints()-bow.getPower()*1.5;
    double currentSorcererHP = sorcerer.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP);
    assertEquals(fighter.getMaxHitPoints() - spirit.getPower()*1.5,
            fighter.getCurrentHitPoints()); // Check counter

    //fighter.attack(hero);

  }
}