package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.factory.FieldFactory;
import model.factory.IItemsFactory;
import model.factory.IUnitsFactory;
import model.factory.items.BowFactory;
import model.factory.items.SpearFactory;
import model.factory.units.AlpacaFactory;
import model.factory.units.ArcherFactory;
import model.factory.units.HeroFactory;
import model.factory.units.SorcererFactory;
import model.items.weapons.Bow;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import model.map.Field;
import model.units.IUnit;
import model.units.carriers.Alpaca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
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

  @Test
  void getTacticians() {
    List<Tactician> tacticians = controller.getTacticians();
    assertEquals(4, tacticians.size());
    for (int i = 0; i < tacticians.size(); i++) {
      assertEquals("Player " + i, tacticians.get(i).getName());
    }
  }

  @Test
  void getGameMap() {
    Field gameMap = controller.getGameMap();
    assertEquals(7, gameMap.getSize()); // getSize deben definirlo
    assertTrue(controller.getGameMap().isConnected());
    FieldFactory ff = new FieldFactory();
    Field testMap = ff.createMap(randomSeed, 7); // map with the same seed
    assertTrue(testMap.isConnected());
    assertEquals(testMap, gameMap);
  }

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
    controller.endTurn();
    // Round 2
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
    controller.endTurn();
    assertEquals(getPlayer(random,lista,1).getName(),controller.getTurnOwner().getName());
  }

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
  }

  @Test /* To Do */
  void getSelectedUnit() {
    Field map = controller.getGameMap();
    Location l = getValidLocation(1);
    IUnit alpaca = new Alpaca(50,2, l,null);
    assertEquals(l, alpaca.getLocation());
    assertEquals(alpaca, l.getUnit());
    assertEquals(l, map.getCell(l.getRow(), l.getColumn()));
    controller.selectUnitIn(l.getRow(), l.getColumn());
    assertEquals(alpaca, controller.getSelectedUnit());
  }

  @Test /* To Do */
  void selectUnitIn() {
    Field map = controller.getGameMap();
    Location l = map.getCell(0,0);
    IUnit alpaca = new Alpaca(50,2, l,null);
    assertEquals(l, alpaca.getLocation());
    assertEquals(alpaca, l.getUnit());
    assertEquals(l, map.getCell(0, 0));
    controller.selectUnitIn(0, 0);
    assertEquals(alpaca, controller.getSelectedUnit());
  }

  /**
   * Method that search in map a valid location
   * @return the -result- valid location that it will find
   */
  public Location getValidLocation(int result){
    for (int i = 0; i<controller.getGameMap().getSize(); i++){
      for (int j = 0; j<controller.getGameMap().getSize(); j++){
        if (controller.getGameMap().getCell(i,j).getClass() != InvalidLocation.class){
          result--;
          if(result==0) return controller.getGameMap().getCell(i,j);
        }
      }
    }
    return null;
  }

  @Test
  void getItems() {
    IUnitsFactory alpacaF = new AlpacaFactory();
    IUnitsFactory archerF = new ArcherFactory();
    Tactician player = controller.getTurnOwner();
    player.addUnit(alpacaF.createGenericUnit(getValidLocation(1),player));
    player.addUnit(archerF.createGenericUnit(getValidLocation(2),player));
    player.selectUnit(player.getUnits().get(0));

    IUnit unit = player.getSelectedUnit();
    assertEquals(unit.getItems(), controller.getItems());
  }

  @Test
  void equipItem() {
    Location l = getValidLocation(1);
    controller.getTurnOwner().setUnitsFactory(new ArcherFactory());
    controller.getTurnOwner().addGenericUnit(l);
    controller.getTurnOwner().selectUnit(controller.getTurnOwner().getUnits().get(0));

    //controller set selected unit with observer

    IItemsFactory bowF = new BowFactory();
    Bow bow = (Bow) bowF.createGenericItem("uwu");
    controller.getTurnOwner().addItem(bow);
    controller.equipItem(0);

    assertEquals(bow, controller.getTurnOwner().getSelectedUnit().getEquippedItem());
  }

  @Test
  void useItemOn() {
    controller = new GameController(4, 3);
    randomSeed = controller.getGameMap().getSeed();
    Field map = controller.getGameMap();

    Tactician player1 = controller.getTacticians().get(0);
    Location l1 = map.getCell(0,0);
    player1.setUnitsFactory(new ArcherFactory());
    player1.addTankUnit(l1);
    player1.selectUnitFromUnitsByIndex(0);
    player1.setItemsFactory(new BowFactory());
    player1.addPowerfulItem("uwu");
    player1.equipItem(player1.getItemInInventoryByIndex(0));
    assertEquals(player1.getSelectedUnit(), controller.getSelectedUnit());

    Location l2 = map.getCell(0,2);
    player1.addGenericUnit(l2);

    List <IUnit> units = player1.getUnits();
    controller.useItemOn(0,2);
    double expected = units.get(1).getMaxHitPoints() - player1.getEquippedItem().getPower();
    double current = units.get(1).getCurrentHitPoints();
    assertEquals(expected, current);

    // The controller should move the unit here
    player1.moveUnitTo(0,1);
    controller.useItemOn(0,2);

    expected = current;
    current = player1.getUnits().get(1).getCurrentHitPoints();
    assertEquals(expected, current); // Out of range
  }

  @Test
  void selectItem() {
    Location l = getValidLocation(1);
    controller.getTurnOwner().setUnitsFactory(new ArcherFactory());
    controller.getTurnOwner().addGenericUnit(l);
    controller.getTurnOwner().selectUnit(controller.getTurnOwner().getUnits().get(0));

    IItemsFactory bowF = new BowFactory();
    Bow bow = (Bow) bowF.createGenericItem("uwu");
    controller.getTurnOwner().addItem(bow);
    controller.selectItem(0);

    assertEquals(bow, controller.getSelectedItem());
  }

  @Test
  void giveItemTo() {

    controller = new GameController(4, 3);
    randomSeed = controller.getGameMap().getSeed();
    Field map = controller.getGameMap();

    Location l1 = map.getCell(0,0);
    Location l2 = map.getCell(0,1);
    Location l3 = map.getCell(0,2);

    controller.getTurnOwner().setUnitsFactory(new ArcherFactory());
    controller.getTurnOwner().addGenericUnit(l1);
    controller.getTurnOwner().setUnitsFactory(new HeroFactory());
    controller.getTurnOwner().addTankUnit(l2);
    controller.getTurnOwner().setUnitsFactory(new SorcererFactory());
    controller.getTurnOwner().addFastUnit(l3);

    // The selected unit is the Archer
    controller.getTurnOwner().selectUnit(controller.getTurnOwner().getUnits().get(0));

    IItemsFactory bowF = new BowFactory();
    Bow bow = (Bow) bowF.createGenericItem("uwu");
    controller.getTurnOwner().addItem(bow);

    controller.selectItem(0);
    controller.giveItemTo(0, 1);

    List<IUnit> units = controller.getTurnOwner().getUnits();
    assertTrue(units.get(1).getItems().contains(bow));
    assertFalse(units.get(0).getItems().contains(bow));

    // The selected unit is the Hero
    controller.getTurnOwner().selectUnit(controller.getTurnOwner().getUnits().get(1));
    controller.selectItem(0);
    controller.giveItemTo(0, 2);
    assertTrue(units.get(2).getItems().contains(bow));
    assertFalse(units.get(1).getItems().contains(bow));

    // The selected unit is the Sorcerer
    controller.getTurnOwner().selectUnit(controller.getTurnOwner().getUnits().get(2));
    controller.selectItem(0);
    controller.giveItemTo(0, 0);
    assertTrue(units.get(2).getItems().contains(bow));
    assertFalse(units.get(0).getItems().contains(bow)); // Out of range
  }
}