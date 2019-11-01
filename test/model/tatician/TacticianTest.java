package model.tatician;

import model.factory.items.*;
import model.factory.units.*;
import model.map.Field;
import model.map.InvalidLocation;
import model.map.Location;
import model.tactician.Tactician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TacticianTest {

  // Map
  private Field map;
  private Tactician player;

  @BeforeEach
  public void setUp(){
    map = new Field();
    map.addCells(true, new Location(0,0), new Location(0,1),
            new Location(0,2), new Location(0,3), new Location(0,4),
            new Location(1,0), new Location(1,1), new Location(1,2),
            new Location(1,3), new Location(1,4), new Location(2,0),
            new Location(2,1), new Location(2,2), new Location(2,3),
            new Location(2,4), new Location(3,0), new Location(3,1),
            new Location(3,2), new Location(3,3), new Location(3,4),
            new Location(4,0), new Location(4,1), new Location(4,2),
            new Location(4,3), new Location(4,4));
  }

  @Test
  public void selfTest(){
    player = new Tactician("Super Slater",map);
    assertEquals("Super Slater", player.getName());
    assertEquals(map, player.getField());
    assertEquals(0, player.getUnits().size());
    player.setUnitsFactory(new AlpacaFactory());
    player.addGenericUnit();
    player.setLastAddedLocation(map.getCell(0,0));
    assertEquals(1, player.getUnits().size());
    player.selectUnit(player.getUnits().get(0));
    assertEquals(player.getUnits().get(0), player.getSelectedUnit());
    player.moveUnitTo(1,0);
    assertEquals(player.getSelectedUnit(), map.getCell(1,0).getUnit());

    player.unselectUnit();
    assertNull(player.getSelectedUnit());
    player.setName("Ultra Slater");
    assertEquals("Ultra Slater", player.getName());

  }

  @Test
  public void getUnitsTest(){
    player = new Tactician("Dave el Barbaro",map);
    player.setUnitsFactory(new HeroFactory());
    player.addGenericUnit();
    player.setLastAddedLocation(map.getCell(0,1));
    player.setItemsFactory(new SpearFactory());
    player.selectUnit(player.getUnits().get(0));
    player.addPowerfulItem("Lanza del metro");
    player.equipItem(player.getItemByIndex(0));
    assertEquals(player.getItemByIndex(0), player.getEquippedItem());
    assertEquals(player.getSelectedUnit().getEquippedItem(), player.getEquippedItem());
  }

  @Test
  public void unSelectTest(){
    player = new Tactician("Doggo",map);
    player.setUnitsFactory(new AlpacaFactory());
    player.addGenericUnit();
    player.setLastAddedLocation(map.getCell(0,0));
    player.selectUnit(player.getUnits().get(0));
    assertEquals(player.getUnits().get(0), player.getSelectedUnit());
    player.unselectUnit();
    assertNull(player.getSelectedUnit());
  }

  @Test
  public void addNewUnitAndItemsTest(){
    player = new Tactician("Doggo",map);
    player.setUnitsFactory(new AlpacaFactory());
    player.addNewUnit(100,3, map.getCell(0,0));
    player.addFastUnit();
    player.setLastAddedLocation(map.getCell(0,0));
    player.selectUnit(player.getUnits().get(0));

    assertEquals(player.getUnits().get(0), player.getSelectedUnit());
    assertEquals(player.getUnits().get(1).getLocation().getClass(), InvalidLocation.class);
    assertEquals(player.getUnits().get(0), map.getCell(0,0).getUnit());
    assertEquals(2, player.getUnits().size());


    player.setItemsFactory(new SwordFactory());
    player.addGenericItem("La HeZkal!Vur");
    player.setItemsFactory(new SpearFactory());
    player.addLongDistanceItem("Kevin");
    player.setItemsFactory(new DarknessFactory());
    player.addNewItem("Guia de vida de Bart Simpson",20,1,10);
    assertEquals(3 ,player.getItems().size());

    player.selectUnitByIndex(0);
    assertEquals(player.getSelectedUnit(), player.getUnits().get(0));
  }

  @Test
  public void addUnitInNonEmptyCellTest(){
    player = new Tactician("Edward Elric",map);
    player.setUnitsFactory(new AlpacaFactory());
    player.addGenericUnit();
    player.setLastAddedLocation(map.getCell(0,0));
    assertEquals( player.getUnits().get(0), map.getCell(0,0).getUnit() );

    player.addFastUnit();
    player.setLastAddedLocation(map.getCell(0,0));
    assertEquals(2,player.getUnits().size());
    assertEquals(InvalidLocation.class, player.getUnits().get(1).getLocation().getClass());
    assertEquals( player.getUnits().get(0),map.getCell(0,0).getUnit() );
  }

  @Test
  public void deadOfUnit(){
    Tactician player1 = new Tactician("Meliodas",map);
    player1.setUnitsFactory(new SwordMasterFactory());
    player1.addTankUnit();
    player1.setLastAddedLocation(map.getCell(0,0));
    player1.setItemsFactory(new SwordFactory());
    player1.selectUnitByIndex(0);
    player1.addNewItem("Lost Vaine", 10000, 1, 100); // OP
    player1.equipItem(player1.getItemByIndex(0));

    Tactician player2 = new Tactician("Rasputin",map);
    player2.setUnitsFactory(new SorcererFactory());
    player2.addGenericUnit();
    player2.setLastAddedLocation(map.getCell(1,0));
    player2.selectUnitByIndex(0);
    player2.setItemsFactory(new DarknessFactory());
    player2.addGenericItem("Libro de Alquimia");
    player2.equipItem(player2.getItemByIndex(0));

    player1.useItemOn(player2.getSelectedUnit());
    assertEquals(0,player2.getUnits().size());
  }

  @Test
  public void attackTest(){
    Tactician player1 = new Tactician("Brownie",map);

    // Cleric
    player1.setUnitsFactory(new ClericFactory());
    player1.addGenericUnit();
    player1.setLastAddedLocation(map.getCell(0,1));
    player1.selectUnitByIndex(0);
    player1.setItemsFactory(new StaffFactory());
    player1.addPowerfulItem("Palito Curandero");
    player1.equipItem(player1.getItemByIndex(0));

    // SwordMaster
    player1.setUnitsFactory(new SwordMasterFactory());
    player1.addTankUnit();
    player1.setLastAddedLocation(map.getCell(0,0));
    player1.selectUnitByIndex(1);
    player1.setItemsFactory(new SwordFactory());
    player1.addGenericItem("Espadita");
    player1.equipItem(player1.getItemByIndex(0));

    Tactician player2 = new Tactician("Rasputin",map);
    player2.setUnitsFactory(new SorcererFactory());
    player2.addGenericUnit();
    player2.setLastAddedLocation(map.getCell(1,0));
    player2.selectUnitByIndex(0);
    player2.setItemsFactory(new DarknessFactory());
    player2.addGenericItem("Libro de Alquimia");
    player2.equipItem(player2.getItemByIndex(0));

    player1.useItemOn(player2.getSelectedUnit());
    double expected = player2.getMaxHP() - player1.getPowerEquippedItem()*1.5;
    assertEquals(expected, player2.getHP());

    player1.useItemOn(player1.getUnitByIndex(0));
    assertEquals(player1.getMaxHPOfUnit(0), player1.getHPOfUnit(0));

    player1.selectUnitByIndex(0);
    player1.useItemOn(player2.getSelectedUnit());
    // Doesn't heal 'cause the tactician owner of the cleric is different to the owner
    // of the Sorcerer
    assertEquals(expected, player2.getHP());

    player1.getUnitByIndex(1).modifyCurrentHitPoints(-60);
    assertEquals(95, player1.getHPOfUnit(1));
    player1.useItemOn(player1.getUnitByIndex(1));
    expected = 95 + player1.getPowerEquippedItem();
    // On this case it should heal
    assertEquals(expected, player1.getHPOfUnit(1));
  }

  @Test
  public void permissionsOnSelectedUnitTest(){
    player = new Tactician("Jackie Chan", map);

    player.setUnitsFactory(new SorcererFactory());
    player.addTankUnit();
    player.setLastAddedLocation(map.getCell(0,0));
    player.setItemsFactory(new LightFactory());
    player.addPowerfulItem("Librito");

    player.setUnitsFactory(new FighterFactory());
    player.addGenericUnit();
    player.setLastAddedLocation(map.getCell(2,0));

    player.selectUnitByIndex(0);

    Tactician anotherOne = new Tactician("Bites the dust", map);
    anotherOne.setUnitsFactory(new SwordMasterFactory());
    anotherOne.addGenericUnit();
    anotherOne.setLastAddedLocation(map.getCell(0,1));

    anotherOne.selectUnit(player.getUnitByIndex(0));
    anotherOne.useItemOn(anotherOne.getUnitByIndex(0));

    assertEquals(anotherOne.getMaxHPOfUnit(0), anotherOne.getHPOfUnit(0));

    anotherOne.moveUnitTo(1,0);
    assertNull(map.getCell(1,1).getUnit());
    assertEquals(anotherOne.getSelectedUnit(), map.getCell(0,0).getUnit());

    assertEquals(0, anotherOne.getMovement());
    assertNull(anotherOne.getItems());
    assertEquals(0, anotherOne.getPowerEquippedItem());
    assertNull(anotherOne.getItemByIndex(0));
  }

}
