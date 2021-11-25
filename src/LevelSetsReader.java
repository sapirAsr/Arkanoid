import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Level sets reader.
 */
public class LevelSetsReader {
    /**
     * Read from file list.
     *
     * @param buffer the buffer
     * @return the list
     */
    public List<LevelSets> readFromFile(BufferedReader buffer) {
        List<LevelSets> levelSetsReader = new ArrayList<LevelSets>();
        LineNumberReader r = new LineNumberReader(buffer);
        Map<String, String> seter = new HashMap<>();
        String key = null;
        String message = null;
        String levelPath = null;
        String line = null;
        try {
            while ((line = buffer.readLine()) != null) {

                if (r.getLineNumber() % 2 == 0) {
                    String[] lineParts = line.trim().split(":");
                    if (lineParts.length == 2) {
                        seter.put(lineParts[0], lineParts[1]);
                        key = lineParts[0];
                        message = lineParts[1];
                    } else {
                        levelPath = line;
                        LevelSets levelSets = new LevelSets(key, message, levelPath);
                        levelSetsReader.add(levelSets);
                    }
                }
            }


        } catch (IOException e) {
            System.out.println("problem with level sets");
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (IOException e1) {
                    System.out.println("cant close the file");
                }
            }
        }
        return levelSetsReader;
    }
}