import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        HighScoresTable table = new HighScoresTable(5);
      table.add(new ScoreInfo("sapir", 40));
      table.add(new ScoreInfo("bla", 70));
      table.add(new ScoreInfo("blabla", 50));
      //table.add(new ScoreInfo("blabla", 50));
      //table.add(new ScoreInfo("blabla", 50));
      //table.add(new ScoreInfo("blabla", 50));
      //table.add(new ScoreInfo("blabla", 50));
      //table.print();
       try {
            table.load(new File("highscores"));
            //table.save(new File("highscores"));
        }catch (IOException e){
            System.out.println("ERORR");
        }
        table.print();

    }
}
