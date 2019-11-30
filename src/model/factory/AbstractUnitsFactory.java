package model.factory;

import model.map.InvalidLocation;
import model.units.IUnit;

/**
 * This class defines the constants and methods for generic,
 * tank and fast units.
 * @author Bryan Ortiz P
 * @since 2.1
 * @version 2.3
 */
public abstract class AbstractUnitsFactory implements IUnitsFactory {

  @Override
  public IUnit createGenericUnit() {
    int genericHP = 100;
    int genericMovement = 3;
    return createUnit(genericHP, genericMovement, new InvalidLocation(), null);
  }

  @Override
  public IUnit createTankUnit() {
    int tankHP = 200;
    int tankMovement = 1;
    return createUnit(tankHP, tankMovement, new InvalidLocation(), null);
  }

  @Override
  public IUnit createFastUnit() {
    int fastHP = 70;
    int fastMovement = 5;
    return createUnit(fastHP, fastMovement, new InvalidLocation(), null);
  }
}
