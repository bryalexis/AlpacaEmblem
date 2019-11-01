package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.carriers.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlpacaFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return alpacaF.createGenericUnit();
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return alpacaF.createTankUnit();
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return alpacaF.createFastUnit();
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Alpaca.class);
    assertEquals(getCreatedTankUnit().getClass(), Alpaca.class);
    assertEquals(getCreatedFastUnit().getClass(), Alpaca.class);
  }
}
