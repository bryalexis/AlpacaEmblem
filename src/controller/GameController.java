package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.map.Location;
import model.tactician.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.1
 * @since 2.0
 */
public class GameController {

  private Map <String, Tactician> tacticians;
  private Field map;
  private Tactician playerInTurn;

  private int numberOfPlayers;
  private int maxRounds;
  private List<String> winners;

  private int mapSize;
  private int roundNumber;

  private IUnit selectedUnit;
  private IEquipableItem selectedItem;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.winners = new ArrayList<>();
    this.numberOfPlayers = numberOfPlayers;
    this.mapSize = mapSize;
    generateMap();
  }

  private void generateMap(){
    List<Location> cells = new ArrayList<>();
    for(int i=0;i<mapSize;i++){
      for(int j=0; j<mapSize;j++) {
        Location L = new Location(0, 0);
        map.addCells(false, L);
      }
    }
  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return new ArrayList<>(tacticians.values());
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently playing
   */
  public Tactician getTurnOwner() {
    return playerInTurn;
  }

  /**
   * @return the number of rounds since the start of the game.
   */
  public int getRoundNumber() {
    return roundNumber;
  }

  /**
   * @return the maximum number of rounds a match can last
   */
  public int getMaxRounds() {
    return maxRounds;
  }

  /**
   * Finishes the current player's turn.
   */
  public void endTurn() {

  }

  /**
   * Removes a tactician and all of it's units from the game.
   * @param tactician the player to be removed
   */
  public void removeTactician(String tactician) {
    tacticians.remove(tactician);
  }

  public void createPlayers(){
    for(int i=0; i<numberOfPlayers;i++){
      Tactician player = new Tactician("Player "+ i, map);
      player.generateUnits();
      tacticians.put("Player "+ i, player);
    }
  }

  /**
   * Starts the game.
   * @param maxTurns the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {

  }

  /**
   * Starts a game without a limit of turns.
   */
  public void initEndlessGame() {
    initGame(-1); //change this
  }

  /**
   * @return the winner of this game, if the match ends in a draw returns a list of all the winners
   */
  public List<String> getWinners() {
    return winners;
  }

  /**
   * @return the current player's selected unit
   */
  public IUnit getSelectedUnit() {
    return playerInTurn.getSelectedUnit();
  }

  /**
   * Selects a unit in the game map
   * @param x horizontal position of the unit
   * @param y vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    Location loc = getGameMap().getCell(x,y);
    selectedUnit = loc.getUnit();
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return selectedUnit.getItems();
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   * @param index the location of the item in the inventory.
   */
  public void equipItem(int index) {
    IEquipableItem item = getItems().get(index);
    selectedUnit.equipItem(item);
  }

  /**
   * Uses the equipped item on a target
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void useItemOn(int x, int y) {
    Location loc = getGameMap().getCell(x,y);
    IUnit target = loc.getUnit();
    selectedUnit.useItemOn(target);
  }

  /**
   * Selects an item from the selected unit's inventory.
   * @param index the location of the item in the inventory.
   */
  public void selectItem(int index) {
    selectedItem = getItems().get(index);
  }

  /**
   * Gives the selected item to a target unit.
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void giveItemTo(int x, int y) {
    IUnit target = getGameMap().getCell(x,y).getUnit();
    selectedUnit.giveItem(target, selectedItem);
  }
}
