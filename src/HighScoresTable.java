import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;


/**
 * The type High scores tableH.
 */
public class HighScoresTable implements Serializable {
    // Create an empty high-scores tableH with the specified size.
    // The size means that the tableH holds up to size top scores.
    private int size;
    private List<ScoreInfo> tableH = new ArrayList<>();

    /**
     * Instantiates a new High scores tableH.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.size = size;
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank <= this.size) {
            this.tableH.add(score);
            this.tableH = this.sortTable(this.tableH);
            List<ScoreInfo> temp = new ArrayList<>();
            int min = Math.min(this.size, this.tableH.size());
            for (int i = 0; i < min; i++) {
                temp.add(this.tableH.get(i));
            }
            this.tableH = temp;
        }
    }

    /**
     * Sort tableH list.
     *
     * @param table the tableH
     * @return the list
     */
    public List<ScoreInfo> sortTable(List<ScoreInfo> table) {
        table.sort(new Comparator<ScoreInfo>() {
            public int compare(ScoreInfo one, ScoreInfo two) {
                int scoreOne = one.getScore();
                int scoreTwo = two.getScore();
                if (scoreOne > scoreTwo) {
                    return -1;
                }

                if (scoreOne < scoreTwo) {
                    return 1;
                }

                return 0;
            }
        });

        return new ArrayList<ScoreInfo>(table);
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return tableH size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.tableH;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //be added to the list.
    public int getRank(int score) {
        List<ScoreInfo> temp = new ArrayList<>(tableH);
        int index = 0;
        if (temp.isEmpty()) {
            return 1;
        }
        for (ScoreInfo t : tableH) {
            if (t.getScore() > score) {
                index++;
            }
        }
        return index + 1;
    }

    /**
     * Clear.
     */
// Clears the tableH
    public void clear() {
        for (int i = 0; i < size; i++) {
            this.tableH.remove(i);
        }
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load tableH data from file.
    // Current tableH data is cleared.
    public void load(File filename) throws IOException {
        BufferedReader is = null;
        try {
            is = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(" highscores ")));

            String line = is.readLine();
            while (line != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    this.tableH.add(new ScoreInfo(parts[0], Integer.parseInt(parts[1])));
                    this.tableH = sortTable(this.tableH);
                } else {
                    break;
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }

    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save tableH data to the specified file.
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        if (!tableH.isEmpty()) {
            try {
                os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(" highscores")));

                for (int i = 0; i < tableH.size(); i++) {
                    os.printf(this.tableH.get(i).getName());
                    os.printf(" ");
                    os.printf(Integer.toString(this.tableH.get(i).getScore()));
                    os.println();

                }
            } catch (IOException e) {
                System.out.println(" Something went wrong while writing !");

            } finally {
                if (os != null) { // Exception might have happened at constructor
                    os.close(); // closes fileOutputStream too
                }
            }
        }
    }

    /**
     * Load from file high scores tableH.
     *
     * @param filename the filename
     * @return the high scores tableH
     */
// Read a tableH from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty tableH is returned.
    public static HighScoresTable loadFromFile(File filename) {
        if (filename.exists()) {
            ObjectInputStream objectInputStream = null;
            try {

                objectInputStream = new ObjectInputStream(new FileInputStream(filename));
                HighScoresTable temp = (HighScoresTable) objectInputStream.readObject();
                return temp;
            } catch (Exception e) {
                System.out.println(" Something went wrong with user input !");
                return new HighScoresTable(0);
            } finally {
                try {
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (Exception e) {
                    System.out.println("Error in closing file at load method!");
                    e.printStackTrace(System.err);
                }

            }

        } else {
            return new HighScoresTable(0);
        }

    }

    /**
     * Print.
     */
    public void print() {
        System.out.println("print start");
        for (ScoreInfo grade : this.tableH) {
            System.out.println(grade.getScore());
        }
        System.out.println("print end");
    }
}
