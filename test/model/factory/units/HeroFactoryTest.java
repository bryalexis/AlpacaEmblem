package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.warriors.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return heroF.createGenericUnit(field.getCell(0,0), null);
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return heroF.createTankUnit(field.getCell(0,0), null);
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return heroF.createFastUnit(field.getCell(0,0), null);
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Hero.class);
    assertEquals(getCreatedTankUnit().getClass(), Hero.class);
    assertEquals(getCreatedFastUnit().getClass(), Hero.class);
  }
}