package model.units.magic;

import model.items.IEquipableItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
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
        if (item instanceof Darkness || item instanceof Light || item instanceof Spirit) {
            equippedItem = item;
        }
    }

    @Override
    public void attack(IUnit target) {
        if(isAbleToAttack(target)){
            startCombatWith(target);
            getEquippedItem().throwSpell(target);
            target.attack(this);
        } else{
            endCombatWith(target);
        }
    }

    // Sería conveniente que cada sorcerer sólo pudiera equiparse un tipo de libro

    @Override
    public void receiveDarknessSpell(IUnit sorcerer) {
        getEquippedItem().takeInDarknessSpell((Darkness) sorcerer.getEquippedItem());
    }

    @Override
    public void receiveLightSpell(IUnit sorcerer) {
        getEquippedItem().takeInLightSpell((Light) sorcerer.getEquippedItem());
    }

    @Override
    public void receiveSpiritSpell(IUnit sorcerer) {
        getEquippedItem().takeInSpiritSpell((Spirit) sorcerer.getEquippedItem());
    }


}
