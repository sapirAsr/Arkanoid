import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
// parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        if (s == null) {
            return null;
        }
        s = s.replace("color(", "");
        s = s.replace(")", "");
        switch (s) {
            case "blue":
                return Color.BLUE;
            case "cyan":
                return Color.cyan;
            case "black":
                return Color.black;
            case "gray":
                return Color.gray;
            case "lightGray":
                return Color.lightGray;
            case "green":
                return Color.green;
            case "orange":
                return Color.orange;
            case "pink":
                return Color.pink;
            case "red":
                return Color.red;
            case "white":
                return Color.white;
            case "yellow":
                return Color.yellow;
                default:break;

        }
        if (s.contains("RGB")) {
            s = s.replace("RGB(", "");
            s = s.replace(")", "");
            String[] parts = s.split(",");
            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            return new Color(r, g, b);
        }
        return null;
    }
}
