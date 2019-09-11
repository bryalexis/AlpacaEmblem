package model.items.healing;

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
public interface IHealing extends IEquipableItem {

    /**
     * Heals another unit
     * @param target who will receive the healing
     */
    void useOn(IUnit target);
}