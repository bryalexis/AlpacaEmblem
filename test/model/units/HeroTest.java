package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import model.units.warriors.Hero;
import model.units.warriors.SwordMaster;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.addItem(spear);
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    hero.addItem(spear);
    hero.equipItem(spear);
    hero.attack(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-spear.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);
  }

  @Test
  public void testStrongAttack(){
    hero.addItem(spear);
    hero.equipItem(spear);
    double current;
    double expected;

    // Spear vs Magic Item
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 1));
    sorcerer.addItem(darkness);
    sorcerer.equipItem(darkness);

    hero.attack(sorcerer);
    double expectedSorcererHP = sorcerer.getMaxHitPoints()-spear.getPower()*1.5;
    double currentSorcererHP = sorcerer.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP,0.01);
    expected = hero.getMaxHitPoints() - darkness.getPower()*1.5;
    current = hero.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter

    // Spear vs Sword
    SwordMaster swordMaster = new SwordMaster (50, 2, field.getCell(0, 2));
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);

    hero.attack(swordMaster);
    double expectedSwordMasterHP = swordMaster.getMaxHitPoints() - spear.getPower()*1.5;
    double currentSwordMasterHP = swordMaster.getCurrentHitPoints();
    assertEquals(expectedSwordMasterHP,currentSwordMasterHP, 0.01);
    expected = current + Math.min(-sword.getPower()+20,0);
    current = hero.getCurrentHitPoints();
    assertEquals(expected, current, 0.01); // Check counter
  }

  @Override
  public IUnit getEquippedTestUnit() {
    hero.addItem(spear);
    hero.equipItem(spear);
    return hero;
  }

}