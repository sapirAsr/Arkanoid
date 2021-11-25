import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DialogManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> levelsList;
    private Counter score = new Counter(0);
    private Counter lives = new Counter(4);
    private GUI gui;
    private HighScoresTable table;


    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the animation runner
     * @param ks  the keyboard sensor
     * @param le  the level information
     * @param gui the gui
     * @param table the table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, List<LevelInformation> le, GUI gui, HighScoresTable table) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.levelsList = le;
        this.gui = gui;
        this.table = table;
    }

    /**
     * @param ar    the animation runner
     * @param ks    the keyboard sensor
     * @param gui   the gui
     * @param table the table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable table) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.table = table;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, lives, score);
            level.initialize();

            while ((level.getLives() > 0) && (level.getBlocks() > 0)) {
                level.playOneTurn();
            }

            if (level.getLives() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new LoseGame(this.keyboardSensor, this.score.getValue())));
                if (table.getRank(this.score.getValue()) <= table.size()) {
                    DialogManager dialog = gui.getDialogManager();
                    String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                    table.add(new ScoreInfo(name, this.score.getValue()));
                }
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(table)));
                try {
                    table.save(new File("highscores"));
                } catch (IOException e1) {
                    System.out.println("cant save the table");
                }
                this.lives = new Counter(4);
                this.score = new Counter(0);
                break;
            }
            int size = levels.size();
            if ((levelInfo == levels.get(size - 1)) && (level.getBlocks() == 0)) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new WinGame(this.keyboardSensor, this.score.getValue())));
                if (table.getRank(this.score.getValue()) <= table.size()) {
                    DialogManager dialog = gui.getDialogManager();
                    String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                    table.add(new ScoreInfo(name, this.score.getValue()));
                }

                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(table)));
                try {
                    table.save(new File("highscores"));
                } catch (IOException e1) {
                    System.out.println("cant save the table");
                }
                this.score = new Counter(0);
                this.lives = new Counter(4);
                break;
            }

        }
    }
}
