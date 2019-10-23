package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.healers.Cleric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClericFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return clericF.createGenericUnit(field.getCell(0,0),null);
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return clericF.createTankUnit(field.getCell(0,0),null);
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return clericF.createFastUnit(field.getCell(0,0),null);
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Cleric.class);
    assertEquals(getCreatedTankUnit().getClass(), Cleric.class);
    assertEquals(getCreatedFastUnit().getClass(), Cleric.class);
  }
}