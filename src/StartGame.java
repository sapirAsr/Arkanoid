import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Start game.
 */
public class StartGame implements Task {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> levelsList;
    private GUI gui;
    private HighScoresTable table;


    /**
     * Instantiates a new Start game.
     *
     * @param ar    the ar
     * @param ks    the ks
     * @param le    the le
     * @param gui   the gui
     * @param table the table
     */
    public StartGame(AnimationRunner ar, KeyboardSensor ks, List<LevelInformation> le, GUI gui, HighScoresTable table) {
        this.runner = ar;
        this.keyboardSensor = ks;
        this.levelsList = le;
        this.gui = gui;
        this.table = table;
    }

    /**
     * @return runnig the game
     */
    public Object run() {
        GameFlow gF = new GameFlow(runner, keyboardSensor, levelsList, gui, table);
        gF.runLevels(levelsList);
        return null;
    }
}
