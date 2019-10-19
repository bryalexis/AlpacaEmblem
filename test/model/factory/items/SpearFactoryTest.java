package model.factory.items;

import model.factory.AbstractItemFactoryTest;
import model.items.IEquipableItem;
import model.items.weapons.Spear;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpearFactoryTest extends AbstractItemFactoryTest {
  @Override
  public IEquipableItem getCreatedGenericItem() {
    return spearF.createGenericItem("uwu");
  }

  @Override
  public IEquipableItem getCreatedPowerfulItem() {
    return spearF.createPowerfulItem("awa");
  }

  @Override
  public IEquipableItem getCreatedLongDistanceItem() {
    return spearF.createLongDistanceItem("owo");
  }

  @Test
  @Override
  public void typeTest() {
    assertEquals(getCreatedGenericItem().getClass(), Spear.class);
    assertEquals(getCreatedPowerfulItem().getClass(), Spear.class);
    assertEquals(getCreatedLongDistanceItem().getClass(), Spear.class);
  }
}