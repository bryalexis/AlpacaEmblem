package model.items;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This interface represents the <i>healing items</i> that clerics of the game can use.
 * <p>
 * The signature for all the common methods of the healing items are defined here.
 * Every book have a base damage and is strong or weak against other type of books and weapons.
 *
 * @author Bryan Ortiz P.
 * @since 1.1
 */
public interface IHealingItem extends IEquipableItem {

    /**
     * Heals another unit
     * @param target who will receive the healing
     */
    void heal(IUnit target);

    /**
     * It tells if a the item can heal the target unit
     * If the owner of the unit that owns the item is different to the target
     * unit, the healing is refused.
     * @param target unit that will receive the healing
     * @return if the healing can be made.
     */
    boolean allowedToHeal(IUnit target);
}