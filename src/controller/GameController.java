package controller;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.handlers.HeroDeadHandler;
import model.factory.FieldFactory;
import model.factory.IItemsFactory;
import model.factory.IUnitsFactory;
import model.factory.items.*;
import model.factory.units.*;
import model.map.InvalidLocation;
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
 * @version 2.5
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

  // Property Change Listeners
  private PropertyChangeListener heroDeadPCL;

  // Factories
  private IUnitsFactory unitsFactory;
  private IItemsFactory itemsFactory;

  /**
   * Creates the controller for a new game.
   *
   * @param numberOfPlayers the number of players for this game
   * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
   */
  public GameController(int numberOfPlayers, int mapSize) {
    this.tacticians = new ArrayList<>();
    this.orderRound = new ArrayList<>();
    this.numberOfPlayers = numberOfPlayers;
    this.mapSize = mapSize;
    this.random = new Random();
    this.seedPlayerSelection = random.nextLong();
    this.random.setSeed(seedPlayerSelection);

    // Handler
    heroDeadPCL = new HeroDeadHandler(this);

    generateMap();
    createPlayers();
    setTurnsInRound();
    selectUnitsSpecialRound();
    playerInTurn = orderRound.get(turnNumber);
  }

  /**
   * To generate the order in the turns of the game, we will
   * use a random method that uses a seed
   *
   * @return the random seed to select players
   */
  public long getSeedPlayerSelection() {
    return seedPlayerSelection;
  }

  /**
   * This method generates the map of the game, using a random seed
   * to ensure that every game will have a random map.
   */
  private void generateMap() {
    FieldFactory ff = new FieldFactory();
    map = ff.createMap(new Random().nextLong(), mapSize, false);
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
    if (roundNumber == 0) {
      endTurnSelectingUnits();
    } else {
      endTurnInGame();
    }
  }

  /**
   * Ends the turn to select units
   */
  private void endTurnSelectingUnits(){
    turnNumber++;
    if(turnNumber >= numberOfPlayers){
      playerInTurn = null;
    } else {
      playerInTurn = orderRound.get(turnNumber);
    }
  }

  /**
   * Deletes a tactician from the game
   * @param tactician deleted
   */
  public void deleteTactician(Tactician tactician){
    tacticians.remove(tactician);
    numberOfPlayers--;
  }

  /**
   * Ends a turn in the game
   */
  private void endTurnInGame(){
    turnNumber++;
    turnNumber %= numberOfPlayers;
    if (turnNumber == 0){
      endRound();
    }
    playerInTurn = orderRound.get(turnNumber);
    resetMovedUnits();
    checkEndGame();
  }

  /**
   * Ends the round
   */
  private void endRound(){
    setTurnsInRound();
    roundNumber++;
  }

  /**
   * Resets the moved units, so the Tactician can move them again
   * on his new turn.
   */
  private void resetMovedUnits(){
    for (IUnit unit : playerInTurn.getUnits()) {
      unit.resetMovedUnit();
    }
  }

  /**
   * "Starts" the moment where the players can add its units
   */
  private void selectUnitsSpecialRound(){
    this.roundNumber = 0;
  }

  /**
   * Finishes the game and set the winners if the conditions are appropriated
   */
  public void checkEndGame() {
    boolean lastRoundReached = roundNumber > getMaxRounds() && getMaxRounds() != -1;
    boolean onlyOnePlayer = tacticians.size() == 1;
    if (lastRoundReached || onlyOnePlayer) {
      playerInTurn = null;
      setWinners();
    }
  }

  /**
   * Sets the order of the turns on the current round
   */
  private void setTurnsInRound() {
    orderRound = new ArrayList<>();
    List<Integer> selected = new ArrayList<>();
    int k, n;
    while (orderRound.size() != numberOfPlayers) {
      k = random.nextInt();
      n = Math.abs(k) % numberOfPlayers;
      Tactician player = tacticians.get(n);
      if (playerCanBeSelectedThisOrder(player, selected, n)) {
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
   *
   * @param player   to be inserted in the order list
   * @param selected players that are currently selected
   * @param n        index of the player
   * @return if the player can be selected
   */
  public boolean playerCanBeSelectedThisOrder(Tactician player, List<Integer> selected, int n) {
    if (!selected.contains(n))
      return playerInTurn == null ||
              !(playerInTurn.getName().equals(player.getName()) && selected.isEmpty());
    return false;
  }

  /**
   * Sets the winners of the game
   */
  public void setWinners() {
    int maxUnits = 0;
    for (Tactician t : tacticians) {
      if(t.getUnits().size() > maxUnits) maxUnits = t.getUnits().size();
    }
    for (Tactician t : tacticians) {
      if(t.getUnits().size() == maxUnits) this.winners.add(t.getName());
    }
  }

  /**
   * Removes a tactician and all of it's units from the game.
   *
   * @param tactician the player to be removed
   */
  public void removeTactician(String tactician) {
    numberOfPlayers--;
    for (Tactician player : tacticians) {
      if (player.getName().equals(tactician)) {
        tacticians.remove(player);
        orderRound.remove(player);
        break;
      }
    }
  }

  /**
   * Creates the players of the game according to the max number of players
   */
  private void createPlayers() {
    for (int i = 0; i < numberOfPlayers; i++) {
      Tactician player = new Tactician("Player " + i, map);
      player.addHeroDeadListener(heroDeadPCL);
      tacticians.add(player);
    }
  }

  /**
   * Selects a specific unit
   * @param unit selected
   */
  public void selectUnit(IUnit unit) {
    playerInTurn.selectUnit(unit);
  }

  /**
   * Starts the game.
   *
   * @param maxRounds the maximum number of turns the game can last
   */
  public void initGame(final int maxRounds) {
    this.maxRounds = maxRounds;
    winners = new ArrayList<>();
    this.roundNumber = 1;
    this.turnNumber = 0;
    playerInTurn = orderRound.get(turnNumber);
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
    return playerInTurn.getSelectedUnit();
  }

  /**
   * @return the inventory of the currently selected unit.
   */
  public List<IEquipableItem> getItems() {
    return getSelectedUnit().getItems();
  }





  // ==============================================================================
  //                          TACTICIAN METHODS
  // ==============================================================================

  /**
   * Adds an item to the selected unit
   *
   * @param item that will be added
   */
  public void addItem(IEquipableItem item) {
    if(roundNumber == 0) playerInTurn.addItem(item);
  }

  /**
   * Adds a unit to the tactician
   *
   * @param unit that will be added
   */
  private void addUnit(IUnit unit, Location location) {
    if(roundNumber == 0 && location.isEmpty()){
      playerInTurn.addUnit(unit);
      unit.setLocation(location);
      selectUnit(unit);
    }
  }

  /**
   * Equips an item from the inventory to the currently selected unit.
   * @param index the location of the item in the inventory.
   */
  public void equipItem(int index) {
    IEquipableItem item = getItems().get(index);
    playerInTurn.equipItem(item);
  }

  /**
   * Equips a determined item
   * @param name of the item that will be equipped
   */
  public void equipItemByName(String name){
    playerInTurn.equipItem(playerInTurn.getItemByName(name));
  }

  /**
   * @return the equipped item by the selected unit
   */
  public IEquipableItem getEquippedItem(){
    return playerInTurn.getEquippedItem();
  }

  /**
   * Obtains an item from inventory by its name
   * @param name of the item
   * @return the item
   */
  public IEquipableItem getItemByName(String name){
    return playerInTurn.getItemByName(name);
  }

  /**
   * @return the current selected item
   */
  public IEquipableItem getSelectedItem(){
    return playerInTurn.getSelectedItem();
  }

  /**
   * @return a list with the player-in-turn's units
   */
  public List<IUnit> getUnits(){
    return playerInTurn.getUnits();
  }

  /**
   * Gives the selected item to a target unit.
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void giveItemTo(int x, int y) {
    IUnit target = getGameMap().getCell(x, y).getUnit();
    playerInTurn.giveItem(target, playerInTurn.getSelectedItem());
  }

  /**
   * Moves the selected unit if it hasn't moved in the turn yet
   * @param x row
   * @param y column
   */
  public void moveTo(int x, int y){
    playerInTurn.moveUnitTo(x,y);
  }

  /**
   * Selects an item from the selected unit's inventory.
   * @param index the location of the item in the inventory.
   */
  public void selectItem(int index) {
    playerInTurn.selectItem(getItems().get(index));
  }

  /**
   * Selects an item from the selected unit's inventory.
   * @param itemName the name of the item.
   */
  public void selectItemByName(String itemName) {
    IEquipableItem item = getItemByName(itemName);
    if(item!=null) playerInTurn.selectItem(item);
  }

  /**
   * Selects the last added Unit in the Units List
   */
  public void selectLastAddedUnit(){
    selectUnit(playerInTurn.getUnitByIndex(playerInTurn.getUnits().size()-1));
  }

  /**
   * Selects a unit in the game map
   *
   * @param x horizontal position of the unit
   * @param y vertical position of the unit
   */
  public void selectUnitIn(int x, int y) {
    Location loc = getGameMap().getCell(x, y);
    selectUnit(loc.getUnit());
  }

  /**
   * Sets the location by coordinates to the selected unit
   * @param x row
   * @param y column
   */
  public void setLocation(int x, int y){
    playerInTurn.setUnitLocation(x,y);
  }

  /**
   * Uses the equipped item on a target
   *
   * @param x horizontal position of the target
   * @param y vertical position of the target
   */
  public void useItemOn(int x, int y) {
    if(roundNumber!=0){
      Location loc = getGameMap().getCell(x, y);
      IUnit target = loc.getUnit();
      playerInTurn.useItemOn(target);
    }
  }





  // ==============================================================================
  //                          UNITS FACTORY METHODS
  // ==============================================================================

  /**
   * Sets the factory for units
   * @param factory to be set
   */
  private void setUnitsFactory(IUnitsFactory factory){
    unitsFactory = factory;
  }

  /**
   * Sets the units factory as an Alpaca Factory
   */
  public void setAlpacaFactory(){
    setUnitsFactory(new AlpacaFactory());
  }

  /**
   * Sets the units factory as an Archer Factory
   */
  public void setArcherFactory(){
    setUnitsFactory(new ArcherFactory());
  }

  /**
   * Sets the units factory as a Cleric Factory
   */
  public void setClericFactory(){
    setUnitsFactory(new ClericFactory());
  }

  /**
   * Sets the units factory as a Fighter Factory
   */
  public void setFighterFactory(){
    setUnitsFactory(new FighterFactory());
  }

  /**
   * Sets the units factory as a Hero Factory
   */
  public void setHeroFactory(){
    setUnitsFactory(new HeroFactory());
  }

  /**
   * Sets the units factory as a Sorcerer Factory
   */
  public void setSorcererFactory(){
    setUnitsFactory(new SorcererFactory());
  }

  /**
   * Sets the units factory as a SwordMaster Factory
   */
  public void setSwordMasterFactory(){
    setUnitsFactory(new SwordMasterFactory());
  }

  /**
   * Adds a completely configurable new unit
   * @param hp hit points of the unit
   * @param movement how much it can move
   */
  public void addNewUnit(int hp, int movement, int x, int y) {
    addUnit(unitsFactory.createUnit(hp, movement, new InvalidLocation(), null), map.getCell(x,y));
  }

  /**
   * Adds a new generic unit, a generic unit has:
   *  - 100 hit points
   *  - 3 movement
   */
  public void addGenericUnit(int x, int y) {
    addUnit(unitsFactory.createGenericUnit(), map.getCell(x,y));
  }

  /**
   * Adds a new tank unit, a tank unit has:
   *  - 200 hit points
   *  - 1 movement
   */
  public void addTankUnit(int x, int y) {
    addUnit(unitsFactory.createTankUnit(), map.getCell(x,y));
  }

  /**
   * Adds a new tank unit, a fast unit has:
   *  - 70 hit points
   *  - 5 movement
   */
  public void addFastUnit(int x, int y){
    addUnit(unitsFactory.createFastUnit(), map.getCell(x,y));
  }





  // ==============================================================================
  //                          ITEMS FACTORY METHODS
  // ==============================================================================

  /**
   * Sets the factory for items
   * @param factory to be set
   */
  private void setItemsFactory(IItemsFactory factory){
    itemsFactory = factory;
  }

  /**
   * Sets the items factory as an Axe Factory
   */
  public void setAxeFactory(){
    setItemsFactory(new AxeFactory());
  }

  /**
   * Sets the items factory as a Bow Factory
   */
  public void setBowFactory(){
    setItemsFactory(new BowFactory());
  }

  /**
   * Sets the items factory as a Darkness spell book Factory
   */
  public void setDarknessFactory(){
    setItemsFactory(new DarknessFactory());
  }

  /**
   * Sets the items factory as a Light spell book Factory
   */
  public void setLightFactory(){
    setItemsFactory(new LightFactory());
  }

  /**
   * Sets the items factory as a Spirit spell book Factory
   */
  public void setSpiritFactory(){
    setItemsFactory(new SpiritFactory());
  }

  /**
   * Sets the items factory as a Staff Factory
   */
  public void setStaffFactory(){
    setItemsFactory(new StaffFactory());
  }

  /**
   * Sets the items factory as a Spear Factory
   */
  public void setSpearFactory(){
    setItemsFactory(new SpearFactory());
  }

  /**
   * Sets the items factory as a Sword Factory
   */
  public void setSwordFactory(){
    setItemsFactory(new SwordFactory());
  }

  /**
   * Adds a completely configurable new item to the selected unit
   * @param name of the item
   * @param power of the item
   * @param minRange of the item
   * @param maxRange of the item
   */
  public void addNewItem(String name, int power, int minRange, int maxRange){
    addItem(itemsFactory.create(name,power,minRange,maxRange));
  }

  /**
   * Adds a generic item to the selected unit, a generic item has:
   *  - 30 power
   *  - 1 minRange
   *  - 5 maxRange
   *  (Exception are bows, with 25 power, 2 minRange, 8 maxRange)
   * @param name of the item
   */
  public void addGenericItem(String name){
    addItem(itemsFactory.createGenericItem(name));
  }

  /**
   * Adds a powerful item to the selected unit, a powerful item has:
   *  - 50 power
   *  - 1 minRange
   *  - 3 maxRange
   *  (Exception are bows, with 40 power, 2 minRange, 6 maxRange)
   * @param name of the item
   */
  public void addPowerfulItem(String name){
    addItem(itemsFactory.createPowerfulItem(name));
  }

  /**
   * Adds a long-distance item to the selected unit, a long-distance item has:
   *  - 10 power
   *  - 1 minRange
   *  - 10 maxRange
   *  (Exception are bows, with 10 power, 2 minRange, 10 maxRange)
   * @param name of the item
   */
  public void addLongDistanceItem(String name){
    addItem(itemsFactory.createLongDistanceItem(name));
  }
}
