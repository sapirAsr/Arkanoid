import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

public class Main {

    public static Rectangle generateRandomRec() {
        Random rand = new Random(); // create a random-number generator
        Point p = new Point(rand.nextInt(400), rand.nextInt(400));
        Rectangle r = new Rectangle(p, rand.nextInt(100), rand.nextInt(100));
        return r;
    }
    public static void drawRec(Rectangle r, DrawSurface d) {
        int x1 = (int) r.getUpperLeft().getX();
        int y1 = (int) r.getUpperLeft().getY();
        int w = (int) r.getWidth();
        int h = (int) r.getHeight();

        d.setColor(Color.BLUE);
        d.drawRectangle(x1, y1, w, h);
    }


    public static void main(String[] args) {
        int recsNum = 3;
        Block[] blocks = new Block[recsNum];
        GameEnvironment g = new GameEnvironment();

        GUI gui = new GUI("title", 400, 400);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(380, 380, 8, Color.PINK, g);
        ball.setVelocity(2, 0);
        ball.minimum(0); // the minimum value of the frame for the ball
        ball.maximum(400); // the maximum value of the frame for the ball
        ball.ballGame(g);
        Rectangle r = new Rectangle(new Point(0,200),10,30);
        Block one = new Block(r);
        g.addCollidable(one);
        Rectangle y = new Rectangle(new Point(10,20),20,10);
        Block two = new Block(y);
        g.addCollidable(two);
        Rectangle z = new Rectangle(new Point(0,0),4000,400);
        Block three = new Block(z);
        g.addCollidable(three);

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            drawRec(r, d);
            drawRec(y, d);
            drawRec(z, d);
            ball.drawOn(d);
            ball.moveOneStep();
            System.out.println("x --" + ball.getX());
            System.out.println("y --" + ball.getY());
            System.out.println("dx :" + ball.getVelocity().getDx());
            System.out.println("dy :" + ball.getVelocity().getDy());
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.

        }

    }
}
