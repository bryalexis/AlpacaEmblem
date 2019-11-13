package model.items.nullitem;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.IUnit;

public class NullItem extends AbstractItem {

  /**
   * Creates a new Null Item
   */
  public NullItem() {
    super("Null Item", 0, 0, 0);
  }


  @Override
  public void equipTo(IUnit unit) {
    unit.equipNullItem(this);
  }

  @Override
  public void takeInMagicalAttack(IEquipableItem item) {
    takeInNormalAttack(item.getPower());
  }

  @Override
  public void takeInDarknessSpell(Darkness spell) {
    takeInMagicalAttack(spell);
  }

  @Override
  public void takeInSpiritSpell(Spirit spell) {
    takeInMagicalAttack(spell);
  }

  @Override
  public void takeInLightSpell(Light spell) {
    takeInMagicalAttack(spell);
  }

  @Override
  public void takeInPhysicalAttack(IEquipableItem item) {
    takeInNormalAttack(item.getPower());
  }

  @Override
  public void takeInAxeAttack(Axe axe) {
    takeInPhysicalAttack(axe);
  }

  @Override
  public void takeInSpearAttack(Spear spear) {
    takeInPhysicalAttack(spear);
  }

  @Override
  public void takeInSwordAttack(Sword sword) {
    takeInPhysicalAttack(sword);
  }

  @Override
  public void useOn(IUnit target) {
    // does nothing
  }
}
