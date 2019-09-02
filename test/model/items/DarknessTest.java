package model.items;

import model.items.spellbooks.Darkness;
import model.map.Location;
import model.units.magic.Sorcerer;
import model.units.IUnit;

/**
 * Test set for Darkness books
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
class DarknessTest extends AbstractTestItem {

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
    sorcerer = new Sorcerer(50, 5, new Location(0, 0));
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
}