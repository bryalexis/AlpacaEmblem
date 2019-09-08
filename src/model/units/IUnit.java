package model.units;

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
import model.units.healers.Cleric;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute by itself are defined here. All units
 * except some special ones can carry at most 3 weapons. The interaction methods are defined in the interface
 * IUnitInteraction
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  //@Deprecated
  //void equipItem(IEquipableItem item);

  /**
   * @return hit points of the unit
   */
  double getCurrentHitPoints();

  /**
   * @return the items carried by this unit
   */
  List<IEquipableItem> getItems();

  /**
   * @return the currently equipped item
   */
  IEquipableItem getEquippedItem();

  /**
   * @param item
   *     the item to be equipped
   */
  void setEquippedItem(IEquipableItem item);

  /**
   * @return the current location of the unit
   */
  Location getLocation();

  /**
   * Sets a new location for this unit,
   */
  void setLocation(final Location location);

  /**
   * @return the number of cells this unit can move
   */
  int getMovement();

  /**
   * Moves this unit to another location.
   * <p>
   * If the other location is out of this unit's movement range, the unit doesn't move.
   */
  void moveTo(Location targetLocation);

  //--

  /**
   * @return the maximum amount of items that the unit can carry.
   */
  int getMaxItems();

  /**
   * Adds an item to be carried by the unit
   * @param item to be added
   */
  void addItem(IEquipableItem item);

  /**
   * Removes an item from the inventory
   * @param item to be deleted
   */
  void removeItem(IEquipableItem item);

  /**
   * The unit receives damage (-) or healing (+)
   * @param value of the modification
   */
  void modifyCurrentHitPoints(double value);

  /**
   * @return the maximum hit points of the unit
   */
  double getMaxHitPoints();

  /**
   * The unit is not alive anymore and is out of combat
   */
  void die();

  /**
   * @return if the unit is alive
   */
  boolean isAlive();

  /**
   * The unit is now in combat
   */
  void startCombat();

  /**
   * The unit is not in combat
   */
  void endCombat();

  /**
   * Transfers the item from player to receptor only if
   * 1- The receptor didn't reach te maximum amount of items
   * 2- The units are at 1 of distance
   * @param receptor
   * @param item
   */
  void giveItem(IUnit receptor, IEquipableItem item);

  /**
   * Attacks another unit with the equipped item
   * The damage done depends on the item that the other unit has equipped,
   * if it hasn't any item equipped, it will receives the default attack.
   * @param target to be attacked
   */
  void attack(IUnit target);

  /**
   * Attacks another unit with the equipped item
   * @param aggressor who attacked
   */
  void counterAttack(IUnit aggressor);

  /**
   * Both the Unit and the enemy are in combat together
   * @param enemy the other fighter
   */
  void startCombatWith(IUnit enemy);

  /**
   * The combat between the unit and the enemy has finished
   * @param enemy the other fighter
   */
  void endCombatWith(IUnit enemy);

  /**
   * @param target who would receive the attack
   * @return if the unit is able to attack the target considering its equipped item
   * its distance, if its cleric, and if the fighters are alive.
   */
  boolean isAbleToAttack(IUnit target);

  /**
   * The unit receives extra Hit Points that depends of the power of the Sorcerer's staff.
   * @param cleric who is healing the unit
   */
  void receiveHealing(Cleric cleric);

  /**
   * The unit receives a darkness-type spell
   * @param sorcerer who attacks
   */
  void receiveDarknessSpell(IUnit sorcerer);

  /**
   * The unit receives a light-type spell
   * @param sorcerer who attacks
   */
  void receiveLightSpell(IUnit sorcerer);

  /**
   * The unit receives a spirit-type spell
   * @param sorcerer who attacks
   */
  void receiveSpiritSpell(IUnit sorcerer);

  /**
   * @return if the unit has an item equipped.
   */
  boolean hasEquippedItem();

  /**
   * Sets an item as equippedItem
   * @param item that will be equipped
   */
  void equipItem(IEquipableItem item);

  /**
   * Sets a Staff as equippedItem.
   * Only clerics can equip this item.
   * Items can only be equipped if they are in the inventory.
   * @param staff that will be equipped.
   */
  void equipStaff(Staff staff);

  /**
   * Sets a Darkness Book as equippedItem.
   * Only sorcerers can equip this item.
   * Items can only be equipped if they are in the inventory.
   * @param darkness book that will be equipped
   */
  void equipDarknessBook(Darkness darkness);

  /**
   * Sets a Light Book as equippedItem
   * Only sorcerers can equip this item
   * Items can only be equipped if they are in the inventory.
   * @param light book that will be equipped
   */
  void equipLightBook(Light light);

  /**
   * Sets a Spirit Book as equippedItem
   * Only sorcerers can equip this item
   * Items can only be equipped if they are in the inventory.
   * @param spirit book that will be equipped
   */
  void equipSpiritBook(Spirit spirit);

  /**
   * Sets an Axe as equippedItem
   * Only fighters can equip this item
   * Items can only be equipped if they are in the inventory.
   * @param axe the weapon that will be equipped
   */
  void equipAxe(Axe axe);

  /**
   * Sets a Bow as equippedItem
   * Only archers can equip this item
   * Items can only be equipped if they are in the inventory.
   * @param bow that will be equipped
   */
  void equipBow(Bow bow);

  /**
   * Sets a Spear as equippedItem
   * Only heroes can equip this item
   * Items can only be equipped if they are in the inventory.
   * @param spear that will be equipped
   */
  void equipSpear(Spear spear);

  /**
   * Sets a Sword as equippedItem
   * Only sword masters can equip this item
   * Items can only be equipped if they are in the inventory.
   * @param sword that will be equipped
   */
  void equipSword(Sword sword);

  /**
   * @return if the unit is in combat with another one.
   */
  boolean getInCombat();

  /**
   * Makes the player to be alive again
   */
  void setAlive();
}