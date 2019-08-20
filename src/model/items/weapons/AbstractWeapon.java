package model.items.weapons;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.spellbooks.AbstractBook;

/**
 * Abstract class that defines some common information and behaviour between weapons.
 * Weapons can't be used by Sorcerers and Clerics
 *
 * @author Bryan Ortiz P
 * @since 1.0
 */
public abstract class AbstractWeapon extends AbstractItem {

    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name
     *     the name of the item
     * @param power
     *     the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange
     *     the minimum range of the item
     * @param maxRange
     *     the maximum range of the item
     */
    public AbstractWeapon(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public boolean isStrongAgainst(IEquipableItem item){
        return item instanceof AbstractBook;
    }

}
