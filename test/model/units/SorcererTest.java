package model.units;

import model.units.magic.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
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
    darkness.equipTo(sorcerer);
    assertEquals(darkness, sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipLightBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(light);
    light.equipTo(sorcerer);
    assertEquals(light, sorcerer.getEquippedItem());
  }

  @Test
  @Override
  public void equipSpiritBookTest() {
    assertNull(sorcerer.getEquippedItem());
    sorcerer.addItem(spirit);
    spirit.equipTo(sorcerer);
    assertEquals(spirit, sorcerer.getEquippedItem());
  }

  @Test
  public void testNormalAttack(){
    Sorcerer enemy = new Sorcerer(50, 2, field.getCell(1, 1));
    sorcerer.addItem(spirit);
    spirit.equipTo(sorcerer);
    sorcerer.attack(enemy);
    double expectedHP = enemy.getMaxHitPoints()-spirit.getPower();
    double currentHP = enemy.getCurrentHitPoints();
    assertEquals(expectedHP,currentHP);
  }
}