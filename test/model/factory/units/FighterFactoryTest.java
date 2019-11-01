package model.factory.units;

import model.factory.AbstractUnitFactoryTest;
import model.units.IUnit;
import model.units.warriors.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FighterFactoryTest extends AbstractUnitFactoryTest {
  @Override
  public IUnit getCreatedGenericUnit() {
    return fighterF.createGenericUnit();
  }

  @Override
  public IUnit getCreatedTankUnit() {
    return fighterF.createTankUnit();
  }

  @Override
  public IUnit getCreatedFastUnit() {
    return fighterF.createFastUnit();
  }

  @Test
  @Override
  public void typeTest(){
    assertEquals(getCreatedGenericUnit().getClass(), Fighter.class);
    assertEquals(getCreatedTankUnit().getClass(), Fighter.class);
    assertEquals(getCreatedFastUnit().getClass(), Fighter.class);
  }
}