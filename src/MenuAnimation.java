import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu {
    private String title;
    private List<String> keys = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    private List<Object> returnVl = new ArrayList<>();
    private boolean stop;
    private KeyboardSensor keyboard;
    private Object toRet = null;
    private List<Boolean> isSubMenu = new ArrayList<>();
    private Map<String, Menu> subMenus = new HashMap<>();
    private AnimationRunner runner;
    private Sprite menuBackground = null;


    /**
     * Instantiates a new Menu animation.
     *
     * @param title  the title
     * @param sensor the sensor
     * @param runner the runner
     */
    public MenuAnimation(String title, KeyboardSensor sensor, AnimationRunner runner) {
        this.title = title;
        this.keyboard = sensor;
        this.runner = runner;
    }

    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVl.add(returnVal);
        this.isSubMenu.add(false);
        this.subMenus.put(key, null);

    }

    @Override
    public Object getStatus() {
        return toRet;
    }

    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        this.keys.add(key);
        this.returnVl.add(null);
        this.messages.add(message);
        this.subMenus.put(key, subMenu);
        this.isSubMenu.add(true);
    }

    /**
     * Reset.
     */
    public void reset() {
        this.toRet = null;
        this.stop = false;
    }

    /**
     * Sets background.
     *
     * @param background the background
     */
    public void setBackground(Sprite background) {
        this.menuBackground = background;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.menuBackground == null) {
            d.setColor(new Color(30, 70, 80));
            d.fillRectangle(0, 0, 800, 600);
        } else {
            this.menuBackground.drawOn(d);
        }
        d.setColor(Color.white);
        d.drawText(230, 85, title, 100);
        for (int i = 0, j = 0; i < messages.size(); i++, j += 70) {
            d.drawText(300, 170 + j, "(" + keys.get(i) + ")", 30);

            d.drawText(350, 170 + j, messages.get(i), 30);
        }
        for (int i = 0; i < keys.size(); i++) {
            if (this.keyboard.isPressed(this.keys.get(i))) {
                if (!this.isSubMenu.get(i)) {
                    this.stop = true;
                    toRet = this.returnVl.get(i);
                } else {
                    Menu subMenu = this.subMenus.get(this.keys.get(i));
                    this.runner.run(subMenu);
                    this.toRet = subMenu.getStatus();
                    this.stop = true;
                    ((MenuAnimation) subMenu).reset();

                }
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * @return the sub menu
     */
    public Menu getSub() {
        if (this.keys != null) {
            if (this.subMenus.containsKey(this.keys)) {
                this.stop = false;
                return this.subMenus.get(this.keys);
            }
        }
        return null;
    }
}