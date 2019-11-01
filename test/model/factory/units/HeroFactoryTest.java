package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.warriors.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return heroF.createGenericUnit();
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return heroF.createTankUnit();
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return heroF.createFastUnit();
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Hero.class);
    assertEquals(getCreatedTankUnit().getClass(), Hero.class);
    assertEquals(getCreatedFastUnit().getClass(), Hero.class);
  }
}