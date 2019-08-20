package model.units;

import model.items.spellbooks.AbstractBook;
import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a sorcerer type unit. A sorcerer can only use magic books.
 *
 * @author Bryan Ortiz P
 * @since 1.0
 */
public class Sorcerer extends AbstractUnit {

    /**
     * Creates a new Sorcerer.
     *
     * @param hitPoints
     *     the maximum amount of damage a unit can sustain
     * @param movement
     *     the number of panels a unit can move
     */
    public Sorcerer(final int hitPoints, final int movement, final Location location,
                  IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    /**
     * Sets the currently equipped item of this unit.
     *
     * @param item
     *     the item to equip
     */
    @Override
    public void equipItem(final IEquipableItem item) {
        if (item instanceof AbstractBook) {
            equippedItem = item;
        }
    }
}
