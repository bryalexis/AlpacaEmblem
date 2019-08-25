package model.items.spellbooks;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;

public abstract class AbstractBook extends AbstractItem {

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
    public AbstractBook(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void takeInPhysicalAttack(IEquipableItem item) {
        getOwner().modifyCurrentHitPoints(- item.getPower()*1.5);
    }

    @Override
    public void takeInMagicalAttack(IEquipableItem item) {
        getOwner().modifyCurrentHitPoints(- item.getPower());
    }

    @Override
    public void takeInAxeAttack(Axe axe) {
        takeInPhysicalAttack(axe);
    }

    @Override
    public void takeInSpearAttack(Spear spear) {
        takeInPhysicalAttack(spear);
    }

    @Override
    public void takeInSwordAttack(Sword sword) {
        takeInPhysicalAttack(sword);
    }
}
