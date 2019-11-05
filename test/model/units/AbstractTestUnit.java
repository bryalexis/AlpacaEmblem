package model.units;

import model.items.*;
import model.items.healing.Staff;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.*;
import model.map.Field;
import model.map.Location;
import model.units.carriers.Alpaca;
import model.units.healers.Cleric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected Darkness darkness;
  protected Spirit spirit;
  protected Light light;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(2, 0),null);
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.darkness = new Darkness("DarknessBook",10,1,2);
    this.spirit = new Spirit("SpiritBook", 10,1,3);
    this.light = new Light("Light", 8,2,4);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    item.equipTo(getTestUnit());
    assertNull(getTestUnit().getEquippedItem());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  @Override
  public Darkness getDarknessBook(){ return  darkness; }

  @Test
  public void equipDarknessBookTest() {
    checkEquippedItem(getDarknessBook());
  }

  @Override
  public Light getLightBook(){ return light; }

  @Override
  @Test
  public void equipLightBookTest() {
    checkEquippedItem(getLightBook());
  }

  @Override
  public Spirit getSpiritBook(){ return spirit; }

  @Override
  @Test
  public void equipSpiritBookTest() {
    checkEquippedItem(getSpiritBook());
  }

  @Override
  @Test
  public void testHasEquippedItem(){
    IUnit unit = getEquippedTestUnit();
    assertEquals(unit.getEquippedItem() != null, unit.hasEquippedItem());
  }

  @Override
  @Test
  public void testIsAbleToAttack(){
    IUnit unit = getEquippedTestUnit();
    if (unit.hasEquippedItem())
      assertTrue(unit.canUseItemOn(targetAlpaca));
    unit.setEquippedItem(null);
    assertFalse(unit.canUseItemOn(targetAlpaca));
    unit = getEquippedTestUnit();
    targetAlpaca.die();
    assertFalse(unit.canUseItemOn(targetAlpaca));
  }

  @Override
  public abstract IUnit getEquippedTestUnit();

  @Override
  @Test
  public void testIsAlive(){
    assertTrue(targetAlpaca.isAlive());
    targetAlpaca.die();
    assertFalse(targetAlpaca.isAlive());
  }

  @Test
  public void removeItemTest(){
    IUnit unit = getEquippedTestUnit();
    IEquipableItem item = unit.getEquippedItem();
    if (item!=null){
      unit.removeItem(item);
      assertFalse(unit.getItems().contains(item));
    }
  }

  @Override
  @Test
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
    if(item!=null){
      unit.giveItem(alpaca,item);
      assertNull(unit.getEquippedItem());
      assertFalse(unit.getItems().contains(item));
      assertTrue(alpaca.getItems().contains(item));

      Darkness oscurito = new Darkness("oscurito",20,1,3);
      Light lucecita = new Light("lucecita",20,1,3);
      Axe hachita = new Axe("hachita",20,1,3);

      unit.addItem(oscurito);
      unit.addItem(lucecita);
      unit.addItem(hachita);
      alpaca.addItem(sword);
      alpaca.giveItem(unit, sword);
      assertTrue(alpaca.getItems().contains(sword));
      assertFalse(unit.getItems().contains(sword));
      unit.removeItem(hachita);
      unit.removeItem(lucecita);
      unit.removeItem(oscurito);
    }

    // Distance > 1
    alpaca.moveTo(field.getCell(2,0));
    assertEquals(alpaca, field.getCell(2,0).getUnit());
    assertEquals(alpaca.getLocation(),field.getCell(2,0));
    alpaca.giveItem(unit,sword);
    assertFalse(unit.getItems().contains(sword));


  }

  @Override
  @Test
  public void testHealing(){
    Cleric cleric = new Cleric(50, 2, field.getCell(1, 0),null);
    IUnit unit = getTestUnit();
    unit.modifyCurrentHitPoints(-unit.getMaxHitPoints()+10);
    cleric.addItem(staff);
    cleric.equipItem(staff);
    cleric.useItemOn(unit);
    assertEquals(10+staff.getPower(), unit.getCurrentHitPoints());
    unit.die();
    cleric.useItemOn(unit);
    assertEquals(0, unit.getCurrentHitPoints());

    unit.setAlive();
    unit.receiveHealing(cleric);
    assertEquals(staff.getPower(), unit.getCurrentHitPoints());

    // Prevents OverHealing
    unit.modifyCurrentHitPoints(unit.getMaxHitPoints()-staff.getPower()*0.5);
    unit.receiveHealing(cleric);
    assertEquals(unit.getMaxHitPoints(), unit.getCurrentHitPoints());
  }

  @Override
  @Test
  public void testCombatState(){
    getTestUnit().startCombatWith(getTargetAlpaca());
    assertTrue(getTestUnit().getInCombat());
    assertTrue(getTargetAlpaca().getInCombat());
    getTestUnit().endCombatWith(getTargetAlpaca());
    assertFalse(getTargetAlpaca().getInCombat());
    assertFalse(getTestUnit().getInCombat());
  }

  @Override
  @Test
  public void alpacaAttackTest(){
    IUnit unit = getEquippedTestUnit();
    getTargetAlpaca().useItemOn(unit);
    assertEquals(unit.getMaxHitPoints(), unit.getCurrentHitPoints());
  }

  @Override
  @Test
  public void clericAttackTest(){
    Cleric cleric = new Cleric(50, 2, field.getCell(0, 0),null);
    cleric.addItem(staff);
    cleric.equipStaff(staff);
    IUnit unit = getEquippedTestUnit();
    cleric.useItemOn(unit);
    assertEquals(unit.getMaxHitPoints(), unit.getCurrentHitPoints());
  }

}
