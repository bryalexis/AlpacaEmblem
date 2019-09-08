package model.items;

import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.IUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Defines some common methods for all the healing tests
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public abstract class AbstractTestHealing extends AbstractTestItem {

  @Test
  public void takeInAxeAttackTest() {
    Axe axe = new Axe("axe", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInAxeAttack(axe);
    assertEquals(Math.max(unit.getMaxHitPoints() - 10,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInSpearAttackTest() {
    Spear spear = new Spear("spear", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInSpearAttack(spear);
    assertEquals(Math.max(unit.getMaxHitPoints() - 10,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInSwordAttackTest() {
    Sword sword = new Sword("sword", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInSwordAttack(sword);
    assertEquals(Math.max(unit.getMaxHitPoints() - 10,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInDarknessSpellTest(){
    Darkness darkness = new Darkness("darkness book", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInDarknessSpell(darkness);
    assertEquals(Math.max(unit.getMaxHitPoints() - 15,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInSpiritSpellTest(){
    Spirit spirit = new Spirit("spirit book", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInSpiritSpell(spirit);
    assertEquals(Math.max(unit.getMaxHitPoints() - 15,0), unit.getCurrentHitPoints());
  }

  @Test
  public void takeInLightSpellTest(){
    Light light = new Light("light book", 10, 1,2);
    IEquipableItem item = getTestItem();
    IUnit unit = getTestUnit();
    unit.addItem(item);
    item.equipTo(unit);

    item.takeInLightSpell(light);
    assertEquals(Math.max(unit.getMaxHitPoints() - 15,0), unit.getCurrentHitPoints());
  }

}
