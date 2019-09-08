package model.units;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.items.IEquipableItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.map.Location;
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
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.maxHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.maxItems = maxItems;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.alive = true;
    this.inCombat = false;
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
    this.location = location;
  }

  @Override
  public void moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
    }
  }

  @Override
  public void addItem(IEquipableItem item){
    if (items.size() < maxItems) {
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
    endCombat();
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
  public boolean isAbleToAttack(IUnit target){
      if(hasEquippedItem() && isAlive() && target.isAlive()){
        return getEquippedItem().isReachable(getLocation().distanceTo(target.getLocation()));
      }
      return false;
  }

  @Override
  public void receiveDarknessSpell(IUnit sorcerer) {
    getEquippedItem().takeInDarknessSpell((Darkness) sorcerer.getEquippedItem());
  }

  @Override
  public void receiveLightSpell(IUnit sorcerer) {
    getEquippedItem().takeInLightSpell((Light) sorcerer.getEquippedItem());
  }

  @Override
  public void receiveSpiritSpell(IUnit sorcerer) {
    getEquippedItem().takeInSpiritSpell((Spirit) sorcerer.getEquippedItem());
  }

  @Override
  public boolean getInCombat(){
    return inCombat;
  }

  @Override
  public void setAlive(){
    alive= true;
  }
}
