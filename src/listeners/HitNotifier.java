// Yuval Weber 207455437
package listeners;

/**
 * listeners.HitNotifier interface.
 * The listeners.HitNotifier interface will be implemented by classes that are interested
 * in being notified of hit events.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * Add hl as a listener to hit events.
     * @param hl The listeners.HitListener to add.
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl The listeners.HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}
