package model.units;

import model.items.IEquipableItem;
import model.map.Location;

public abstract class __AbstractMagic extends AbstractUnit{

    public __AbstractMagic(final int hitPoints, final int movement,
                           final Location location, final int maxItems, final IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }
}
