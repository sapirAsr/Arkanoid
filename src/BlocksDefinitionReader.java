import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bf = new BufferedReader(reader);
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();

        try {
            BlocksDefinitionReader blockDef = new BlocksDefinitionReader();
            bf.readLine();
            String str;
            Map<String, String> defaultValues = new HashMap<>();


            while ((str = bf.readLine()) != null) {
                if (!"".equals(str) && !str.startsWith("#")) {
                    if (str.startsWith("default ")) {
                        str = str.replace("default ", "");
                        defaultValues = blockDef.fromLineToMap(str);
                    } else if (str.startsWith("bdef ")) {
                        str = str.replace("bdef ", "");
                        Map<String, String> blockBySymbolParams = blockDef.fromLineToMap(str);
                        HashMap<String, String> blockDetails = new HashMap<>(defaultValues);
                        blockDetails.putAll(blockBySymbolParams);
                        checkIfAllParamsExist(blockDetails);
                        String symbol = blockDetails.get("symbol");
                        if (symbol.length() != 1) {
                            throw new RuntimeException("symbol is not valid");
                        }
                        int height = Integer.parseInt(blockDetails.get("height"));
                        int width = Integer.parseInt(blockDetails.get("width"));
                        int hitPoints = Integer.parseInt(blockDetails.get("hit_points"));
                        if (height <= 0 || width <= 0 || hitPoints <= 0) {
                            throw new RuntimeException("must be positive number!");

                        }
                        Image image = null;
                        Color color = null;
                        ColorsParser cP = new ColorsParser();
                        Map<Integer, Color> colorMap = new HashMap<>();
                        Map<Integer, Image> imageMap = new HashMap<>();
                        if (blockDetails.containsKey("fill")) {
                            String fillValue = blockDetails.get("fill").trim();
                            if (fillValue != null && fillValue.contains("color")) {
                                color = cP.colorFromString(fillValue);
                                colorMap.put(0, color);
                            } else if (fillValue != null && fillValue.contains("image")) {
                                image = blockDef.convertToImage(fillValue);
                                imageMap.put(0, image);
                            }
                        }
                            for (int i = 0; i < hitPoints; i++) {
                                String fillName = "fill-" + (i + 1);
                                if (blockDetails.containsKey(fillName)) {
                                    String fillValue = blockDetails.get(fillName).trim();
                                    if (fillValue != null && fillValue.contains("color")) {
                                        color = cP.colorFromString(fillValue);
                                        colorMap.put(i, color);
                                    } else if (fillValue != null && fillValue.contains("image")) {
                                        image = blockDef.convertToImage(fillValue);
                                        imageMap.put(i, image);
                                    }
                                } else {
                                    if (image != null) {
                                        imageMap.put(i, image);
                                    } else if (color != null) {
                                        colorMap.put(i, color);
                                    }
                                }

                            }

                        Color stroke = null;
                        if (blockDetails.containsKey("stroke")) {
                            stroke = cP.colorFromString(blockDetails.get("stroke"));
                        }
                        BlockCreator blockCreator = new CreateBlock(height, width, hitPoints, colorMap,
                                imageMap, stroke);

                        blocksFromSymbolsFactory.addBlockCreator(symbol, blockCreator);


                    } else if (str.startsWith("sdef ")) {
                        str = str.replace("sdef ", "");
                        Map<String, String> spacers = blockDef.fromLineToMap(str);
                        String symbol = spacers.get("symbol");
                        if (symbol.length() != 1) {
                            throw new RuntimeException("symbol is not valid");
                        }
                        int width = Integer.parseInt(spacers.get("width"));

                        if (width <= 0) {
                            throw new RuntimeException("width must be positive number");
                        }
                        checkSpaceParams(spacers);
                        blocksFromSymbolsFactory.addSpacerWidth(symbol, width);
                    }
                }
            }
        } catch (IOException e) {

            System.out.println("cant read the block section");
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
        return blocksFromSymbolsFactory;
    }

    /**.
     * convert a string to an image
     * @param img the image path
     * @return the image
     */
    private Image convertToImage(String img) {
        Image image = null;
        img = img.replace("image(", "");
        img = img.replace(")", "");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(img);

        try {
            image = ImageIO.read(is);
            return image;
        } catch (IOException e) {
            System.out.println("cant load image");
        }
        return null;
    }

    /**.
     * checking if all the parametrs are in the map
     * @param params the map
     * @throws RuntimeException exception
     */
    private static void checkIfAllParamsExist(Map<String, String> params) throws RuntimeException {
        if (!params.containsKey("height") || !params.containsKey("width")
                || !params.containsKey("hit_points") || !params.containsKey("symbol")) {
            throw new RuntimeException("missing parameters!");
        }
    }

    /**.
     * checking if all the space symbols are in the map
     * @param params the map
     * @throws RuntimeException exception
     */
    private static void checkSpaceParams(Map<String, String> params) throws RuntimeException {
        if (!params.containsKey("symbol") || !params.containsKey("width")) {
            throw new RuntimeException("missing parameters!");
        }
    }

    /**.
     * turning a string to a map
     * @param s the string
     * @return the map
     */

    private Map<String, String> fromLineToMap(String s) {
        String[] parts = s.split(" ");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            String[] sParts = parts[i].split(":");
            if (sParts.length == 2) {
                map.put(sParts[0], sParts[1]);
            }
        }
        return map;
    }
}
