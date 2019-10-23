package model.tatician;

import model.factory.IItemsFactory;
import model.factory.IUnitsFactory;
import model.factory.units.AlpacaFactory;
import model.items.healing.Staff;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Field;
import model.map.Location;
import model.tactician.Tactician;
import model.units.carriers.Alpaca;
import model.units.healers.Cleric;
import model.units.magic.Sorcerer;
import model.units.warriors.Archer;
import model.units.warriors.Fighter;
import model.units.warriors.Hero;
import model.units.warriors.SwordMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TacticianTest {

  // Units
  private Alpaca alpaca;
  private Archer archer;
  private Cleric cleric;
  private Hero hero;
  private Fighter fighter;
  private Sorcerer sorcerer;
  private SwordMaster swordMaster;

  // Items
  private Axe axe;
  private Bow bow;
  private Darkness darkness;
  private Light light;
  private Spear spear;
  private Spirit spirit;
  private Staff staff;
  private Sword sword;

  // Map
  private Field map;
  Tactician player;
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
    player.addGenericUnit(map.getCell(0,0));
    assertEquals(1, player.getUnits().size());
  }

}
