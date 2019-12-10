package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.factory.FieldFactory;
import model.factory.items.AxeFactory;
import model.factory.items.SpearFactory;
import model.factory.units.*;
import model.items.IEquipableItem;
import model.items.nullitem.NullItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.map.Field;
import model.units.IUnit;
import model.units.carriers.Alpaca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.CompositeName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 * @version 2.4
 */
class GameControllerTest {

  private GameController controller;
  private long randomSeed;
  private List<String> testTacticians;

  @BeforeEach
  void setUp() {
    // A random seed is generated to have randomness in tests.
    controller = new GameController(4, 7);
    randomSeed = controller.getGameMap().getSeed();
    testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
  }

  /**
   * Tests the creation of the tacticians
   */
  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  /**
   * Tests that all the cells in the map are connected in some way.
   * Additionally, it tests that the map is replicable with the same seed.
   */
  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(7, gameMap.getSize()); // getSize deben definirlo
    assertTrue(controller.getGameMap().isConnected());
    FieldFactory ff = new FieldFactory();
    Field testMap = ff.createMap(randomSeed, 7,false); // map with the same seed
    assertTrue(testMap.isConnected());
    assertEquals(testMap, gameMap);
  }

  /**
   * Tests the owner of the turns in the game
   */
  @Test
  void getTurnOwner() {
    controller.initGame(2);
    Random random = new Random();
    random.setSeed(controller.getSeedPlayerSelection());
    List<Integer> lista = new ArrayList<>();
    assertEquals(getPlayer(random, lista, 1).getName(), controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    // We save a reference to the last player on this round
    String lastOfFirstRound = controller.getTurnOwner().getName();
    controller.endTurn();
    // Round 2
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    assertNotEquals(controller.getTurnOwner().getName(), lastOfFirstRound);
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
  }

  /**
   * Tests the number of the rounds
   */
  @Test
  void getRoundNumber() {
    controller.initGame(10);
    for (int i = 1; i <= 10; i++) {
      assertEquals(i, controller.getRoundNumber());
      for (int j = 0; j < 4; j++) {
        controller.endTurn();
      }
    }
  }

  /**
   * Tests the maximum rounds per game
   */
  @Test
  void getMaxRounds() {
    Random randomTurnSequence = new Random();
    IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
      controller.initGame(nextInt);
      System.out.println(nextInt);
      assertEquals(nextInt, controller.getMaxRounds());
      System.out.println(nextInt);
    });
    controller.initEndlessGame();
    assertEquals(-1, controller.getMaxRounds());
  }

  /**
   * Tests the end of turns functionality
   */
  @Test
  void endTurn() {
    Tactician firstPlayer = controller.getTurnOwner();
    controller.initGame(1);
    // Again, to determine the order of the players a seed must be used
    Random r = new Random();
    r.setSeed(controller.getSeedPlayerSelection());
    Tactician secondPlayer = getPlayer(r, new ArrayList<>(), 2);
    assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

    controller.endTurn();
    assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
    assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
    controller.endTurn();
    controller.endTurn();
    controller.endTurn();
    assertNull(controller.getTurnOwner());
  }

  /**
   * This method replies the way as the turns are set on the Controller
   * @param r the replicable random
   * @param turns the list with the players that have been selected
   * @param pos the position since this selection
   * @return a player that will have the same name as the player in turn
   */
  private Tactician getPlayer(Random r, List<Integer> turns, int pos){
    int last = -1;
    if(turns.size()==4){
      last = turns.get(3);
      turns.remove(3);
      turns.remove(2);
      turns.remove(1);
      turns.remove(0);
    }
    int k=0, n=0, limit = turns.size()+pos;
    while(turns.size()!=limit){
      k = r.nextInt();
      n = Math.abs(k)%controller.getTacticians().size();
      if( !turns.contains(n) && n!=last) turns.add(n);
    }
    return new Tactician("Player "+turns.get(turns.size()-1), controller.getGameMap());
  }

  /**
   * Tests behaviors when a Tactician is deleted from the game, how
   * it affects to the turns and the game development in general.
   */
  @Test
  void removeTactician() {
    assertEquals(4, controller.getTacticians().size());
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 0");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians().forEach(tactician -> assertNotEquals("Player 0", tactician));
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

    controller.removeTactician("Player 5");
    assertEquals(3, controller.getTacticians().size());
    controller.getTacticians()
            .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
  }

  /**
   * Tests the conditions to win the game.
   *
   * In the first case, a game with 2 rounds is set
   * and at the end all the Tacticians are the winners
   * 'cause no one lost the game.
   *
   * In the second case, is set a game with infinite rounds,
   * but the players will be deleted one by one until
   *
   * In the third case, the winner is who have more units.
   */
  @Test
  void getWinners() {
    controller.initGame(2);
    IntStream.range(0, 8).forEach(i -> controller.endTurn());
    assertEquals(4, controller.getWinners().size());
    controller.getWinners()
            .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

    controller.initGame(2);
    IntStream.range(0, 4).forEach(i -> controller.endTurn());
    assertEquals(0,controller.getWinners().size());
    controller.removeTactician("Player 0");
    controller.removeTactician("Player 2");
    IntStream.range(0, 2).forEach(i -> controller.endTurn());
    List<String> winners = controller.getWinners();
    assertEquals(2, winners.size());
    assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

    controller.initEndlessGame();
    for (int i = 0; i < 3; i++) {
      assertEquals(0,controller.getWinners().size());
      controller.removeTactician("Player " + i);
    }
    assertTrue(List.of("Player 3").containsAll(controller.getWinners()));

    controller = new GameController(3, 4);
    randomSeed = controller.getGameMap().getSeed();

    // Player 1
    controller.setArcherFactory();
    controller.addGenericUnit(0,0);
    controller.selectLastAddedUnit();
    controller.endTurn();

    // Player 2
    String winner = controller.getTurnOwner().getName();
    controller.setSwordMasterFactory();
    controller.addGenericUnit(0,1);
    controller.setArcherFactory();
    controller.addGenericUnit(0,2);

    controller.initGame(1);
    for(int i=0; i<3; i++){
      controller.endTurn();
    }
    assertEquals(1, controller.getWinners().size());
    assertTrue(controller.getWinners().contains(winner));

  }

  /**
   * Tests the functionality of selecting units
   */
  @Test
  void getSelectedUnit() {
    Field map = controller.getGameMap();
    Location l = map.getCell(0,0);
    IUnit alpaca = new Alpaca(50,2, l,null);
    assertEquals(l, alpaca.getLocation());
    assertEquals(alpaca, l.getUnit());
    assertEquals(l, map.getCell(0, 0));
    controller.getTurnOwner().selectUnit(alpaca);
    assertEquals(alpaca, controller.getSelectedUnit());
    assertEquals(alpaca, controller.getTurnOwner().getSelectedUnit());
  }

  /**
   * Tests the functionality of select items on determined position of the map
   * using its coordinates
   */
  @Test
  void selectUnitIn() {
    Field map = controller.getGameMap();
    Location l = map.getCell(0,0);
    IUnit alpaca = new Alpaca(50,2, l,null);
    assertEquals(l, alpaca.getLocation());
    assertEquals(alpaca, l.getUnit());
    assertEquals(l, map.getCell(0, 0));
    controller.selectUnitIn(0, 0);
    assertEquals(alpaca, controller.getSelectedUnit());
    assertEquals(alpaca, controller.getTurnOwner().getSelectedUnit());
  }

  /**
   * Tests the method to get the items of the selected unit by the Tactician
   */
  @Test
  void getItems() {

    controller.setArcherFactory();
    controller.addGenericUnit(0,0);

    IUnit unit = controller.getTurnOwner().getSelectedUnit();
    assertEquals(unit.getItems(), controller.getItems());

    controller.setBowFactory();
    controller.addGenericItem("cosa para disparar flechas");
    controller.setSwordFactory();
    controller.addLongDistanceItem("Espada de dave el barbaro");

    assertEquals(unit.getItems(), controller.getItems());
    assertEquals(2, controller.getItems().size());
    assertEquals(2, unit.getItems().size());
  }

  /**
   * Tests the equipment of an item
   */
  @Test
  void equipItem() {
    controller.setArcherFactory();
    controller.addGenericUnit(0,0);
    assertEquals(NullItem.class, controller.getTurnOwner().getSelectedUnit().getEquippedItem().getClass());

    controller.setBowFactory();
    controller.addGenericItem("uwu");
    controller.equipItem(0);
    IEquipableItem bow = controller.getItemByName("uwu");

    assertEquals(bow, controller.getTurnOwner().getSelectedUnit().getEquippedItem());
  }

  /**
   * Tests the uses of items
   */
  @Test
  void useItemOn() {
    controller = new GameController(4, 3);
    randomSeed = controller.getGameMap().getSeed();

    Random random = new Random();
    random.setSeed(controller.getSeedPlayerSelection());
    List<Integer> lista = new ArrayList<>();

    controller.setArcherFactory();
    controller.addTankUnit(0,0);
    controller.setBowFactory();
    controller.addPowerfulItem("awa");
    controller.equipItemByName("awa");

    Tactician player1 = controller.getTurnOwner();
    assertEquals(player1.getSelectedUnit(), controller.getSelectedUnit());
    assertEquals(player1.getItems(), controller.getItems());
    assertEquals(player1.getEquippedItem(), controller.getEquippedItem());

    Tactician player2 = getPlayer(random, lista, 2);

    SorcererFactory sorcererFactory = new SorcererFactory();
    player2.addUnit(sorcererFactory.createGenericUnit());
    player2.selectUnitByIndex(0);
    player2.setUnitLocation(0,2);

    controller.initGame(1);
    List <IUnit> units = player2.getUnits();
    controller.useItemOn(0,2);
    double expected = units.get(0).getMaxHitPoints() - player1.getEquippedItem().getPower();
    double current = units.get(0).getCurrentHitPoints();
    assertEquals(expected, current);

    // The controller should move the unit here
    controller.moveTo(0,1);
    controller.useItemOn(0,2);

    expected = current;
    current = player2.getUnits().get(0).getCurrentHitPoints();
    assertEquals(expected, current); // Out of range
  }

  /**
   * Tests the selection of items
   */
  @Test
  void selectItem() {
    controller.setArcherFactory();
    controller.addGenericUnit(0,0);

    controller.setBowFactory();
    controller.addGenericItem("ewe");
    assertNull(controller.getSelectedItem());

    controller.selectItem(0);
    assertEquals(controller.getItemByName("ewe"), controller.getSelectedItem());
  }

  /**
   * Tests when a unit gives an item to another unit
   */
  @Test
  void giveItemTo() {

    controller = new GameController(4, 3);
    randomSeed = controller.getGameMap().getSeed();

    controller.setSwordMasterFactory();
    controller.addGenericUnit(0,0);
    controller.setSwordFactory();
    controller.addPowerfulItem("pium");

    controller.setHeroFactory();
    controller.addTankUnit(0,1);

    controller.setSorcererFactory();
    controller.addFastUnit(0,2);

    // The selected unit is the SwordMaster
    controller.selectUnitIn(0,0);
    controller.selectItemByName("pium");
    IEquipableItem item = controller.getSelectedItem();
    controller.giveItemTo(0, 1);

    List<IUnit> units = controller.getTurnOwner().getUnits();
    assertTrue(units.get(1).getItems().contains(item));
    assertFalse(units.get(0).getItems().contains(item));

    // The selected unit is the Hero
    controller.selectUnitIn(0,1);
    controller.selectItem(0);
    controller.giveItemTo(0, 2);
    assertTrue(units.get(2).getItems().contains(item));
    assertFalse(units.get(1).getItems().contains(item));

    // The selected unit is the Sorcerer
    controller.selectUnitIn(0,2);
    controller.selectItem(0);
    controller.giveItemTo(0, 0);
    assertTrue(units.get(2).getItems().contains(item));
    assertFalse(units.get(0).getItems().contains(item)); // Out of range

    // Give item o a unit of a different Tactician
    IUnit swordMasterTactician1 = controller.getUnits().get(0);
    assertEquals(0,swordMasterTactician1.getItems().size());
    controller.endTurn();

    controller.setHeroFactory();
    controller.addGenericUnit(1,0);
    controller.setSpearFactory();
    controller.addGenericItem("Lanzable");
    controller.selectItem(0);

    // The interchange is not done.
    assertEquals(1,controller.getItems().size());
    controller.giveItemTo(0,0);
    assertEquals(swordMasterTactician1, controller.getGameMap().getCell(0,0).getUnit());
    assertEquals(0,swordMasterTactician1.getItems().size());
    assertEquals(1,controller.getItems().size());

  }

  /**
   * Tests that when a hero die, the Tactician owner is deleted from the game
   */
  @Test
  void heroDeathTest(){
    controller = new GameController(4, 3);
    randomSeed = controller.getGameMap().getSeed();
    Field map = controller.getGameMap();

    Tactician firstPlayer = controller.getTacticians().get(0);
    Tactician anotherPlayer = controller.getTacticians().get(1);

    // The first player will have a Fighter selected
    FighterFactory fighterFactory = new FighterFactory();
    firstPlayer.addUnit(fighterFactory.createTankUnit());
    firstPlayer.setLastAddedLocation(map.getCell(0,0));
    firstPlayer.selectUnit(firstPlayer.getUnits().get(0));

    AxeFactory axeFactory = new AxeFactory();
    firstPlayer.addItem(axeFactory.create("Super Axe", 2000, 1,10));
    firstPlayer.equipItem(firstPlayer.getItemByIndex(0));

    // The second player will have a Hero (who is supposed to die eventually)
    HeroFactory heroFactory = new HeroFactory();
    anotherPlayer.addUnit(heroFactory.createFastUnit());
    anotherPlayer.setLastAddedLocation(map.getCell(0,1));
    anotherPlayer.selectUnit(anotherPlayer.getUnits().get(0));

    SpearFactory spearFactory = new SpearFactory();
    anotherPlayer.addItem(spearFactory.createGenericItem("Generic Spear"));
    anotherPlayer.equipItem(anotherPlayer.getItemByIndex(0));
    controller.selectUnitIn(0,0);

    firstPlayer.useItemOn(map.getCell(0,1).getUnit());
    assertEquals(3, controller.getTacticians().size());
    assertNull(map.getCell(0,1).getUnit());
    assertFalse(controller.getTacticians().contains(anotherPlayer));
  }

  /**
   * Tests that we can't move the same unit twice in the same turn
   */
  @Test
  void moveTheSameUnitInATurn(){
    controller = new GameController(4, 3);
    randomSeed = controller.getGameMap().getSeed();
    Field map = controller.getGameMap();

    controller.setAlpacaFactory();
    controller.addGenericUnit(0,0);

    controller.setArcherFactory();
    controller.addGenericUnit(0,2);

    controller.setFighterFactory();
    controller.addGenericUnit(2,2);

    List<IUnit> units = controller.getTurnOwner().getUnits();
    IUnit unit1 = units.get(0);
    IUnit unit2 = units.get(1);
    IUnit unit3 = units.get(2);

    controller.selectUnitIn(0,0);
    controller.moveTo(1,0);
    assertEquals(unit1,map.getCell(1,0).getUnit());
    assertNull(map.getCell(0,0).getUnit());
    controller.moveTo(2,0);
    assertEquals(unit1,map.getCell(1,0).getUnit());
    assertNull(map.getCell(2,0).getUnit());

    controller.selectUnitIn(0,2);
    controller.moveTo(0,1);
    assertEquals(unit2,map.getCell(0,1).getUnit());
    assertNull(map.getCell(0,2).getUnit());
    controller.moveTo(0,0);
    assertEquals(unit2,map.getCell(0,1).getUnit());
    assertNull(map.getCell(0,0).getUnit());

    controller.endTurn();
    // Move the unit from another player
    controller.selectUnitIn(2,2);
    controller.moveTo(1,2);
    assertEquals(unit3,map.getCell(2,2).getUnit());
    assertNull(map.getCell(1,2).getUnit());
  }

  /**
   * Tests the functionality of adding units by the controller factory to the player in turn
   */
  @Test
  void factoryTest(){
    // Player Wan
    controller.setAlpacaFactory();
    controller.addGenericUnit(0,0);

    controller.setSpiritFactory();
    controller.addPowerfulItem("Power Spiritito");
    controller.setSwordFactory();
    controller.addNewItem("Espadita", 100, 1,3);

    assertEquals(controller.getItems().size(), 2);
    assertTrue(controller.getItems().contains(controller.getItemByName("Power Spiritito")));
    assertTrue(controller.getItems().contains(controller.getItemByName("Espadita")));
    assertEquals(controller.getUnits().size(),1);

    controller.setArcherFactory();
    controller.addTankUnit(5,5);
    controller.setClericFactory();
    controller.addFastUnit(1,0);

    controller.setBowFactory();
    controller.addLongDistanceItem("arquito");
    controller.setStaffFactory();
    controller.addGenericItem("palito");
    controller.equipItemByName("palito");

    controller.addPowerfulItem("palito de repuesto");
    controller.addGenericItem("tercer palito");
    assertEquals(controller.getItems().size(),3);
    assertNull(controller.getItemByName("tercer palito"));
    assertEquals(controller.getUnits().size(),3);

    controller.endTurn();

    // SECOND PLAYER
    controller.setFighterFactory();
    controller.addNewUnit(1000,3,1,1);

    controller.setAxeFactory();
    controller.addGenericItem("super hachita");
    controller.equipItemByName("super hachita");
    controller.setLightFactory();
    controller.addPowerfulItem("brilloso");
    controller.setDarknessFactory();
    controller.addLongDistanceItem("roscuro");

    assertEquals(3,controller.getItems().size());
    assertEquals(1, controller.getUnits().size());

    controller.endTurn();

    // Player 3
    controller.setHeroFactory();
    controller.addGenericUnit(5,3);
    controller.setSorcererFactory();
    controller.addFastUnit(2,2);

    controller.setDarknessFactory();
    controller.addGenericItem("OSCURITO");
    controller.equipItemByName("OSCURITO");

    assertEquals(controller.getItems().size(),1);
    assertEquals(controller.getUnits().size(),2);

    controller.endTurn();

    // Player 4
    controller.setSwordMasterFactory();
    controller.addFastUnit(0,3);

    controller.setSwordFactory();
    controller.addPowerfulItem("espadito");
    controller.setSpearFactory();
    controller.addGenericItem("britney");

    assertEquals(2, controller.getItems().size());
    assertEquals(1, controller.getUnits().size());

    controller.endTurn();
    assertNull(controller.getTurnOwner());
    controller.endTurn();
    assertNull(controller.getTurnOwner());
  }

  /**
   * Tests that we can't move a unit that is not ours, and that we can't move a unit to
   * a used cell. Also it tests that we can't move a unit twice in a turn
   */
  @Test
  void moveToUnavailableCell(){
    controller = new GameController(2, 3);
    randomSeed = controller.getGameMap().getSeed();

    controller.setArcherFactory();
    controller.addGenericUnit(0,0);

    controller.endTurn();
    controller.setHeroFactory();
    controller.addTankUnit(0,0);
    assertEquals(0,controller.getUnits().size());
    controller.addTankUnit(0,1);
    assertEquals(controller.getSelectedUnit(), controller.getGameMap().getCell(0,1).getUnit());
    assertEquals(1,controller.getUnits().size());

    controller.endTurn();
    controller.initGame(2);
    // Selects a unit from another tactician
    controller.selectUnitIn(0,1);
    controller.moveTo(1,1);
    assertNull(controller.getGameMap().getCell(1,1).getUnit());
    assertEquals(controller.getGameMap().getCell(0,1), controller.getSelectedUnit().getLocation());

    // Selects it's own unit
    controller.selectUnitIn(0,0);

    controller.moveTo(0,1);
    // Cell is not empty, so it doesn't move
    assertEquals(controller.getGameMap().getCell(0,0), controller.getSelectedUnit().getLocation());

    controller.moveTo(1,0);
    // Now it moves
    assertEquals(controller.getGameMap().getCell(1,0), controller.getSelectedUnit().getLocation());

    controller.moveTo(1,1);
    // It can't move twice
    assertEquals(controller.getGameMap().getCell(1,0), controller.getSelectedUnit().getLocation());

    controller.endTurn();
    controller.endTurn();

    // It's turn again
    controller.moveTo(1,1);
    // Now it can moves 'cause it's another turn
    assertEquals(controller.getGameMap().getCell(1,1), controller.getSelectedUnit().getLocation());

  }

  /**
   * Tests that when a hero die in the turn of it's owner, the turn ends immediately
   * and the tactician is deleted
   */
  @Test
  void heroDieInMyTurn(){
    controller = new GameController(3, 3);
    randomSeed = controller.getGameMap().getSeed();

    Tactician player1 = controller.getTurnOwner();
    controller.setHeroFactory();
    controller.addNewUnit(5,20,0,0);
    controller.setSpearFactory();
    controller.addGenericItem("Britney");
    controller.equipItemByName("Britney");

    controller.endTurn();
    Tactician player2 = controller.getTurnOwner();
    controller.setFighterFactory();
    controller.addTankUnit(1,0);
    controller.setAxeFactory();
    controller.addPowerfulItem("SuperAxe");
    controller.equipItem(0);

    controller.initGame(1);
    assertEquals(player1, controller.getTurnOwner());
    controller.useItemOn(1,0); // The Hero Should die
    assertEquals(player2, controller.getTurnOwner());
    assertNull(controller.getGameMap().getCell(0,0).getUnit());
  }

  /**
   * Tests that we can't add units after the game inits.
   */
  @Test
  void addUnitAfterInitGame(){
    assertEquals(0, controller.getUnits().size());
    controller.setHeroFactory();
    controller.addGenericUnit(0,0);
    controller.setSwordFactory();
    controller.addGenericItem("item");
    assertEquals(1, controller.getUnits().size());
    assertEquals(1,controller.getItems().size());

    controller.initGame(1);
    controller.setArcherFactory();
    controller.addTankUnit(0,1);
    controller.setFighterFactory();
    controller.addGenericUnit(0,2);
    controller.selectUnitIn(0,0);
    controller.setSpearFactory();
    controller.addGenericItem("item2");
    assertEquals(1, controller.getUnits().size());
    assertEquals(1,controller.getItems().size());
  }

  /**
   * Tests that we can't use items before the game starts.
   */
  @Test
  void useItemBeforeGameInit(){
    controller = new GameController(2, 3);
    randomSeed = controller.getGameMap().getSeed();

    controller.setHeroFactory();
    controller.addGenericUnit(0,0);
    controller.setSpearFactory();
    controller.addGenericItem("item");
    controller.equipItemByName("item");
    controller.endTurn();

    controller.setSwordMasterFactory();
    controller.addFastUnit(0,1);
    controller.setSwordFactory();
    controller.addGenericItem("item2");
    controller.equipItemByName("item2");
    controller.useItemOn(0,0);

    assertEquals(controller.getTurnOwner().getMaxHP(), controller.getTurnOwner().getHP());
    IUnit unitAttacked = controller.getGameMap().getCell(0,0).getUnit();
    assertEquals(unitAttacked.getMaxHitPoints(), unitAttacked.getCurrentHitPoints());
  }

  /**
   * Test when a tactician is deleted.
   */
  @Test
  void deleteTacticianTest(){
    Tactician toDelete = controller.getTurnOwner();
    controller.setSwordMasterFactory();
    controller.addGenericUnit(0,0);
    controller.setHeroFactory();
    controller.addTankUnit(0,1);
    controller.deleteTactician(toDelete);

    Field map = controller.getGameMap();
    assertNull(map.getCell(0,0).getUnit());
    assertNull(map.getCell(0,1).getUnit());
    assertEquals(3,controller.getTacticians().size());

  }

}