package model.builder.items;

import model.builder.IItemsFactory;
import model.items.IEquipableItem;

public class AxeFactory implements IItemsFactory {
  @Override
  public IEquipableItem createGenericItem(String name) {
    return null;
  }

  @Override
  public IEquipableItem createPowerfulItem(String name) {
    return null;
  }

  @Override
  public IEquipableItem createLongDistanceItem(String name) {
    return null;
  }
}
