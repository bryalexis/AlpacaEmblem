package model.factory.items;

import model.factory.AbstractItemFactoryTest;
import model.items.IEquipableItem;
import model.items.spellbooks.Light;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightFactoryTest extends AbstractItemFactoryTest {
  @Override
  public IEquipableItem getCreatedGenericItem() {
    return lightF.createGenericItem("uwu");
  }

  @Override
  public IEquipableItem getCreatedPowerfulItem() {
    return lightF.createPowerfulItem("awa");
  }

  @Override
  public IEquipableItem getCreatedLongDistanceItem() {
    return lightF.createLongDistanceItem("owo");
  }

  @Test
  @Override
  public void typeTest() {
    assertEquals(getCreatedGenericItem().getClass(), Light.class);
    assertEquals(getCreatedPowerfulItem().getClass(), Light.class);
    assertEquals(getCreatedLongDistanceItem().getClass(), Light.class);
  }
}