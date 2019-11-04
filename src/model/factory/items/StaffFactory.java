package model.factory.items;

import model.factory.AbstractItemsFactory;
import model.items.healing.Staff;

/**
 * This class creates instances of Staffs
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public class StaffFactory extends AbstractItemsFactory {

  @Override
  public Staff create(String name, int power, int minRange, int maxRange) {
    return new Staff(name,power,minRange,maxRange);
  }

}
