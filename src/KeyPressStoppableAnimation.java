import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation  implements Animation {

    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        //if (this.keyboardSensor.isPressed(key) && !this.isAlreadyPressed) {
        //    this.stop = true;
        //}
        return this.stop;
    }

    /**.
     * reset the menu
     */
    public void restartKey() {
        this.stop = false;
        this.isAlreadyPressed = true;
    }
}

