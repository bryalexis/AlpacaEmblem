package model.spellBooks;

/**
 * This class represents a <i>Spirit</i> item.
 * <p>
 * Spirit is a spell book of spirit type.
 * Spirit spells are strong against light and weak against darkness.
 *
 * @author Bryan Ortiz P
 * @since 1.0
 */
public class Spirit extends AbstractBook {

    /**
     * Creates a new Spirit book item.
     *
     * @param name
     *     the name of the Spirit Book
     * @param power
     *     the base damage of the Spirit Book
     * @param minRange
     *     the minimum range of the spells
     * @param maxRange
     *     the maximum range of the spells
     */
    public Spirit(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }
}
