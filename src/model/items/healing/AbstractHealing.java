package model.items.healing;

import model.items.AbstractItem;
import model.items.IEquipableItem;

/**
 * Abstract class that defines some common information and behaviour between healing items.
 * Healing items can only be used by Clerics (for now)
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public class AbstractHealing extends AbstractItem {
    /**
     * Constructor for a default healing item.
     *
     * @param name
     *     the name of the item
     * @param power
     *     the power of the item (amount of healing)
     * @param minRange
     *     the minimum range of the item
     * @param maxRange
     *     the maximum range of the item
     */
    public AbstractHealing(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public boolean isStrongAgainst(IEquipableItem item) {
        return false;
    }

    @Override
    public double getEffectAgainst(IEquipableItem item){
        return getPower();
    }
}
