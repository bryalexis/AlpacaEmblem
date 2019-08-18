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
  private int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  private final int maxItems;
  protected IEquipableItem equippedItem;
  private Location location;

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
    } else{
      currentHitPoints += value;
    }
    // Y si se muere?
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
    if (equippedItem != null) {
      double distance = getLocation().distanceTo(target.getLocation());
      int weaponMaxRange = equippedItem.getMaxRange();
      int weaponMinRange = equippedItem.getMinRange();

      if (distance <= weaponMaxRange && distance >= weaponMinRange) {
        if (equippedItem instanceof Staff) { // Healing
          target.modifyCurrentHitPoints(equippedItem.getPower());
        } else if (equippedItem.isStrongAgainst(target.getEquippedItem())) { //x1.5 hit
          target.modifyCurrentHitPoints(-equippedItem.getPower() * 1.5);
        } else if (equippedItem.isWeakAgainst(target.getEquippedItem()) && -equippedItem.getPower() + 20 < 0) {
          target.modifyCurrentHitPoints(-equippedItem.getPower() + 20); //-20 hit
        } else {// Normal Damage
          target.modifyCurrentHitPoints(-equippedItem.getPower());
        }
      }
    }
  }

}
