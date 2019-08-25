package model.units.healers;

import model.units.IUnit;

public interface IHealer {

    /**
     * Gives Hit Points to an unit
     * @param unit who will receive the Hit Points
     */
    void heal(IUnit unit);
}
