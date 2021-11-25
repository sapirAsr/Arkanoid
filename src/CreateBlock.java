import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Create block.
 */
public class CreateBlock implements BlockCreator {
    private int hitPoints;
    private List<HitListener> hitListeners;
    private int height;
    private int width;
    private Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
    private Map<Integer, Image> imageMap = new HashMap<Integer, Image>();
    private Color stroke;

    /**
     * Instantiates a new Create block.
     *
     * @param height    the height
     * @param width     the width
     * @param hitPoints the hit points
     * @param colorMap  the color map
     * @param imageMap  the image map
     * @param stroke    the stroke
     */
    public CreateBlock(int height, int width, int hitPoints, Map<Integer, Color> colorMap,
                       Map<Integer, Image> imageMap,
                       Color stroke) {
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.colorMap = colorMap;
        this.imageMap = imageMap;
        this.stroke = stroke;
        this.hitListeners = new ArrayList<HitListener>();

    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = new Block(new Point(xpos, ypos), this.height, this.width,
                this.hitPoints, this.colorMap, this.imageMap, this.stroke);
        return block;
    }
}
