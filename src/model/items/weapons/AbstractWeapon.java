package model.items.weapons;

import model.items.AbstractItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.units.IUnit;

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
    public void throwSpell(IUnit target){
        // Weapons can't throw spells (fix this)
    }

    @Override
    public void takeInDarknessSpell(Darkness spell){
        takeInMagicAttack(spell.getPower());
    }

    @Override
    public void takeInSpiritSpell(Spirit spell){
        takeInMagicAttack(spell.getPower());
    }

    @Override
    public void takeInLightSpell(Light spell){
        takeInMagicAttack(spell.getPower());
    }

    public void takeInMagicAttack(int power){
        getOwner().modifyCurrentHitPoints(- power*1.5);
    }

    @Override
    public void takeInPhysicalAttack(int power){
        getOwner().modifyCurrentHitPoints(-power);
    }

}
