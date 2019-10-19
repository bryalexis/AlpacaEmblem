package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.warriors.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordMasterFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return swordMasterF.createGenericUnit(field.getCell(0,0));
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return swordMasterF.createTankUnit(field.getCell(0,0));
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return swordMasterF.createFastUnit(field.getCell(0,0));
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), SwordMaster.class);
    assertEquals(getCreatedTankUnit().getClass(), SwordMaster.class);
    assertEquals(getCreatedFastUnit().getClass(), SwordMaster.class);
  }
}