package model.units.warriors;

import model.items.weapons.Axe;
import model.items.IEquipableItem;
import model.map.Location;
import model.tactician.Tactician;
import model.units.AbstractUnit;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends AbstractUnit {

  public Fighter(final int hitPoints, final int movement, final Location location, Tactician owner,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, owner, items);
  }

  @Override
  public void equipAxe(Axe axe) {
    if (getItems().contains(axe)){
      setEquippedItem(axe);
    }
  }

}
