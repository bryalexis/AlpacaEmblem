package model.factory.items;

import model.factory.AbstractItemFactoryTest;
import model.items.IEquipableItem;
import model.items.weapons.Axe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AxeFactoryTest extends AbstractItemFactoryTest {
  @Override
  public IEquipableItem getCreatedGenericItem() {
    return axeF.createGenericItem("uwu");
  }

  @Override
  public IEquipableItem getCreatedPowerfulItem() {
    return axeF.createPowerfulItem("awa");
  }

  @Override
  public IEquipableItem getCreatedLongDistanceItem() {
    return axeF.createLongDistanceItem("owo");
  }

  @Test
  @Override
  public void typeTest() {
    assertEquals(getCreatedGenericItem().getClass(), Axe.class);
    assertEquals(getCreatedPowerfulItem().getClass(), Axe.class);
    assertEquals(getCreatedLongDistanceItem().getClass(), Axe.class);
  }
}
