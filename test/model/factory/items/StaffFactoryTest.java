package model.factory.items;

import model.factory.AbstractItemFactoryTest;
import model.items.IEquipableItem;
import model.items.healing.Staff;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffFactoryTest extends AbstractItemFactoryTest {
  @Override
  public IEquipableItem getCreatedGenericItem() {
    return staffF.createGenericItem("uwu");
  }

  @Override
  public IEquipableItem getCreatedPowerfulItem() {
    return staffF.createPowerfulItem("awa");
  }

  @Override
  public IEquipableItem getCreatedLongDistanceItem() {
    return staffF.createLongDistanceItem("owo");
  }

  @Test
  @Override
  public void typeTest() {
    assertEquals(getCreatedGenericItem().getClass(), Staff.class);
    assertEquals(getCreatedPowerfulItem().getClass(), Staff.class);
    assertEquals(getCreatedLongDistanceItem().getClass(), Staff.class);
  }
}