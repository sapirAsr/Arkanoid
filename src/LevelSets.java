/**
 * The type Level sets.
 */
public class LevelSets {
    private String key;
    private String levelPath;
    private String message;

    /**
     * Instantiates a new Level sets.
     *
     * @param name    the name
     * @param message the message
     * @param path    the path
     */
    public LevelSets(String name, String message, String path) {
        this.key = name;
        this.message = message;
        this.levelPath = path;

    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.key = name;
    }
    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Get level path string.
     *
     * @return the string
     */
    public String getLevelPath() {
        return this.levelPath;
    }

    /**
     * Get message string.
     *
     * @return the string
     */
    public String getMessage() {
        return this.message;
    }
}
