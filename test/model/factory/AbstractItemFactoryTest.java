package model.factory;

import model.factory.items.*;
import model.factory.units.*;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Defines some common methods for all the item factory tests
 *
 * @author Bryan Ortiz P
 * @since 2.3
 * @version 2.3
 */
public abstract class AbstractItemFactoryTest {

  protected IItemsFactory axeF;
  //protected IItemsFactory bowF;
  protected IItemsFactory darknessF;
  protected IItemsFactory lightF;
  protected IItemsFactory spearF;
  protected IItemsFactory spiritF;
  protected IItemsFactory staffF;
  protected IItemsFactory swordF;

  public abstract IEquipableItem getCreatedGenericItem();
  public abstract IEquipableItem getCreatedPowerfulItem();
  public abstract IEquipableItem getCreatedLongDistanceItem();

  @BeforeEach
  public void setUp() {
    axeF = new AxeFactory();
    //bowF = new BowFactory();
    darknessF = new DarknessFactory();
    lightF = new LightFactory();
    spearF = new SpearFactory();
    spiritF = new SpiritFactory();
    staffF = new StaffFactory();
    swordF = new SwordFactory();
  }

  @Test
  public abstract void typeTest();

  @Test
  public void createdItemsTest(){
    assertEquals(30,getCreatedGenericItem().getPower());
    assertEquals(1, getCreatedGenericItem().getMinRange());
    assertEquals(5, getCreatedGenericItem().getMaxRange());
    assertEquals(50,getCreatedPowerfulItem().getPower());
    assertEquals(1, getCreatedPowerfulItem().getMinRange());
    assertEquals(3, getCreatedPowerfulItem().getMaxRange());
    assertEquals(10,getCreatedLongDistanceItem().getPower());
    assertEquals(3, getCreatedLongDistanceItem().getMinRange());
    assertEquals(10, getCreatedLongDistanceItem().getMaxRange());
  }

}

