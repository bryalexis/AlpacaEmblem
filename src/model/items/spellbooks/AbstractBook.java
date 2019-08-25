package model.items.spellbooks;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;

/**
 * Abstract class that defines some common information and behaviour between spells books.
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
public abstract class AbstractBook extends AbstractItem implements ISpellsBook {

    /**
     * Constructor for a default spells book.
     *
     * @param name
     *     the name of the book
     * @param power
     *     the power of the book
     * @param minRange
     *     the minimum range of the book
     * @param maxRange
     *     the maximum range of the book
     */
    AbstractBook(final String name, final int power, final int minRange, final int maxRange) {
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
