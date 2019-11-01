package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.warriors.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArcherFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return archerF.createGenericUnit();
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return archerF.createTankUnit();
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return archerF.createFastUnit();
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Archer.class);
    assertEquals(getCreatedTankUnit().getClass(), Archer.class);
    assertEquals(getCreatedFastUnit().getClass(), Archer.class);
  }
}