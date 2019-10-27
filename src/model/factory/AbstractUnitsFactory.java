package model.factory;

/**
 * This class defines the constants for generic, powerful and long-distance items
 */
public abstract class AbstractUnitsFactory implements IUnitsFactory{
  protected final int genericHP = 100;
  protected final int genericMovement = 3;
  protected final int tankHP = 200;
  protected final int tankMovement = 1;
  protected final int fastHP = 70;
  protected final int fastMovement = 5;
}
