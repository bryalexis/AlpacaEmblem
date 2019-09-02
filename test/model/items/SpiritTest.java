package model.items;

import model.items.spellbooks.Spirit;
import model.map.Location;
import model.units.magic.Sorcerer;
import model.units.IUnit;

/**
 * Test set for Spirit books
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
class SpiritTest extends AbstractTestBook {

  private Spirit spiritBook;
  private Spirit wrongSpiritBook;
  private Sorcerer sorcerer;

  @Override
  public void setTestItem() {
    expectedName = "Common spirit book";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    spiritBook = new Spirit(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongSpiritBook = new Spirit("Wrong spirit book", 0, -1, -2);
  }

  /**
   * Sets the unit that will be equipped with the test item
   */
  @Override
  public void setTestUnit() {
    sorcerer = new Sorcerer(50, 5, new Location(0, 0));
  }

  @Override
  public IEquipableItem getWrongTestItem() {
    return wrongSpiritBook;
  }

  @Override
  public IEquipableItem getTestItem() {
    return spiritBook;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return sorcerer;
  }
}