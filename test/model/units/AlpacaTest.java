package model.units;

import model.items.IEquipableItem;
import model.items.nullitem.NullItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.weapons.Axe;
import model.units.carriers.Alpaca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0),null);
  }

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
  }

  @Override
  public IUnit getEquippedTestUnit() {
    return getTestUnit();
  }

  @Test
  @Override
  public void testGiveItem(){
    IUnit unit = getTestUnit();
    unit.addItem(sword);
    Alpaca alpaca = getTargetAlpaca();


    alpaca.moveTo(field.getCell(1,0));
    assertNull(field.getCell(2,0).getUnit());

    unit.giveItem(alpaca,sword);
    assertFalse(unit.getItems().contains(sword));
    assertTrue(alpaca.getItems().contains(sword));
    alpaca.removeItem(sword);

    unit = getEquippedTestUnit();
    IEquipableItem item = unit.getEquippedItem();

    // Distance > 1
    alpaca.moveTo(field.getCell(2,0));
    assertEquals(alpaca, field.getCell(2,0).getUnit());
    assertEquals(alpaca.getLocation(),field.getCell(2,0));
    alpaca.giveItem(unit,sword);
    assertFalse(unit.getItems().contains(sword));
  }

  @Test
  @Override
  public void testIsAbleToAttack(){
    IUnit unit = getEquippedTestUnit();
    assertFalse(unit.canUseItemOn(targetAlpaca));
  }

}