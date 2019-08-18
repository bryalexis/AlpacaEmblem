package model.spellBooks;

import model.items.IEquipableItem;

/**
 * This class represents a <i>Darkness</i> book item.
 * <p>
 * Darkness is a spell book of darkness type.
 * Darkness spells are strong against spirit and weak against light.
 *
 * @author Bryan Ortiz P
 * @since 1.0
 */
public class Darkness extends AbstractBook {

    /**
     * Creates a new Darkness book item.
     *
     * @param name
     *     the name of the Darkness Book
     * @param power
     *     the base damage of the Darkness Book
     * @param minRange
     *     the minimum range of the spells
     * @param maxRange
     *     the maximum range of the spells
     */
    public Darkness(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public boolean isStrongAgainst(IEquipableItem item){
        return super.isStrongAgainst(item) || item instanceof Spirit;
    }

    @Override
    public boolean isWeakAgainst(IEquipableItem item){
        return item instanceof Light;
    }
}
