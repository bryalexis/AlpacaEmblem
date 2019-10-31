package model.tactician;

import model.factory.IItemsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.tactician.handlers.HeroDieListener;
import model.tactician.handlers.UnitDieListener;
import model.units.IUnit;
import model.units.warriors.Hero;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player.
 * Handle all user instructions and delegate messages to model objects
 * @author Bryan Ortiz P
 * @version 2.4
 * @since 2.1
 */
public class Tactician {

  /**
   * Units references, and properties of tacticians in general
   */
  private List<IUnit> units;
  private IUnit selectedUnit;
  private IEquipableItem equippedItem;
  private Field field;
  private String name;

  /**
   * Property change Supports
   */
  private PropertyChangeSupport selectedUnitPCS, heroDeadPCS;

  /**
   * Property Change Listeners
   */
  private PropertyChangeListener unitDiePCL, heroDiePCL;

  /**
   * Builders for units and items
   */
  private IUnitsFactory unitsFactory;
  private IItemsFactory itemsFactory;

  /**
   * Creates a new instance of Tactician (player)
   * @param name of the tactician
   * @param map where the game will be played
   */
  public Tactician(String name, Field map){
    this.units = new ArrayList<>();
    this.name = name;
    this.field = map;
    this.selectedUnitPCS = new PropertyChangeSupport(this);
    this.heroDeadPCS = new PropertyChangeSupport(this);
    this.unitDiePCL = new UnitDieListener(this);
    this.heroDiePCL = new HeroDieListener(this);
  }

  /**
   * @return the name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the player
   * @param name to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the field where the game will be placed
   */
  public Field getField(){
    return field;
  }

  /**
   * A method to get the units owned by the player
   * @return a list with the units
   */
  public List<IUnit> getUnits(){
    return units;
  }

  /**
   * Selects a unit
   * @param unit to be selected
   */
  public void selectUnit(IUnit unit){
    selectedUnitPCS.firePropertyChange("SelectUnit", selectedUnit, unit);
    selectedUnit = unit;
    equippedItem = unit.getEquippedItem();
  }

  /**
   * Forgets the unit selected
   */
  public void unselectUnit(){
    selectedUnit = null;
  }

  /**
   * @return the unit selected by the player
   */
  public IUnit getSelectedUnit(){
    return selectedUnit;
  }

  /**
   * @return the item equipped by the unit selected
   */
  public IEquipableItem getEquippedItem(){
    // Should we check that we can only see the item equipped by our own units ?
    return equippedItem;
  }

  /**
   * Adds a new unit to the tactician
   * @param unit to be added
   */
  public void addUnit(IUnit unit){
    if(unit!=null){
      units.add(unit);
      unit.addDeadListener(unitDiePCL);
      unit.addHeroDeadListener(heroDiePCL);
    }
  }

  /**
   * @return true if the selected unit belongs to the tactician
   */
  public boolean isMyUnit(){
    return getUnits().contains(getSelectedUnit());
  }

  /**
   * Adds a new item to the selected unit (only if the selected unit belongs to the tactician)
   * @param item to be added
   */
  public void addItem(IEquipableItem item){
    if(isMyUnit()) getSelectedUnit().addItem(item);
  }

  /**
   * Moves the selected unit to a specific position in the field
   * (Only if the position exists)
   * (only if the selected unit belongs to the tactician)
   * @param x row
   * @param y column
   */
  public void moveUnitTo(int x, int y){
    int size = field.getSize();
    if( x < size && y < size && isMyUnit() && !selectedUnit.wasMoved()) {
      selectedUnit.moveTo(field.getCell(x, y));
      selectedUnit.setUnitMovedInTurn();
    }
  }

  /**
   * Use the equipped item of the selected unit to attack another unit
   * (only if the selected unit belongs to the tactician)
   * @param target unit that will receive the attack (can be a unit of the same tactician)
   */
  public void useItemOn(IUnit target){
    if (isMyUnit()) getSelectedUnit().useItemOn(target);
  }

  /**
   * When a hero die, the tactician lose the game.
   */
  public void fireHeroDeath(){
    heroDeadPCS.firePropertyChange("Death of a Hero", null, this);
  }

  /**
   * Equips an item to the selected unit
   * (only if the selected unit belongs to the tactician)
   * @param item to be equipped
   */
  public void equipItem(IEquipableItem item){
    if(isMyUnit()){
      getSelectedUnit().equipItem(item);
      equippedItem = item;
    }
  }

  /**
   * Gives a specific unit un the list of units of the tactician
   * @param index of the unit
   * @return the unit
   */
  public IUnit getUnitByIndex(int index){
    return getUnits().get(index);
  }

  /**
   * Set the selected unit to one of the units in the list
   * It search the unit by its index.
   * @param index of the unit
   */
  public void selectUnitByIndex(int index){
    selectUnit(getUnitByIndex(index));
  }

  /**
   * @return the items of the selected unit
   */
  public List<IEquipableItem> getItems(){
    if (isMyUnit()) return selectedUnit.getItems();
    return null;
  }

  /**
   * @return the maximum amount of HitPoints the selected unit can have
   */
  public double getMaxHP(){
    return selectedUnit.getMaxHitPoints();
  }

  /**
   * Gives the maximum amount of HitPoints that a specific unit in inventory have
   * @param index of the unit
   * @return the max HP
   */
  public double getMaxHPOfUnit(int index){
    return getUnits().get(index).getMaxHitPoints();
  }

  /**
   * Gives the current amount of HitPoints that a specific unit in inventory have
   * @param index of the unit
   * @return the current HP
   */
  public double getHPOfUnit(int index){
    return getUnits().get(index).getCurrentHitPoints();
  }

  /**
   * @return the current amount of HitPoints the selected unit can have
   */
  public double getHP(){
    return selectedUnit.getCurrentHitPoints();
  }

  /**
   * @return how much the selected unit can moves
   */
  public int getMovement(){
    if (isMyUnit()) return selectedUnit.getMovement();
    return 0;
  }

  /**
   * @return the location of the selected unit
   */
  public Location getLocation(){
    return selectedUnit.getLocation();
  }

  /**
   * @return the Tactician owner of the selected unit
   */
  public Tactician getOwner(){
    return selectedUnit.getOwner();
  }

  /**
   * @return the power of the item equipped by the selected unit
   */
  public int getPowerEquippedItem(){
    if(isMyUnit()) return selectedUnit.getEquippedItem().getPower();
    return 0;
  }

  /**
   * Gets an item from the inventory of the selected unit
   * (Only if the selected unit belongs to the tactician)
   * @param index of the item in inventory
   * @return the item
   */
  public IEquipableItem getItemByIndex(int index){
    if (isMyUnit()) return getItems().get(index);
    return null;
  }


  // ==============================================================================
  //                        FACTORY AND BUILDER METHOD
  // ==============================================================================

  /**
   * Sets the factory for units
   * @param factory to be set
   */
  public void setUnitsFactory(IUnitsFactory factory){
    unitsFactory = factory;
  }

  /**
   * Adds a completely configurable new unit
   * @param hp hit points of the unit
   * @param movement how much it can move
   * @param location in the map where the unit will be placed by default
   */
  public void addNewUnit(int hp, int movement, Location location) {
    addUnit(unitsFactory.createUnit(hp, movement, location, this));
  }

  /**
   * Adds a new generic unit, a generic unit has:
   *  - 100 hit points
   *  - 3 movement
   * @param location in the map where the unit will be placed by default
   */
  public void addGenericUnit(Location location) {
    addUnit(unitsFactory.createGenericUnit(location,this));
  }

  /**
   * Adds a new tank unit, a tank unit has:
   *  - 200 hit points
   *  - 1 movement
   * @param location in the map where the unit will be placed by default
   */
  public void addTankUnit(Location location) {
    addUnit(unitsFactory.createTankUnit(location,this));
  }

  /**
   * Adds a new tank unit, a fast unit has:
   *  - 70 hit points
   *  - 5 movement
   * @param location in the map where the unit will be placed by default
   */
  public void addFastUnit(Location location){
    addUnit(unitsFactory.createFastUnit(location,this));
  }

  /**
   * Sets the factory for items
   * @param factory to be set
   */
  public void setItemsFactory(IItemsFactory factory){
    itemsFactory = factory;
  }

  /**
   * Adds a completely configurable new item to the selected unit
   * @param name of the item
   * @param power of the item
   * @param minRange of the item
   * @param maxRange of the item
   */
  public void addNewItem(String name, int power, int minRange, int maxRange){
    addItem(itemsFactory.create(name,power,minRange,maxRange));
  }

  /**
   * Adds a generic item to the selected unit, a generic item has:
   *  - 30 power
   *  - 1 minRange
   *  - 5 maxRange
   *  (Exception are bows, with 25 power, 2 minRange, 8 maxRange)
   * @param name of the item
   */
  public void addGenericItem(String name){
    addItem(itemsFactory.createGenericItem(name));
  }

  /**
   * Adds a powerful item to the selected unit, a powerful item has:
   *  - 50 power
   *  - 1 minRange
   *  - 3 maxRange
   *  (Exception are bows, with 40 power, 2 minRange, 6 maxRange)
   * @param name of the item
   */
  public void addPowerfulItem(String name){
    addItem(itemsFactory.createPowerfulItem(name));
  }

  /**
   * Adds a long-distance item to the selected unit, a long-distance item has:
   *  - 10 power
   *  - 1 minRange
   *  - 10 maxRange
   *  (Exception are bows, with 10 power, 2 minRange, 10 maxRange)
   * @param name of the item
   */
  public void addLongDistanceItem(String name){
    addItem(itemsFactory.createLongDistanceItem(name));
  }



  // ==============================================================================
  //                            LISTENERS METHODS
  // ==============================================================================

  /**
   * Adds a new listener for when a unit is selected
   * @param unitSelectedPCL the listener
   */
  public void addUnitSelectedListener(PropertyChangeListener unitSelectedPCL) {
    selectedUnitPCS.addPropertyChangeListener(unitSelectedPCL);
  }

  /**
   * Adds a new listener for when a hero die
   * @param heroDeadPCL the listener
   */
  public void addHeroDeadListener(PropertyChangeListener heroDeadPCL) {
    heroDeadPCS.addPropertyChangeListener(heroDeadPCL);
  }

}
