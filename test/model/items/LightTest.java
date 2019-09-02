package model.items;

import model.items.spellbooks.Light;
import model.map.Location;
import model.units.magic.Sorcerer;
import model.units.IUnit;

/**
 * Test set for Light books
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
class LightTest extends AbstractTestBook {

  private Light lightBook;
  private Light wrongLightBook;
  private Sorcerer sorcerer;

  @Override
  public void setTestItem() {
    expectedName = "Common light book";
    expectedPower = 10;
    expectedMinRange = 1;
    expectedMaxRange = 2;
    lightBook = new Light(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
  }

  /**
   * Sets up an item with wrong ranges setted.
   */
  @Override
  public void setWrongRangeItem() {
    wrongLightBook = new Light("Wrong light book", 0, -1, -2);
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
    return wrongLightBook;
  }

  @Override
  public IEquipableItem getTestItem() {
    return lightBook;
  }

  /**
   * @return a unit that can equip the item being tested
   */
  @Override
  public IUnit getTestUnit() {
    return sorcerer;
  }
}