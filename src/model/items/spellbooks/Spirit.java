package model.items.spellbooks;

import model.items.AbstractItem;
import model.units.IUnit;

/**
 * This class represents a <i>Spirit</i> item.
 * <p>
 * Spirit is a spell book of spirit type.
 * Spirit spells are strong against light and weak against darkness.
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public class Spirit extends AbstractBook {

    /**
     * Creates a new Spirit book item.
     *
     * @param name
     *     the name of the Spirit Book
     * @param power
     *     the base damage of the Spirit Book
     * @param minRange
     *     the minimum range of the spells
     * @param maxRange
     *     the maximum range of the spells
     */
    public Spirit(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipSpiritBook(this);
        setOwner(unit);
    }

    @Override
    public void useOn(IUnit target) {
        target.getEquippedItem().takeInSpiritSpell(this);
    }

    @Override
    public void takeInDarknessSpell(Darkness spell){
        getOwner().modifyCurrentHitPoints(- spell.getPower()*1.5);
    }

    @Override
    public void takeInSpiritSpell(Spirit spell){
        getOwner().modifyCurrentHitPoints(- spell.getPower());
    }

    @Override
    public void takeInLightSpell(Light spell){
        double damage = -spell.getPower() + 20;
        getOwner().modifyCurrentHitPoints(Math.min(damage, 0));
    }
}
