import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**.
 * asrassa
 */
public class Ass7Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception exception is thrown
     */
    public static void main(String[] args) throws Exception {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);

        HighScoresTable table = new HighScoresTable(5);
        if (new File("highscores").exists()) {
            try {
                table.load(new File("highscores"));
            } catch (IOException e) {
                System.out.println("cant load highscores");
            }
        }
        GameFlow gF = new GameFlow(runner, gui.getKeyboardSensor(), gui, table);
        MenuAnimation<Task<Void>> levelSetsMenu = new MenuAnimation<Task<Void>>("Level Sets", gui.getKeyboardSensor(),
                runner);
        String levelSetsPath = "level_sets.txt";
        if (args.length > 0) {
            levelSetsPath = args[0];
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(ClassLoader.getSystemResourceAsStream(levelSetsPath)));
            LevelSetsReader levelSetsReader = new LevelSetsReader();

            final List<LevelSets> l = levelSetsReader.readFromFile(br);
            Iterator<LevelSets> levelSetsIteratorIterator = l.iterator();
            while (levelSetsIteratorIterator.hasNext()) {
                LevelSets levelSets = levelSetsIteratorIterator.next();
                LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                BufferedReader r = null;
                try {
                    r =
                            new BufferedReader(new InputStreamReader(
                                            ClassLoader.getSystemResourceAsStream(levelSets.getLevelPath())));
                    final List<LevelInformation> levels = levelSpecificationReader.fromReader(r);

                    Task<Void> task = new Task<Void>() {
                        @Override
                        public Void run() {
                            gF.runLevels(levels);
                            return null;
                        }

                    };

                    levelSetsMenu.addSelection(levelSets.getKey(), levelSets.getMessage(), task);

                } catch (Exception e) {
                    System.out.println("error args");
                }
            }
        } catch (Exception e) {
            System.out.println("error args");

        }
        KeyPressStoppableAnimation highScores = new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(table));
        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", gui.getKeyboardSensor(), runner);
        menu.setBackground(new BackgroundTemplate("", "background_images/mountain.jpg"));
        menu.addSubMenu("s", "start game", levelSetsMenu);
        menu.addSelection("h", "Hi scores", new ShowHiScoresTask(runner, highScores));
        menu.addSelection("q", "quit", new ExitTask());
        while (true) {
            runner.run(menu);
            // wait for user selection
            Task task = (Task) menu.getStatus();
            task.run();
            menu.reset();
        }

    }
}

