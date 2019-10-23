package model.units;

import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bryan Ortiz P
 */
public class SorcererTest extends AbstractTestUnit {

  private Sorcerer sorcerer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    sorcerer = new Sorcerer(50, 2, field.getCell(0, 0),null);
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return sorcerer;
  }

  @Test
  @Override
  public void equipDarknessBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(darkness);
    sorcerer.equipItem(darkness);
    assertEquals(darkness, sorcerer.getEquippedItem());
    sorcerer.addItem(bow);
    sorcerer.equipItem(bow);
    assertEquals(darkness, sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipLightBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(light);
    sorcerer.equipItem(light);
    assertEquals(light, sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipSpiritBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(spirit);
    sorcerer.equipItem(spirit);
    assertEquals(spirit, sorcerer.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    sorcerer.addItem(spirit);
    sorcerer.equipItem(spirit);
    sorcerer.useItemOn(getTargetAlpaca());
    double expectedHP = getTargetAlpaca().getMaxHitPoints()-spirit.getPower();
    double currentHP = getTargetAlpaca().getCurrentHitPoints();
    assertEquals(expectedHP,currentHP,0.01);
  }

  @Test
  public void testStrongAttack(){
    sorcerer.addItem(darkness);
    sorcerer.equipItem(darkness);

    Sorcerer target = new Sorcerer(50, 2, field.getCell(1, 1),null);
    target.addItem(spirit);
    target.equipItem(spirit);

    sorcerer.useItemOn(target);
    double expectedSorcererHP = target.getMaxHitPoints()-darkness.getPower()*1.5;
    double currentSorcererHP = target.getCurrentHitPoints();
    assertEquals(expectedSorcererHP,currentSorcererHP,0.01);


    Archer archer = new Archer(50, 2, field.getCell(0, 2),null);
    archer.addItem(bow);
    archer.equipItem(bow);

    sorcerer.useItemOn(archer);
    double expectedArcherHP = archer.getMaxHitPoints()-darkness.getPower()*1.5;
    double currentArcherHP = archer.getCurrentHitPoints();
    assertEquals(expectedArcherHP, currentArcherHP,0.01);
  }

  @Override
  public IUnit getEquippedTestUnit() {
    sorcerer.addItem(light);
    sorcerer.equipItem(light);
    return sorcerer;
  }

}