package model.tatician;

import model.factory.FieldFactory;
import model.factory.items.*;
import model.factory.units.*;
import model.items.IEquipableItem;
import model.items.spellbooks.Spirit;
import model.map.Field;
import model.map.InvalidLocation;
import model.tactician.Tactician;
import model.units.IUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TacticianTest {

  // Map
  private Field map;
  private Tactician player;

  // Units Factories
  private AlpacaFactory alpacaFactory;
  private ArcherFactory archerFactory;
  private ClericFactory clericFactory;
  private FighterFactory fighterFactory;
  private HeroFactory heroFactory;
  private SorcererFactory sorcererFactory;
  private SwordMasterFactory swordMasterFactory;

  // Items Factories
  private AxeFactory axeFactory;
  private BowFactory bowFactory;
  private DarknessFactory darknessFactory;
  private LightFactory lightFactory;
  private SpearFactory spearFactory;
  private SpiritFactory spiritFactory;
  private StaffFactory staffFactory;
  private SwordFactory swordFactory;

  @BeforeEach
  public void setUp(){
    FieldFactory fieldFactory = new FieldFactory();
    map = fieldFactory.createMap(0,5,true);
    setUnitsFactories();
    setItemsFactories();
  }

  private void setUnitsFactories(){
    alpacaFactory = new AlpacaFactory();
    archerFactory = new ArcherFactory();
    clericFactory = new ClericFactory();
    fighterFactory = new FighterFactory();
    heroFactory = new HeroFactory();
    sorcererFactory = new SorcererFactory();
    swordMasterFactory = new SwordMasterFactory();
  }

  private void setItemsFactories(){
    axeFactory = new AxeFactory();
    bowFactory = new BowFactory();
    darknessFactory = new DarknessFactory();
    lightFactory = new LightFactory();
    spearFactory = new SpearFactory();
    spiritFactory = new SpiritFactory();
    staffFactory = new StaffFactory();
    swordFactory = new SwordFactory();
  }

  @Test
  public void selfTest(){
    // Creating Player
    player = new Tactician("Super Slater",map);
    assertEquals("Super Slater", player.getName());
    assertEquals(map, player.getField());
    assertEquals(0, player.getUnits().size());

    // Add Unit
    IUnit alpaca = alpacaFactory.createGenericUnit();
    player.addUnit(alpaca);
    player.setLastAddedLocation(map.getCell(0,0));
    assertEquals(1, player.getUnits().size());

    // Select Unit
    player.selectUnit(alpaca);
    assertEquals(alpaca, player.getSelectedUnit());
    assertEquals(player.getUnits().get(0), player.getSelectedUnit());

    // Move Selected Unit
    player.moveUnitTo(1,0);
    assertEquals(player.getSelectedUnit(), map.getCell(1,0).getUnit());

    // Unselect Unit
    player.unselectUnit();
    assertNull(player.getSelectedUnit());

    // Changing Name
    player.setName("Ultra Slater");
    assertEquals("Ultra Slater", player.getName());

  }

  @Test
  public void getUnitsTest(){
    // Setting Player
    player = new Tactician("Dave el Barbaro",map);

    // Setting Units
    IUnit hero = heroFactory.createGenericUnit();
    player.addUnit(hero);
    player.setLastAddedLocation(map.getCell(0,1));
    player.selectUnit(player.getUnits().get(0));

    // Setting Items
    IEquipableItem spear = spearFactory.createPowerfulItem("Brittney");
    player.addItem(spear);

    // Equipping Items
    player.equipItem(spear);
    assertEquals(player.getItemByIndex(0), player.getEquippedItem());
    assertEquals(spear, player.getEquippedItem());
    assertEquals(player.getSelectedUnit().getEquippedItem(), player.getEquippedItem());
  }

  @Test
  public void unSelectTest(){
    player = new Tactician("Doggo",map);
    IUnit alpaca = alpacaFactory.createGenericUnit();
    player.addUnit(alpaca);
    player.setLastAddedLocation(map.getCell(0,0));
    player.selectUnit(player.getUnits().get(0));
    assertEquals(player.getUnits().get(0), player.getSelectedUnit());
    player.unselectUnit();
    assertNull(player.getSelectedUnit());
  }

  @Test
  public void addNewUnitAndItemsTest(){
    // Setting the player
    player = new Tactician("Doggo",map);
    IUnit alpaca1 = alpacaFactory.createUnit(100,3, map.getCell(0,0), null);
    player.addUnit(alpaca1);
    IUnit alpaca2 = alpacaFactory.createFastUnit();
    player.addUnit(alpaca2);
    player.setLastAddedLocation(map.getCell(0,0));
    player.selectUnit(alpaca1);

    // Selected Unit Asserts
    assertEquals(alpaca1, player.getSelectedUnit());
    assertEquals(player.getUnits().get(0), player.getSelectedUnit());

    // Locations in map Asserts
    assertEquals(alpaca2.getLocation().getClass(), InvalidLocation.class);
    assertEquals(alpaca1, map.getCell(0,0).getUnit());

    // Units Added Assert
    assertEquals(2, player.getUnits().size());

    // Adding Items Test
    IEquipableItem sword = swordFactory.createGenericItem("La HeZkal!Vur");
    player.addItem(sword);
    IEquipableItem spear = spearFactory.createLongDistanceItem("Kevin");
    player.addItem(spear);
    IEquipableItem darkn = darknessFactory.create(
            "Guia de vida de Bart Simpson",20,1,10);
    player.addItem(darkn);
    assertEquals(3 ,player.getItems().size());

    player.selectUnitByIndex(0);
    assertEquals(player.getSelectedUnit(), player.getUnits().get(0));
  }

  @Test
  public void addUnitInNonEmptyCellTest(){
    player = new Tactician("Edward Elric",map);
    IUnit alpaca = alpacaFactory.createGenericUnit();
    player.addUnit(alpaca);
    player.setLastAddedLocation(map.getCell(0,0));
    assertEquals( player.getUnits().get(0), map.getCell(0,0).getUnit() );

    IUnit fastAlpaca = alpacaFactory.createFastUnit();
    player.addUnit(fastAlpaca);
    player.setLastAddedLocation(map.getCell(0,0));
    assertEquals(2,player.getUnits().size());
    assertEquals(InvalidLocation.class, player.getUnits().get(1).getLocation().getClass());
    assertEquals( player.getUnits().get(0),map.getCell(0,0).getUnit() );
  }

  @Test
  public void deadOfUnit(){
    Tactician player1 = new Tactician("Meliodas",map);

    // Adds the unit
    IUnit sm = swordMasterFactory.createTankUnit();
    player1.addUnit(sm);
    player1.setLastAddedLocation(map.getCell(0,0));
    player1.selectUnit(sm);

    // Adds the item
    IEquipableItem sword = swordFactory.create("Lost Vaine", 10000, 1, 100);
    player1.addItem(sword); // OP
    player1.equipItem(sword);

    // The enemy
    Tactician player2 = new Tactician("Rasputin",map);

    // Enemy's Unit
    IUnit sorcerer = sorcererFactory.createGenericUnit();
    player2.addUnit(sorcerer);
    player2.setLastAddedLocation(map.getCell(1,0));
    player2.selectUnit(sorcerer);

    // Enemy's Item
    IEquipableItem dark = darknessFactory.createGenericItem("Libro de Alquimia");
    player2.addItem(dark);
    player2.equipItem(dark);

    // The enemy's unit is dead now, so it disappears from the world :(
    player1.useItemOn(player2.getSelectedUnit());
    assertEquals(0, player2.getUnits().size());
  }

  @Test
  public void attackTest(){
    // Player 1
    Tactician player1 = new Tactician("Brownie",map);

    // Cleric - P1
    IUnit cleric = clericFactory.createGenericUnit();
    player1.addUnit(cleric);
    player1.setLastAddedLocation(map.getCell(0,1));
    player1.selectUnit(cleric);
    IEquipableItem staff = staffFactory.createPowerfulItem("Palito Curandero");
    player1.addItem(staff);
    player1.equipItem(staff);

    // SwordMaster - P1
    IUnit sm = swordMasterFactory.createTankUnit();
    player1.addUnit(sm);
    player1.setLastAddedLocation(map.getCell(0,0));
    player1.selectUnit(sm);
    IEquipableItem sword = swordFactory.createGenericItem("Espadita");
    player1.addItem(sword);
    player1.equipItem(sword);

    // Player 2
    Tactician player2 = new Tactician("Rasputin",map);

    // Sorcerer - P2
    IUnit sorcerer = sorcererFactory.createGenericUnit();
    player2.addUnit(sorcerer);
    player2.setLastAddedLocation(map.getCell(1,0));
    player2.selectUnit(sorcerer);
    IEquipableItem spirit = spiritFactory.createGenericItem("Libro de Alquimia");
    player2.addItem(spirit);
    player2.equipItem(spirit);

    // Attack Unit of another Tactician (must be done)
    player1.useItemOn(player2.getSelectedUnit());
    double expected = player2.getMaxHP() - player1.getPowerEquippedItem()*1.5;
    assertEquals(expected, player2.getHP());

    // Attack Unit of same Tactician (it shouldn't happen)
    player1.useItemOn(player1.getUnitByIndex(0));
    assertEquals(player1.getMaxHPOfUnit(0), player1.getHPOfUnit(0));

    // Heal Unit of another Tactician (it shouldn´t happen)
    player1.selectUnit(sorcerer);
    player1.useItemOn(player2.getSelectedUnit());
    assertEquals(expected, player2.getHP());

    // Heal Unit of same Tactician
    player1.getUnitByIndex(1).modifyCurrentHitPoints(-60);
    assertEquals(95, player1.getHPOfUnit(1));
    player1.useItemOn(player1.getUnitByIndex(1));
    expected = 95 + player1.getPowerEquippedItem();
    // On this case it should heal
    assertEquals(expected, player1.getHPOfUnit(1));
  }

  @Test
  public void permissionsOnSelectedUnitTest(){
    // Sets the player
    player = new Tactician("Jackie Chan", map);

    // Sorcerer
    IUnit sorcerer = sorcererFactory.createTankUnit();
    player.addUnit(sorcerer);
    player.setLastAddedLocation(map.getCell(0,0));
    IEquipableItem light = lightFactory.createPowerfulItem("Librito");
    player.addItem(light);

    // Fighter
    IUnit fighter = fighterFactory.createGenericUnit();
    player.addUnit(fighter);
    player.setLastAddedLocation(map.getCell(2,0));

    // Unit Selected by Player 1
    player.selectUnit(sorcerer);

    // Another Player
    Tactician anotherOne = new Tactician("Bites the dust", map);
    IUnit sm = swordMasterFactory.createGenericUnit();
    anotherOne.addUnit(sm);
    anotherOne.setLastAddedLocation(map.getCell(0,1));

    // Use a unit from another tactician (should do nothing)
    anotherOne.selectUnit(sorcerer);
    anotherOne.useItemOn(sm);
    assertEquals(anotherOne.getMaxHPOfUnit(0), anotherOne.getHPOfUnit(0));

    anotherOne.moveUnitTo(1,0);
    assertNull(map.getCell(1,1).getUnit());
    assertEquals(anotherOne.getSelectedUnit(), map.getCell(0,0).getUnit());

    assertEquals(0, anotherOne.getMovement());
    assertNull(anotherOne.getItems());
    assertEquals(0, anotherOne.getPowerEquippedItem());
    assertNull(anotherOne.getItemByIndex(0));
  }

  @Test
  public void itemMethodsTest(){
    player = new Tactician("Chefcito",map);
    IUnit sorcerer = sorcererFactory.createTankUnit();
    player.addUnit(sorcerer);
    player.selectUnit(sorcerer);

    IEquipableItem dark = darknessFactory.createGenericItem("AvaraKedabra");
    player.addItem(dark);
    IEquipableItem light = lightFactory.createGenericItem("Patronum");
    player.addItem(light);
    IEquipableItem spirit = spiritFactory.createPowerfulItem("Espiritito");
    player.addItem(spirit);
    player.setUnitLocation(0,0);

    player.equipItem(player.getItemByName("Espiritito"));
    assertEquals(player,player.getOwner());
    assertEquals(map.getCell(0,0), player.getLocation());

    IEquipableItem avarakedabra = player.getItemByIndex(0);
    assertEquals(avarakedabra, player.getItemByName("AvaraKedabra"));
    IEquipableItem patronum = player.getItemByIndex(1);
    assertEquals(patronum, player.getItemByName("Patronum"));
    IEquipableItem espiritito = player.getItemByIndex(2);
    assertEquals(espiritito, player.getItemByName("Espiritito"));

    assertEquals(espiritito, player.getEquippedItem());
    assertEquals(espiritito.getPower(), player.getPowerEquippedItem());
    assertEquals(30, player.getItemPowerByName("AvaraKedabra"));
    assertEquals(30, player.getItemPowerByName("Patronum"));
    assertEquals(50, player.getItemPowerByName("Espiritito"));

    assertEquals(player.getSelectedUnit() , player.getItemTacticianByName("AvaraKedabra"));
    assertEquals(player.getSelectedUnit() , player.getItemTacticianByName("Patronum"));
    assertEquals(player.getSelectedUnit() , player.getItemTacticianByName("Espiritito"));

    assertEquals(1, player.getItemMinRangeByName("AvaraKedabra"));
    assertEquals(5, player.getItemMaxRangeByName("AvaraKedabra"));
    assertEquals(1, player.getItemMinRangeByName("Patronum"));
    assertEquals(5, player.getItemMaxRangeByName("Patronum"));
    assertEquals(1, player.getItemMinRangeByName("Espiritito"));
    assertEquals(3, player.getItemMaxRangeByName("Espiritito"));

    Tactician anotherPlayer = new Tactician("Tramposo", map);
    anotherPlayer.selectUnit(player.getSelectedUnit());

    assertEquals(0, anotherPlayer.getItemMinRangeByName("AvaraKedabra"));
    assertEquals(0, anotherPlayer.getItemMaxRangeByName("AvaraKedabra"));
    assertEquals(0, anotherPlayer.getItemMinRangeByName("Patronum"));
    assertEquals(0, anotherPlayer.getItemMaxRangeByName("Patronum"));
    assertEquals(0, anotherPlayer.getItemMinRangeByName("Espiritito"));
    assertEquals(0, anotherPlayer.getItemMaxRangeByName("Espiritito"));

    assertNull(anotherPlayer.getItemTacticianByName("AvaraKedabra"));
    assertNull(anotherPlayer.getItemTacticianByName("Patronum"));
    assertNull(anotherPlayer.getItemTacticianByName("Espiritito"));

    assertEquals(0, anotherPlayer.getItemPowerByName("AvaraKedabra"));
    assertEquals(0, anotherPlayer.getItemPowerByName("Patronum"));
    assertEquals(0, anotherPlayer.getItemPowerByName("Espiritito"));
  }

}
