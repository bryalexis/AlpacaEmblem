package model.items.spellbooks;

import model.items.AbstractItem;

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
    public void takeInPhysicalAttack(int power) {
        getOwner().modifyCurrentHitPoints(- power*1.5);
    }

    @Override
    public void takeInAxeAttack(int power) {
        takeInPhysicalAttack(power);
    }

    @Override
    public void takeInSpearAttack(int power) {
        takeInPhysicalAttack(power);
    }

    @Override
    public void takeInSwordAttack(int power) {
        takeInPhysicalAttack(power);
    }
}
