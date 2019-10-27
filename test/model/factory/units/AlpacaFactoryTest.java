package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.carriers.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlpacaFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return alpacaF.createGenericUnit(field.getCell(0,0),null);
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return alpacaF.createTankUnit(field.getCell(0,1),null);
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return alpacaF.createFastUnit(field.getCell(0,2),null);
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Alpaca.class);
    assertEquals(getCreatedTankUnit().getClass(), Alpaca.class);
    assertEquals(getCreatedFastUnit().getClass(), Alpaca.class);
  }
}
