package model.factory;

import model.factory.units.*;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    FieldFactory factory= new FieldFactory();
    Random r = new Random();
    this.field = factory.createMap(r.nextLong(), 10, false);
  }

  @Test
  public abstract void typeTest();

  @Test
  public void createdUnitsTest(){
    IUnit generic = getCreatedGenericUnit();
    IUnit tank = getCreatedTankUnit();
    IUnit fast = getCreatedFastUnit();
    assertEquals(100,generic.getMaxHitPoints());
    assertEquals(3, generic.getMovement());
    assertEquals(200,tank.getMaxHitPoints());
    assertEquals(1, tank.getMovement());
    assertEquals(70,fast.getMaxHitPoints());
    assertEquals(5, fast.getMovement());
  }

}
