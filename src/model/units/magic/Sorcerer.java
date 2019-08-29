package model.units.magic;

import model.items.IEquipableItem;
import model.items.healing.Staff;
import model.items.spellbooks.ISpellsBook;
import model.map.Location;
import model.units.AbstractUnit;
import model.units.IUnit;

/**
 * This class represents a sorcerer type unit. A sorcerer can only use spells books.
 *
 * @author Bryan Ortiz P
 * @since 1.1
 */
public class Sorcerer extends AbstractUnit {

    /**
     * Creates a new Sorcerer.
     *
     * @param hitPoints
     *     the maximum amount of damage a unit can sustain
     * @param movement
     *     the number of panels a unit can move
     */
    public Sorcerer(final int hitPoints, final int movement, final Location location,
                  IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    /**
     * Sets the currently equipped item of this unit.
     *
     * @param item
     *     the item to equip
     */
    @Override
    public void equipItem(final IEquipableItem item) {
        if (item instanceof Staff) {
            setEquippedItem(item);
        }
    }

    @Override
    public void attack(IUnit target) {
        if(isAbleToAttack(target)){
            startCombatWith(target);
            ISpellsBook item = (ISpellsBook) getEquippedItem();
            item.throwSpell(target);
            target.counterAttack(this);
        }
    }

    @Override
    public void counterAttack(IUnit aggressor) {
        if(isAbleToAttack(aggressor)){
            startCombatWith(aggressor);
            ISpellsBook item = (ISpellsBook) getEquippedItem();
            item.throwSpell(aggressor);
        } else {
            endCombatWith(aggressor);
        }
    }

}
