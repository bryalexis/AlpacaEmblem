package model.units.healers;

import model.units.IUnit;

public interface IHealer extends IUnit {

    /**
     * Gives Hit Points to an unit
     * @param unit who will receive the Hit Points
     */
    void useItemOn(IUnit unit);
}
