package model.items.spellbooks;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.weapons.AbstractWeapon;
import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between spells books.
 * Spells books can only be used by Sorcerers
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public abstract class AbstractBook extends AbstractItem {

    /**
     * Constructor for a default spells book.
     *
     * @param name
     *     the name of the book
     * @param power
     *     the power of the book (amount of damage)
     * @param minRange
     *     the minimum range of the item
     * @param maxRange
     *     the maximum range of the item
     */
    public AbstractBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public boolean isStrongAgainst(IEquipableItem item){
        return item instanceof AbstractWeapon;
    }

}
