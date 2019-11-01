package model.factory;

import model.map.InvalidLocation;
import model.units.IUnit;

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

  @Override
  public IUnit createGenericUnit() {
    return createUnit(genericHP, genericMovement, new InvalidLocation(), null);
  }

  @Override
  public IUnit createTankUnit() {
    return createUnit(tankHP, tankMovement, new InvalidLocation(), null);
  }

  @Override
  public IUnit createFastUnit() {
    return createUnit(fastHP, fastMovement, new InvalidLocation(), null);
  }
}
