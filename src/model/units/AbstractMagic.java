package model.units;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.warriors.Fighter;
import model.units.warriors.Hero;
import model.units.warriors.SwordMaster;

public abstract class AbstractMagic extends AbstractUnit{

    public AbstractMagic(final int hitPoints, final int movement,
                            final Location location, final int maxItems, final IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    @Override
    public void receivePhysicalAttack(IUnit unit){
        modifyCurrentHitPoints(-unit.getEquippedItem().getPower()*1.5);
    }

    @Override
    public void receiveMagicalAttack(IUnit unit) {
        modifyCurrentHitPoints(-unit.getEquippedItem().getPower());
    }
}
