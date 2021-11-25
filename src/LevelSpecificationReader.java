import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    private List<LevelInformation> levels = new ArrayList<>();
    private List<String> blocksStr = new ArrayList<>();

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader bf = new BufferedReader(reader);
        try {
            bf.readLine();
            String str;
            Map<String, String> levelStr = new HashMap<String, String>();
            while ((str = bf.readLine()) != null) {
                if (!"".equals(str) && !str.startsWith("#")) {
                    String[] parts = str.split(":");
                    if (parts.length == 2) {
                        levelStr.put(parts[0], parts[1]);
                    }
                    if (!str.startsWith("#") && !str.equals("") && !str.contains(":")
                            && !str.equals("START_BLOCKS") && !str.equals("END_BLOCKS")
                            && !str.equals("START_LEVEL")  && !str.equals("END_LEVEL")) {
                        blocksStr.add(str);
                    }
                    if (str.equals("END_LEVEL")) {
                        this.checkIfAllInMap(levelStr);
                        LevelInformation levelInformation = this.fromMapToLevel(levelStr, blocksStr);
                        levels.add(levelInformation);
                    }

                }
            }
            return levels;

        } catch (IOException e) {
            System.out.println("cant read the levels");
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
        return levels;
    }

    /**
     * From map to level level information.
     *
     * @param map         the map
     * @param blocksOrder the blocks order
     * @return the level information
     */
    public LevelInformation fromMapToLevel(Map<String, String> map, List<String> blocksOrder) {
        String levelName = map.get("level_name").trim();
        List<Velocity> ballsVelocity = new ArrayList<>();
        String[] velocity = map.get("ball_velocities").split(" ");
        for (String v : velocity) {
            String[] velocityParts = v.split(",");
            Velocity vel = Velocity.fromAngleAndSpeed(Integer.parseInt(velocityParts[0].trim()),
                    Integer.parseInt(velocityParts[1].trim()));
            ballsVelocity.add(vel);
        }
        String background1 = map.get("background").trim();
        Color backgroundColor = null;
        Image backgroundImage = null;
        if (background1.contains("color")) {
            ColorsParser c = new ColorsParser();
            backgroundColor = c.colorFromString(background1);
        } else if (background1.contains("image")) {
            backgroundImage = this.convertToImage(background1);
        }
        BackgroundTemplate background = null;
        if (backgroundColor == null) {
            background = new BackgroundTemplate(levelName, backgroundImage);
        } else if (backgroundImage == null) {
            background = new BackgroundTemplate(levelName, backgroundColor);

        }
        int paddelS = Integer.parseInt(map.get("paddle_speed").trim());
        int paddelW = Integer.parseInt(map.get("paddle_width").trim());
        int blocksX = Integer.parseInt(map.get("blocks_start_x").trim());
        int blocksY = Integer.parseInt(map.get("blocks_start_y").trim());
        int rowHeight = Integer.parseInt(map.get("row_height").trim());
        int numOfBlocks = Integer.parseInt(map.get("num_blocks").trim());
        String blockDefinitions = map.get("block_definitions").trim();
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();
        try {
            blocksFromSymbolsFactory = BlocksDefinitionReader
                    .fromReader(new InputStreamReader(
                            ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinitions)));
        } catch (Exception e) {
            System.out.println("error blocks");
        }
        double upperLeftX;
        double upperLeftY = blocksY;
        List<Block> blocks = new ArrayList<>();
        for (String blockLine : blocksOrder) {
            upperLeftX = blocksX;
            for (int i = 0; i < blockLine.length(); i++) {
                char symbolChar = blockLine.charAt(i);
                String symbol = String.valueOf(symbolChar);
                if (blocksFromSymbolsFactory.isSpaceSymbol(symbol) && blockLine.length() != 1) {
                    double width = blocksFromSymbolsFactory.getSpaceWidth(symbol);
                    upperLeftX += width;
                } else if (blocksFromSymbolsFactory.isBlockSymbol((symbol))) {
                    Block block = blocksFromSymbolsFactory.getBlock(symbol, (int) upperLeftX, (int) upperLeftY);
                    blocks.add(block);
                    double width = block.getCollisionRectangle().getWidth();
                    upperLeftX += width;
                }
            }
            upperLeftY += rowHeight;
        }
        blocksStr.clear();
        LevelInformation levelInformation = new TemplateLevelInfo(ballsVelocity.size(), ballsVelocity, paddelS,
                paddelW, levelName, blocks, background);
        return levelInformation;
    }

    /**
     * Check if all in map.
     *
     * @param map the map
     * @throws RuntimeException the runtime exception
     */
    public void checkIfAllInMap(Map<String, String> map) throws RuntimeException {
        if (!map.containsKey("level_name") || !map.containsKey("ball_velocities")
                || !map.containsKey("background") || !map.containsKey("paddle_speed")
                || !map.containsKey("paddle_width") || !map.containsKey("block_definitions")
                || !map.containsKey("blocks_start_x") || !map.containsKey("blocks_start_y")
                || !map.containsKey("row_height") || !map.containsKey("num_blocks")) {
            throw new RuntimeException("missing parameters in map!");
        }
    }

    /**
     * Convert to image image.
     *
     * @param img the img
     * @return the image
     */
    public Image convertToImage(String img) {
        Image image;
        img = img.replace("image(", "");
        img = img.replace(")", "");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(img);
        try {
            image = ImageIO.read(is);
            return image;
        } catch (IOException e) {
            System.out.println("cant load img");
        }
        return null;
    }
}
