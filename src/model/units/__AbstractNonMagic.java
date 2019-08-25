package model.units;

import model.items.IEquipableItem;
import model.items.spellbooks.Darkness;
import model.items.spellbooks.Light;
import model.items.spellbooks.Spirit;
import model.map.Location;

public abstract class __AbstractNonMagic extends AbstractUnit {

    public __AbstractNonMagic(final int hitPoints, final int movement,
                              final Location location, final int maxItems, final IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }


}
