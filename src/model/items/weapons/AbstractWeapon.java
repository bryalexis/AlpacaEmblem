package model.items.weapons;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;

/**
 * Abstract class that defines some common information and behaviour between physical weapons.
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 * @version 2.2
 */
public abstract class AbstractWeapon extends AbstractItem {

    /**
     * Constructor for a default physical weapon.
     *
     * @param name
     *     the name of the weapon
     * @param power
     *     the power of the weapon
     * @param minRange
     *     the minimum range of the weapon
     * @param maxRange
     *     the maximum range of the weapon
     */
    AbstractWeapon(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void takeInDarknessSpell(Darkness spell){
        takeInMagicalAttack(spell);
    }

    @Override
    public void takeInSpiritSpell(Spirit spell){
        takeInMagicalAttack(spell);
    }

    @Override
    public void takeInLightSpell(Light spell){
        takeInMagicalAttack(spell);
    }

    @Override
    public void takeInMagicalAttack(IEquipableItem item){
        takeInStrongAttack(item.getPower());
    }

    @Override
    public void takeInPhysicalAttack(IEquipableItem item){
        takeInNormalAttack(item.getPower());
    }

}
