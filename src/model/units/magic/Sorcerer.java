package model.units.magic;

import model.items.IEquipableItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.map.Location;
import model.units.AbstractMagic;
import model.units.IUnit;

/**
 * This class represents a sorcerer type unit. A sorcerer can only use magic books.
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public class Sorcerer extends AbstractMagic {

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
        if (item instanceof Darkness || item instanceof Light || item instanceof Spirit) {
            equippedItem = item;
        }
    }

    @Override
    public void attack(IUnit target) {
        target.receiveMagicalAttack(this);
    }


}
