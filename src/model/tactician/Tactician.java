package model.tactician;

import model.map.Field;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player.
 * Handle all user instructions and delegate messages to model objects
 * @author Bryan Ortiz P
 * @version 2.1
 * @since 2.1
 */
public class Tactician {
  private List<IUnit> units;
  private IUnit selectedUnit;
  private Field field;
  private String name;

  public Tactician(String name, Field map){
    this.units = new ArrayList<>();
    this.name = name;
    this.field = map;
  }

  public void addUnit(IUnit unit){
    units.add(unit);
  }

  public List<IUnit> getUnits(){
    return units;
  }

  public void selectUnit(IUnit unit){
    assert(units.contains(unit));
    selectedUnit = unit;
  }

  public void unselectUnit(){
    selectedUnit = null;
  }

  public IUnit getSelectedUnit(){
    return selectedUnit;
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
  public void generateUnits() {
    // generar las unidades del tactician
  }
}
