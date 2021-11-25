import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    private boolean stop;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(184, 111, 179));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(190, 50, "High Scores Table", 60);
        java.util.List<ScoreInfo> list = new ArrayList<>(this.highScoresTable.getHighScores());
        for (int i = 0, j = 0; i < list.size(); i++, j += 45) {
            d.drawText(260, 110 + j, list.get(i).getName(), 40);
            d.drawText(500, 110 + j, Integer.toString(list.get(i).getScore()), 40);

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
