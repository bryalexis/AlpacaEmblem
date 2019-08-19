package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.items.Staff;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  private final int maxItems;
  protected IEquipableItem equippedItem;
  private Location location;
  private boolean isAlive;
  public boolean inCombat;

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
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.maxHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.isAlive = true;
    this.inCombat = false;
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public int getMaxHitPoints() {
    return maxHitPoints;
  }

  @Override
  public void modifyCurrentHitPoints(double value) {
    if (currentHitPoints + value > maxHitPoints){
      currentHitPoints = maxHitPoints;
    } else if(currentHitPoints + value < 0){
      currentHitPoints = 0;
      die();
    } else{
      currentHitPoints += value;
    }
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
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public int getMaxItems(){ return maxItems; }

  @Override
  public void addItem(IEquipableItem item){
    items.add(item);
  }

  @Override
  public void removeItem(IEquipableItem item){
    items.remove(item);
  }

  @Override
  public void giveItem(IUnit receptor, IEquipableItem item){
    if (receptor.getItems().size() < receptor.getMaxItems() &&
            getLocation().isNeighbour(receptor.getLocation())){
      receptor.addItem(item);
      removeItem(item);
    }
  }

  @Override
  public void attack(IUnit target){
    IEquipableItem equippedItem = getEquippedItem();
    if (equippedItem != null && target.isAlive()) {
      double distance = getLocation().distanceTo(target.getLocation());
      if (distance <= equippedItem.getMaxRange() && distance >= equippedItem.getMinRange()) {
        // Healing
        if (equippedItem instanceof Staff) {
          target.modifyCurrentHitPoints(equippedItem.getPower());
          // Strong (x1.5 hit)
        } else if (equippedItem.isStrongAgainst(target.getEquippedItem())) {
          target.modifyCurrentHitPoints(-equippedItem.getPower() * 1.5);
          // Weak (-20 hit)
        } else if (equippedItem.isWeakAgainst(target.getEquippedItem()) && -equippedItem.getPower() + 20 < 0) {
          target.modifyCurrentHitPoints(-equippedItem.getPower() + 20);
          // Normal Damage
        } else {
          target.modifyCurrentHitPoints(-equippedItem.getPower());
        }
      }
    }
  }

  @Override
  public void die(){
    this.isAlive = false;
    endCombat();
  }

  @Override
  public boolean isAlive(){
    return isAlive;
  }

  @Override
  public void startCombat(){
    inCombat = true;
  }

  @Override
  public void endCombat(){
    inCombat = false;
  }
}
