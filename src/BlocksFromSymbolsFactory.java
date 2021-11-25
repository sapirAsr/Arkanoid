import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new HashMap<>();
    private Map<String, Integer> spacerHeights = new HashMap<>();

    private Map<String, BlockCreator> blockCreators = new HashMap<>();

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
// returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Gets space width.
     *
     * @param s the s
     * @return Returns the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Add block creator.
     *
     * @param symbol       the symbol
     * @param blockCreator the block creator
     */
    public void addBlockCreator(String symbol, BlockCreator blockCreator) {
        this.blockCreators.put(symbol, blockCreator);
    }

    /**
     * Add spacer width.
     *
     * @param symbol the symbol
     * @param width  the width
     */
    public void addSpacerWidth(String symbol, Integer width) {
        this.spacerWidths.put(symbol, width);
    }

    /**
     * Add spacer height.
     *
     * @param symbol the symbol
     * @param height the height
     */
    public void addSpacerHeight(String symbol, Integer height) {
        this.spacerHeights.put(symbol, height);
    }

    /**
     * Gets blocks.
     *
     * @return the blocks
     */
    public Map<String, BlockCreator> getBlocks() {
        return this.blockCreators;
    }
}