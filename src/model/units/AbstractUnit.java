package model.units;

import static java.lang.Math.min;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.items.healing.Staff;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;
import model.tactician.Tactician;
import model.units.healers.Cleric;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 * @version 2.5
 */
public abstract class AbstractUnit implements IUnit {

  private List<IEquipableItem> items = new ArrayList<>();
  private IEquipableItem equippedItem;
  private final int maxItems;

  private final double maxHitPoints;
  private double currentHitPoints;

  private final int movement;
  private Location location;
  private boolean alive;
  private boolean inCombat;

  private Tactician owner;
  private PropertyChangeSupport isDeadPCS;
  private boolean moved = false;

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  public AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, Tactician owner, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.maxHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    location.setUnit(this);
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.alive = true;
    this.inCombat = false;
    this.owner = owner;
    this.isDeadPCS = new PropertyChangeSupport(this);
  }

  @Override
  public void equipItem(IEquipableItem item){
    item.equipTo(this);
  }

  @Override
  public double getCurrentHitPoints() {
      return currentHitPoints;
  }

  @Override
  public double getMaxHitPoints() {
      return maxHitPoints;
  }

  @Override
  public int getMaxItems() {
      return maxItems;
  }

  @Override
  public List<IEquipableItem> getItems() {
      return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
      return equippedItem;
  }

  @Override
  public Location getLocation(){
      return location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void modifyCurrentHitPoints(double value) {
    if (currentHitPoints + value > maxHitPoints){
      currentHitPoints = maxHitPoints;
    } else if (currentHitPoints + value < 0){
      currentHitPoints = 0;
      die();
    } else {
      currentHitPoints += value;
    }
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public void setLocation(final Location location) {
    location.setUnit(this);
    if(location.getUnit()==this){
      this.location = location;
    }
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      getLocation().setUnit(null);
      setLocation(targetLocation);
    }
  }

  @Override
  public void addItem(IEquipableItem item){
    if (items.size() < maxItems ) {
      items.add(item);
      item.setOwner(this);
    }
  }

  @Override
  public void removeItem(IEquipableItem item){
      items.remove(item);
  }

  @Override
  public void giveItem(IUnit receptor, IEquipableItem item){
    if (receptor.getItems().size() < receptor.getMaxItems() &&
            getLocation().distanceTo(receptor.getLocation())==1){
      receptor.addItem(item);
      item.setOwner(receptor);
      if(equippedItem == item){
        equippedItem = null;
      }
      removeItem(item);
    }
  }

  @Override
  public void die(){
    this.alive = false;
    this.currentHitPoints = 0;
    this.getLocation().setUnit(null); // The Location is now available
    endCombat();
    isDeadPCS.firePropertyChange("The unit died",null, this);
  }

  @Override
  public boolean isAlive(){
    return alive;
  }

  @Override
  public void startCombat(){
    inCombat = true;
  }

  @Override
  public void receiveHealing(Cleric cleric){
      modifyCurrentHitPoints(cleric.getEquippedItem().getPower());
  }

  @Override
  public void startCombatWith(IUnit enemy){
      startCombat();
      enemy.startCombat();
  }

  @Override
  public void endCombat(){
    inCombat = false;
  }

  @Override
  public void endCombatWith(IUnit enemy){
      endCombat();
      enemy.endCombat();
  }

  @Override
  public boolean hasEquippedItem(){
      return getEquippedItem() != null;
  }

  @Override
  public boolean canUseItemOn(IUnit target){
      if(hasEquippedItem() && isAlive() && target.isAlive()){
        Location l1 = getLocation();
        Location l2 = target.getLocation();
        int distance = (int) l1.distanceTo(l2);
        return getEquippedItem().isReachable(distance);
      }
      return false;
  }

  @Override
  public boolean getInCombat(){
    return inCombat;
  }

  @Override
  public void setAlive(){
    alive= true;
  }

  @Override
  public void setOwner(Tactician player){
    owner = player;
  }

  @Override
  public Tactician getOwner(){
    return owner;
  }

  @Override
  public void equipStaff(Staff staff) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipDarknessBook(Darkness darkness) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipLightBook(Light light) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipSpiritBook(Spirit spirit) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipAxe(Axe axe) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipBow(Bow bow) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipSpear(Spear spear) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void equipSword(Sword sword) {
    // Does nothing (implemented in the respective class)
  }

  @Override
  public void resetMovedUnit() {
    moved = false;
  }

  @Override
  public void setUnitMovedInTurn() {
    moved = true;
  }

  @Override
  public boolean wasMoved(){
    return moved;
  }

  @Override
  public void addDeadListener(PropertyChangeListener deadUnitPCL) {
    isDeadPCS.addPropertyChangeListener(deadUnitPCL);
  }

  @Override
  public PropertyChangeSupport getIsDeadPCS(){
    return isDeadPCS;
  }

  @Override
  public void addHeroDeadListener(PropertyChangeListener heroDiePCL){
    // It does nothing in a generic unit
  }
}
