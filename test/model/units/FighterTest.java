package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.nullitem.NullItem;
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
    fighter = new Fighter(50, 2, field.getCell(0, 0),null);
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
    assertEquals(NullItem.class, getTestUnit().getEquippedItem().getClass());
    fighter.equipItem(axe);
    assertEquals(NullItem.class, getTestUnit().getEquippedItem().getClass());
    fighter.addItem(axe);
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
    fighter.addItem(sword);
    fighter.equipItem(sword);
    assertEquals(axe, fighter.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    fighter.addItem(axe);
    fighter.equipItem(axe);
    fighter.useItemOn(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-axe.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);
  }

  @Test
  public void testStrongAttack(){
    fighter.addItem(axe);
    fighter.equipItem(axe);
    double current;
    double expected;

    // Axe vs Magic Item
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 1),null);
    sorcerer.addItem(spirit);
    sorcerer.equipItem(spirit);

    fighter.useItemOn(sorcerer);
    double expectedSorcererHP = sorcerer.getMaxHitPoints()-axe.getPower()*1.5;
    double currentSorcererHP = sorcerer.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP,0.01);
    expected = fighter.getMaxHitPoints() - spirit.getPower()*1.5;
    current = fighter.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter

    // Axe vs Spear
    Hero hero = new Hero (50, 2, field.getCell(0, 2),null);
    hero.addItem(spear);
    hero.equipItem(spear);

    fighter.useItemOn(hero);
    double expectedHeroHP = hero.getMaxHitPoints() - axe.getPower()*1.5;
    double currentHeroHP = hero.getCurrentHitPoints();
    assertEquals(expectedHeroHP,currentHeroHP,0.01);
    expected = current + Math.min(-spear.getPower()+20,0);
    current = fighter.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter

    // Overkilling (? assert
    hero.modifyCurrentHitPoints(-currentHeroHP+1);
    fighter.useItemOn(hero);
    assertEquals(0,hero.getCurrentHitPoints());
  }

  @Override
  public IUnit getEquippedTestUnit() {
    fighter.addItem(axe);
    fighter.equipItem(axe);
    return fighter;
  }

}