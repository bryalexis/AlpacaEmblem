package model.tactician;

import model.factory.IItemsFactory;
import model.factory.IUnitsFactory;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player.
 * Handle all user instructions and delegate messages to model objects
 * @author Bryan Ortiz P
 * @version 2.3
 * @since 2.1
 */
public class Tactician {
  private List<IUnit> units;
  private IUnit selectedUnit;
  private IEquipableItem equippedItem;
  private Field field;
  private String name;

  private PropertyChangeSupport selectedUnitPCS;

  private IUnitsFactory unitsFactory;
  private IItemsFactory itemsFactory;

  public Tactician(String name, Field map){
    this.units = new ArrayList<>();
    this.name = name;
    this.field = map;
    //this.selectedUnitPCS = new PropertyChangeSupport(this);
  }

  public List<IUnit> getUnits(){
    return units;
  }

  public void selectUnit(IUnit unit){
    assert(units.contains(unit));
    selectedUnitPCS.firePropertyChange("SelectUnit", selectedUnit, unit);
    selectedUnit = unit;
    equippedItem = unit.getEquippedItem();
  }

  public Field getField(){
    return field;
  }

  public void unselectUnit(){
    selectedUnit = null;
  }

  public IUnit getSelectedUnit(){
    return selectedUnit;
  }

  public IEquipableItem getEquippedItem(){
    return equippedItem;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void moveUnitTo(int x, int y){
    selectedUnit.moveTo(field.getCell(x,y));
  }

  public void attackTo(IUnit target){

  }

  public void setUnitsFactory(IUnitsFactory factory){
    unitsFactory = factory;
  }

  public void addUnit(IUnit unit){
    units.add(unit);
  }

  public void addNewUnit(int hp, int movement, Location location) {
    addUnit(unitsFactory.createUnit(hp, movement, location, this));
  }

  public void addGenericUnit(Location location) {
    addUnit(unitsFactory.createGenericUnit(location,this));
  }

  public void addTankUnit(Location location) {
    addUnit(unitsFactory.createTankUnit(location,this));
  }

  public void addFastUnit(Location location){
    addUnit(unitsFactory.createFastUnit(location,this));
  }

  public void setItemsFactory(IItemsFactory factory){
    itemsFactory = factory;
  }

  public void addItem(IEquipableItem item){
    getSelectedUnit().addItem(item);
  }

  public void addNewItem(String name, int power, int minRange, int maxRange){
    addItem(itemsFactory.create(name,power,minRange,maxRange));
  }

  public void addGenericItem(String name){
    addItem(itemsFactory.createGenericItem(name));
  }

  public void addPowerfulItem(String name){
    addItem(itemsFactory.createPowerfulItem(name));
  }

  public void addLongDistanceItem(String name){
    addItem(itemsFactory.createLongDistanceItem(name));
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    selectedUnitPCS.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    selectedUnitPCS.removePropertyChangeListener(pcl);
  }

  public void equipItem(IEquipableItem item){
    getSelectedUnit().equipItem(item);
    equippedItem = item;
  }

  public void selectUnitFromUnits(int index){
    selectUnit(getUnits().get(index));
  }

  public IEquipableItem getItemInInventory(int index){
    return selectedUnit.getItems().get(index);
  }
}
