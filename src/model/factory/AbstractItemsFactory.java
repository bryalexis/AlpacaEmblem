package model.factory;

/**
 * This class defines the constants for generic, powerful and long-distance items
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
}
