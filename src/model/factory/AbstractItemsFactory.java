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
  // Generic
  protected final int genericPower = 30;
  protected final int genericMinRange = 1;
  protected final int genericMaxRange = 5;
  // Powerful
  protected final int powerfulPower = 50;
  protected final int powerfulMinRange = 1;
  protected final int powerfulMaxRange = 3;
  // Long Distance
  protected final int longDistancePower = 10;
  protected final int longDistanceMinRange = 3;
  protected final int longDistanceMaxRange = 10;


  @Override
  public IEquipableItem createGenericItem(String name) {
    return create(name,genericPower,genericMinRange,genericMaxRange);
  }

  @Override
  public IEquipableItem createPowerfulItem(String name) {
    return create(name,powerfulPower,powerfulMinRange,powerfulMaxRange);
  }

  @Override
  public IEquipableItem createLongDistanceItem(String name) {
    return create(name,longDistancePower,longDistanceMinRange,longDistanceMaxRange);
  }
}
