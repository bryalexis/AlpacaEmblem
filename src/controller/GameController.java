package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.factory.FieldFactory;
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

  // Player things
  private List<Tactician> tacticians;
  private Tactician playerInTurn;
  private Random random;
  private long seedPlayerSelection;

  private int numberOfPlayers;
  private int maxRounds;
  private List<String> winners;

  // Configuration of the game
  private int mapSize;
  private Field map;
  private int roundNumber;
  private int turnNumber;
  private List<Tactician> orderRound;

  // Things of the unit selected
  private IUnit selectedUnit;
  private List<IEquipableItem> selectedUnitItems;
  private IEquipableItem selectedItem;

  // Property Change Listeners


  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers
   *     the number of players for this game
   * @param mapSize
   *     the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.tacticians = new ArrayList<>();
    this.orderRound = new ArrayList<>();
    this.numberOfPlayers = numberOfPlayers;
    this.mapSize = mapSize;
    this.random = new Random();
    this.seedPlayerSelection = random.nextLong();
    this.random.setSeed(seedPlayerSelection);
    generateMap();
    createPlayers();
    setTurnsInRound();
    playerInTurn = orderRound.get(turnNumber);
  }

  /**
   * To generate the order in the turns of the game, we will
   * use a random method that uses a seed
   * @return the random seed to select players
   */
  public long getSeedPlayerSelection(){
    return seedPlayerSelection;
  }

  /**
   * This method generates the map of the game, using a random seed
   * to ensure that every game will have a random map.
   */
  private void generateMap(){
    FieldFactory ff = new FieldFactory();
    map = ff.createMap(new Random().nextLong(), mapSize);
  }

  /**
   * @return the list of all the tacticians participating in the game.
   */
  public List<Tactician> getTacticians() {
    return tacticians;
  }

  /**
   * @return the map of the current game
   */
  public Field getGameMap() {
    return map;
  }

  /**
   * @return the tactician that's currently in turn
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
    turnNumber++;
    turnNumber%=numberOfPlayers;
    if(turnNumber == 0){
      setTurnsInRound();
      roundNumber++;
    }
    playerInTurn = orderRound.get(turnNumber);
    checkEndGame();
  }

  /**
   * Finishes the game and set the winners if the conditions are appropriated
   */
  public void checkEndGame(){
    boolean lastRoundReached = roundNumber > getMaxRounds() && getMaxRounds()!=-1;
    boolean onlyOnePlayer = tacticians.size() == 1;
    if (lastRoundReached || onlyOnePlayer){
      playerInTurn = null;
      setWinners();
    }
  }

  /**
   * Sets the order of the turns on the current round
   */
  private void setTurnsInRound(){
    orderRound = new ArrayList<>();
    List<Integer> selected = new ArrayList<>();
    int k, n;
    while (orderRound.size()!=numberOfPlayers){
      k = random.nextInt();
      n = Math.abs(k)%numberOfPlayers;
      Tactician player = tacticians.get(n);
      if(playerCanBeSelectedThisOrder(player,selected,n)){
        orderRound.add(player);
        selected.add(n);
      }
    }
  }

  /**
   * This method returns if the players can be selected in a specific order
   * restricted by the norms of the game (the last player of the last round
   * can't be the first player of the next round, and just one turn per player
   * on each round)
   * @param player to be inserted in the order list
   * @param selected players that are currently selected
   * @param n index of the player
   * @return if the player can be selected
   */
  public boolean playerCanBeSelectedThisOrder(Tactician player, List<Integer> selected, int n){
    if(!selected.contains(n))
      return playerInTurn==null ||
            !(playerInTurn.getName().equals(player.getName()) && selected.isEmpty());
    return false;
  }

  /**
   * Sets the winners of the game
   */
  public void setWinners() {
    for(Tactician t: tacticians){
      this.winners.add(t.getName());
    }
  }

  /**
   * Removes a tactician and all of it's units from the game.
   * @param tactician the player to be removed
   */
  public void removeTactician(String tactician) {
    numberOfPlayers--;
    for(Tactician player: tacticians){
      if(player.getName().equals(tactician)){
        tacticians.remove(player);
        orderRound.remove(player);
        break;
      }
    }
  }

  /**
   * Creates the players of the game according to the max number of players
   */
  public void createPlayers(){
    for(int i=0; i<numberOfPlayers;i++){
      Tactician player = new Tactician("Player "+ i, map);
      // player.addPropertyChangeListener(this);
      tacticians.add(player);
    }
  }

  /**
   * Starts the game.
   * @param maxTurns the maximum number of turns the game can last
   */
  public void initGame(final int maxTurns) {
    maxRounds = maxTurns;
    winners = new ArrayList<>();
    this.roundNumber = 1;
    this.turnNumber = 0;
  }

  /**
   * Starts a game without a limit of turns
   * on this case, we will convent that the maxTurns is -1.
   */
  public void initEndlessGame() {
    initGame(-1);
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
    return selectedUnit;
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

  /**
   * @return the current selected item
   */
  public IEquipableItem getSelectedItem(){
    return selectedItem;
  }


}
