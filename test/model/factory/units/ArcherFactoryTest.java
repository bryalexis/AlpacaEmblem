package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.warriors.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArcherFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return archerF.createGenericUnit(field.getCell(0,3),null);
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return archerF.createTankUnit(field.getCell(1,0),null);
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return archerF.createFastUnit(field.getCell(1,1),null);
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Archer.class);
    assertEquals(getCreatedTankUnit().getClass(), Archer.class);
    assertEquals(getCreatedFastUnit().getClass(), Archer.class);
  }
}