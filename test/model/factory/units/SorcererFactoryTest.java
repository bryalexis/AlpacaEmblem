package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.magic.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorcererFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return sorcererF.createGenericUnit(field.getCell(0,0), null);
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return sorcererF.createTankUnit(field.getCell(1,0), null);
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return sorcererF.createFastUnit(field.getCell(2,0), null);
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Sorcerer.class);
    assertEquals(getCreatedTankUnit().getClass(), Sorcerer.class);
    assertEquals(getCreatedFastUnit().getClass(), Sorcerer.class);
  }
}