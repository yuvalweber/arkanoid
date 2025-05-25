// Yuval Weber 207455437
package listeners;

/**
 * listeners.Counter class.
 * This class is used to count numbers.
 * It has three methods:
 * 1. increase: add number to current count.
 * 2. decrease: subtract number from current count.
 * 3. getValue: get current count.
 */
public class Counter {
    private int count;
    // constructor.
    /**
     * Constructor.
     */
    public Counter() {
        this.count = 0;
    }
    // add number to current count.
    /**
     * Add number to current count.
     * @param number the number to add.
     */
    public void increase(int number) {
        this.count += number;
    }
    // subtract number from current count.
    /**
     * Subtract number from current count.
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        this.count -= number;
    }
    // get current count.
    /**
     * Get current count.
     * @return the current count.
     */
    public int getValue() {
        return this.count;
    }
}
