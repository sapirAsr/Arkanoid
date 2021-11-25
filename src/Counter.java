/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param counter the counter
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    void decrease(int number) {
        this.counter -= number;

    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    int getValue() {
        return this.counter;
    }
}
