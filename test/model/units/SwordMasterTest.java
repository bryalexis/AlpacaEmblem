package model.units;

import model.items.nullitem.NullItem;
import model.units.magic.Sorcerer;
import model.units.warriors.Fighter;
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
    swordMaster = new SwordMaster(50, 2, field.getCell(0, 0),null);
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
    assertEquals(NullItem.class, getTestUnit().getEquippedItem().getClass());
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    assertEquals(sword, swordMaster.getEquippedItem());
    swordMaster.addItem(bow);
    swordMaster.equipItem(bow);
    assertEquals(sword, swordMaster.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    swordMaster.useItemOn(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-sword.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);
  }

  @Test
  public void testStrongAttack(){
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    double current;
    double expected;

    // Sword vs Magic Item
    Sorcerer sorcerer = new Sorcerer(50, 2, field.getCell(1, 1),null);
    sorcerer.addItem(light);
    sorcerer.equipItem(light);

    swordMaster.useItemOn(sorcerer);
    double expectedSorcererHP = sorcerer.getMaxHitPoints()-sword.getPower()*1.5;
    double currentSorcererHP = sorcerer.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP,0.01);
    expected = swordMaster.getMaxHitPoints() - light.getPower()*1.5;
    current = swordMaster.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter

    // Sword vs Axe
    Fighter fighter = new Fighter(50, 2, field.getCell(0, 2),null);
    fighter.addItem(axe);
    fighter.equipItem(axe);

    swordMaster.useItemOn(fighter);
    double expectedHeroHP = fighter.getMaxHitPoints() - sword.getPower()*1.5;
    double currentHeroHP = fighter.getCurrentHitPoints();
    assertEquals(expectedHeroHP,currentHeroHP,0.01);
    expected = current + Math.min(-axe.getPower()+20,0);
    current = swordMaster.getCurrentHitPoints();
    assertEquals(expected, current,0.01); // Check counter
  }

  @Override
  public IUnit getEquippedTestUnit() {
    swordMaster.addItem(sword);
    swordMaster.equipItem(sword);
    return swordMaster;
  }

}