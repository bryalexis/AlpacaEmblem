package model.items;

import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.map.Location;
import model.units.magic.Sorcerer;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test set for Darkness books
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
class DarknessTest extends AbstractTestBook {

  private Darkness darknessBook;
  private Darkness wrongDarknessBook;
  private Sorcerer sorcerer;

  @Override
  public void setTestItem() {
    expectedName = "Common darkness book";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    darknessBook = new Darkness(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongDarknessBook = new Darkness("Wrong darkness book", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    sorcerer = new Sorcerer(50, 5, new Location(0, 0),null);
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongDarknessBook;
  }

  @Override
  public IEquipableItem getTestItem() {
    return darknessBook;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return sorcerer;
  }

  @Test
  public void takeInDarknessSpellTest(){
    Darkness darkness = new Darkness("darkness book", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInDarknessSpell(darkness);
    assertEquals(Math.max(unit.getMaxHitPoints() - 10,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInSpiritSpellTest(){
    Spirit spirit = new Spirit("spirit book", 40, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    assertEquals(unit, item.getOwner());
    item.takeInSpiritSpell(spirit);
    assertEquals(Math.max(unit.getMaxHitPoints() - (40-20),0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInLightSpellTest(){
    Light light = new Light("light book", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInLightSpell(light);
    assertEquals(Math.max(unit.getMaxHitPoints() - 15,0), unit.getCurrentHitPoints());
  }

  @Test
  public void throwSpellTest(){
    Darkness darkness = new Darkness("darkness book", 10, 1,2);
    Sorcerer sorcerer = new Sorcerer(50, 5, new Location(1, 0),null);
    sorcerer.addItem(darkness);
    darkness.equipTo(sorcerer);

    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    darkness.useOn(unit);
    assertEquals(Math.max(unit.getMaxHitPoints() - 10,0), unit.getCurrentHitPoints());
  }
}