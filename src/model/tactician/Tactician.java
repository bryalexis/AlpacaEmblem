package model.tactician;

import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.tactician.handlers.HeroDieListener;
import model.tactician.handlers.UnitDieListener;
import model.units.IUnit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player.
 * Handle all user instructions and delegate messages to model objects
 * @author Bryan Ortiz P
 * @version 2.5
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
  private PropertyChangeSupport heroDeadPCS;

  /**
   * Property Change Listeners
   */
  private PropertyChangeListener unitDiePCL, heroDiePCL;

  /**
   * Creates a new instance of Tactician (player)
   * @param name of the tactician
   * @param map where the game will be played
   */
  public Tactician(String name, Field map){
    this.units = new ArrayList<>();
    this.name = name;
    this.field = map;
    this.heroDeadPCS = new PropertyChangeSupport(this);
    this.unitDiePCL = new UnitDieListener(this);
    this.heroDiePCL = new HeroDieListener(this);
  }

  // ==============================================================================
  //                        TACTICIAN SELF METHODS
  // ==============================================================================

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
   * Adds a new unit to the tactician
   * @param unit to be added
   */
  public void addUnit(IUnit unit){
    if(unit.getOwner()==null){
      units.add(unit);
      unit.setOwner(this);
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
   * Set the selected unit to one of the units in the list
   * It search the unit by its index.
   * @param index of the unit
   */
  public void selectUnitByIndex(int index){
    selectUnit(getUnitByIndex(index));
  }

  /**
   * @return the last added unit
   */
  private IUnit getLastAddedUnit(){
    return getUnitByIndex(getUnits().size()-1);
  }

  /**
   * Sets the location of the last added unit
   * @param location where it will be placed
   */
  public void setLastAddedLocation(Location location){
    if(location.getUnit()==null)
      getLastAddedUnit().setLocation(location);
  }

  /**
   * Gives a specific unit un the list of units of the tactician
   * @param index of the unit
   * @return the unit
   */
  public IUnit getUnitByIndex(int index){
    return getUnits().get(index);
  }


  // ==============================================================================
  //                        SELECTED UNIT METHODS
  // ==============================================================================

  /**
   * @return the item equipped by the unit selected
   */
  public IEquipableItem getEquippedItem(){
    if(isMyUnit()) return equippedItem;
    return null;
  }

  /**
   * Adds a new item to the selected unit (only if the selected unit belongs to the tactician)
   * @param item to be added
   */
  public void addItem(IEquipableItem item){
    if(isMyUnit()) getSelectedUnit().addItem(item);
  }

  /**
   * Sets the Location of a unit in the map
   * @param x row
   * @param y column
   */
  public void setUnitLocation(int x, int y){
    if(isMyUnit()) getSelectedUnit().setLocation(field.getCell(x,y));
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
   * @return the current amount of HitPoints the selected unit can have
   */
  public double getHP(){
    return selectedUnit.getCurrentHitPoints();
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

  /**
   * Gets an item from the inventory of the selected unit
   * (Only if the selected unit belongs to the tactician)
   * @param name of the item
   * @return the item
   */
  public IEquipableItem getItemByName(String name){
    if(isMyUnit()){
      for(IEquipableItem item: getItems()){
        if(item.getName().equals(name)) return item;
      }
    }
    return null;
  }

  /**
   * Gives an item to a specific unit
   * @param receiver who will receive the item
   * @param item that will be given
   */
  public void giveItem(IUnit receiver, IEquipableItem item){
    if(isMyUnit()) selectedUnit.giveItem(receiver, item);
  }

  /**
   * Shows the power of a item of the selected unit, searching by item in inventory
   * @param name of the item
   * @return the power
   */
  public int getItemPowerByName(String name){
    if (isMyUnit()) return getItemByName(name).getPower();
    return 0;
  }

  /**
   * Shows the min range of a item of the selected unit, searching by item in inventory
   * @param name of the item
   * @return the min range
   */
  public int getItemMinRangeByName(String name){
    if (isMyUnit()) return getItemByName(name).getMinRange();
    return 0;
  }

  /**
   * Shows the max range of a item of the selected unit, searching by item in inventory
   * @param name of the item
   * @return the max range
   */
  public int getItemMaxRangeByName(String name){
    if (isMyUnit()) return getItemByName(name).getMaxRange();
    return 0;
  }

  /**
   * Shows the max range of a item of the selected unit, searching by item in inventory
   * @param name of the item
   * @return the max range
   */
  public IUnit getItemTacticianByName(String name){
    if (isMyUnit()) return getItemByName(name).getOwner();
    return null;
  }



  // ==============================================================================
  //                            LISTENERS METHODS
  // ==============================================================================

  /**
   * Adds a new listener for when a hero die
   * @param heroDeadPCL the listener
   */
  public void addHeroDeadListener(PropertyChangeListener heroDeadPCL) {
    heroDeadPCS.addPropertyChangeListener(heroDeadPCL);
  }

  /**
   * When a hero die, the tactician lose the game.
   */
  public void fireHeroDeath(){
    heroDeadPCS.firePropertyChange("Death of a Hero", null, this);
  }

}
