package model.factory;

import model.items.IEquipableItem;

/**
 * This class defines the constants and methods
 * for generic, powerful and long-distance items
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.5
 */
public abstract class AbstractItemsFactory implements IItemsFactory {

  @Override
  public IEquipableItem createGenericItem(String name) {
    // Generic
    int genericPower = 30;
    int genericMinRange = 1;
    int genericMaxRange = 5;
    return create(name, genericPower, genericMinRange, genericMaxRange);
  }

  @Override
  public IEquipableItem createPowerfulItem(String name) {
    // Powerful
    int powerfulPower = 50;
    int powerfulMinRange = 1;
    int powerfulMaxRange = 3;
    return create(name, powerfulPower, powerfulMinRange, powerfulMaxRange);
  }

  @Override
  public IEquipableItem createLongDistanceItem(String name) {
    // Long Distance
    int longDistancePower = 10;
    int longDistanceMinRange = 3;
    int longDistanceMaxRange = 10;
    return create(name, longDistancePower, longDistanceMinRange, longDistanceMaxRange);
  }
}
