package model.factory;

import model.factory.units.*;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import model.units.*;
import model.units.carriers.Alpaca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Defines some common methods for all the unit factory tests
 *
 * @author Bryan Ortiz P
 * @since 2.3
 * @version 2.3
 */
public abstract class AbstractUnitFactoryTest {

  protected IUnitsFactory alpacaF;
  protected IUnitsFactory archerF;
  protected IUnitsFactory clericF;
  protected IUnitsFactory fighterF;
  protected IUnitsFactory heroF;
  protected IUnitsFactory sorcererF;
  protected IUnitsFactory swordMasterF;

  protected Field field;

  public abstract IUnit getCreatedGenericUnit();
  public abstract IUnit getCreatedTankUnit();
  public abstract IUnit getCreatedFastUnit();

  @BeforeEach
  public void setUp() {
    alpacaF = new AlpacaFactory();
    archerF = new ArcherFactory();
    clericF = new ClericFactory();
    fighterF = new FighterFactory();
    heroF = new HeroFactory();
    sorcererF = new SorcererFactory();
    swordMasterF = new SwordMasterFactory();
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
            new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
            new Location(2, 1), new Location(2, 2));
  }

  @Test
  public abstract void typeTest();

  @Test
  public void createdUnitsTest(){
    assertEquals(50,getCreatedGenericUnit().getMaxHitPoints());
    assertEquals(3, getCreatedGenericUnit().getMovement());
    assertEquals(100,getCreatedTankUnit().getMaxHitPoints());
    assertEquals(1, getCreatedTankUnit().getMovement());
    assertEquals(30,getCreatedFastUnit().getMaxHitPoints());
    assertEquals(5, getCreatedFastUnit().getMovement());
  }

}
