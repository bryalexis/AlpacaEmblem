package model.tactician;

import model.factory.IItemsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
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
  // Additionally we save a reference to all the heroes in the list of units
  private List<Hero> heroes;

  /**
   * Property change Supports
   */
  private PropertyChangeSupport selectedUnitPCS, heroDeadPCS;

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
    this.heroes = new ArrayList<>();
    this.selectedUnitPCS = new PropertyChangeSupport(this);
    this.heroDeadPCS = new PropertyChangeSupport(this);
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
    units.add(unit);
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
    if( x < size && y < size && isMyUnit())
      selectedUnit.moveTo(field.getCell(x,y));
  }

  /**
   * Use the equipped item of the selected unit to attack another unit
   * (only if the selected unit belongs to the tactician)
   * @param target unit that will receive the attack (can be a unit of the same tactician)
   */
  public void useItemOn(IUnit target){
    if (isMyUnit()) getSelectedUnit().useItemOn(target);
    Tactician targetOwner = target.getOwner();
    if(aHeroWasKilled())
      fireHeroDeath(this);
    if(targetOwner.aHeroWasKilled())
      fireHeroDeath(targetOwner);
  }

  /**
   * Launch a property change when a hero die.
   * @param tactician owner of the hero.
   */
  private void fireHeroDeath(Tactician tactician){
    heroDeadPCS.firePropertyChange(
            new PropertyChangeEvent(this, "Death of a Hero", null, tactician)
    );
  }

  /**
   * @return if a hero was killed during an attack
   */
  private boolean aHeroWasKilled(){
    for(Hero hero: heroes){
      if(!hero.isAlive()) return true;
    }
    return false;
  }

  /**
   * Equips an item to the selected unit
   * (only if the selected unit belongs to the tactician)
   * @param item
   */
  public void equipItem(IEquipableItem item){
    if(isMyUnit()){
      getSelectedUnit().equipItem(item);
      equippedItem = item;
    }
  }

  /**
   * Set the selected unit to one of the units in the list
   * It search the unit by its index.
   * @param index of the unit
   */
  public void selectUnitFromUnitsByIndex(int index){
    selectUnit(getUnits().get(index));
  }

  /**
   * @return the items of the selected unit
   */
  public List<IEquipableItem> getItems(){
    if (isMyUnit()) return selectedUnit.getItems();
    return null;
  }

  /**
   * Gets an item from the inventory of the selected unit
   * (Only if the selected unit belongs to the tactician)
   * @param index of the item in inventory
   * @return the item
   */
  public IEquipableItem getItemInInventoryByIndex(int index){
    if (isMyUnit()) return selectedUnit.getItems().get(index);
    return null;
  }

  /**
   * Saves a reference to the heroes that the unit will have
   * @param hero to be saved
   */
  public void addHero(Hero hero) {
    heroes.add(hero);
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
