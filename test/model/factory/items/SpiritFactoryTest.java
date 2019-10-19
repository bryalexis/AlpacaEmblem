package model.factory.items;

import model.factory.AbstractItemFactoryTest;
import model.items.IEquipableItem;
import model.items.spellbooks.Spirit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpiritFactoryTest extends AbstractItemFactoryTest {
  @Override
  public IEquipableItem getCreatedGenericItem() {
    return spiritF.createGenericItem("uwu");
  }

  @Override
  public IEquipableItem getCreatedPowerfulItem() {
    return spiritF.createPowerfulItem("awa");
  }

  @Override
  public IEquipableItem getCreatedLongDistanceItem() {
    return spiritF.createLongDistanceItem("owo");
  }

  @Test
  @Override
  public void typeTest() {
    assertEquals(getCreatedGenericItem().getClass(), Spirit.class);
    assertEquals(getCreatedPowerfulItem().getClass(), Spirit.class);
    assertEquals(getCreatedLongDistanceItem().getClass(), Spirit.class);
  }
}