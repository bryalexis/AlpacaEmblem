package model.units.warriors;

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
import model.tactician.Tactician;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {

  public SwordMaster(final int hitPoints, final int movement, final Location location, Tactician owner,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, owner, items);
  }

  @Override
  public void equipSword(Sword sword) {
    if (getItems().contains(sword)){
      setEquippedItem(sword);
    }
  }
  
}
