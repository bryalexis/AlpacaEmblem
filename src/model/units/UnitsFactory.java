package model.units;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.carriers.Alpaca;
import model.units.healers.Cleric;
import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import model.units.warriors.Fighter;
import model.units.warriors.Hero;
import model.units.warriors.SwordMaster;

/**
 * Units Factory
 * Creates instances of units with its respective parameters.
 * @version 2.2
 * @since 2.2
 * @author Bryan Ortiz P
 */
public class UnitsFactory {

  /**
   * Creates an Alpaca
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return the unique and unparalleled alpaca.
   */
  public Alpaca createAlpaca(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new Alpaca(hitPoints, movement, location, items);
  }

  /**
   * Creates a Cleric
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return the kind cleric.
   */
  public Cleric createCleric(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new Cleric(hitPoints, movement, location, items);
  }

  /**
   * Creates a Sorcerer
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return the great rasputin
   */
  public Sorcerer createSorcerer(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new Sorcerer(hitPoints, movement, location, items);
  }

  /**
   * Creates a Archer
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return a super robin hood
   */
  public Archer createArcher(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new Archer(hitPoints, movement, location, items);
  }

  /**
   * Creates a Fighter
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return subzero
   */
  public Fighter createFighter(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new Fighter(hitPoints, movement, location, items);
  }

  /**
   * Creates a Hero
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return allMight
   */
  public Hero createHero(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new Hero(hitPoints, movement, location, items);
  }

  /**
   * Creates a SwordMaster
   * @param hitPoints the maximum and default amount of hit points
   * @param movement the number of cells that it can moves
   * @param location where it will be placed at the beginning
   * @param items the items that it will have
   * @return samurai Jack
   */
  public SwordMaster createSwordMaster(int hitPoints, int movement, Location location, IEquipableItem... items){
    return new SwordMaster(hitPoints, movement, location, items);
  }
}
