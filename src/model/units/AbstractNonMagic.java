package model.units;

import model.items.IEquipableItem;
import model.map.Location;

public abstract class AbstractNonMagic extends AbstractUnit {

    public AbstractNonMagic(final int hitPoints, final int movement,
                            final Location location, final int maxItems, final IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }

    @Override
    public void receivePhysicalAttack(IUnit unit){
        modifyCurrentHitPoints(-unit.getEquippedItem().getPower());
    }

    @Override
    public void receiveMagicalAttack(IUnit unit){
        modifyCurrentHitPoints(-unit.getEquippedItem().getPower()*1.5);
    }

}
