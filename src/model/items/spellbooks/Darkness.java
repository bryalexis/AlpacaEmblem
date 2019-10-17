package model.items.spellbooks;

import model.units.IUnit;

/**
 * This class represents a <i>Darkness</i> book item.
 * <p>
 * Darkness is a spell book of darkness type.
 * Darkness spells are strong against spirit and weak against light.
 *
 * @author Bryan Ortiz P
 * @since 1.1
 * @version 2.2
 */
public class Darkness extends AbstractBook{

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
    public void equipTo(IUnit unit) {
        unit.equipDarknessBook(this);
        setOwner(unit);
    }

    @Override
    public void takeInDarknessSpell(Darkness spell){
        takeInNormalAttack(spell.getPower());
    }

    @Override
    public void takeInSpiritSpell(Spirit spell){
        takeInWeakAttack(spell.getPower());
    }

    @Override
    public void takeInLightSpell(Light spell){
        takeInStrongAttack(spell.getPower());
    }

    @Override
    public void useOn(IUnit target) {
        target.getEquippedItem().takeInDarknessSpell(this);
    }


}
