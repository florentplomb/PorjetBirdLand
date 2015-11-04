package view;

/**
 * All classes implementing this interface will have the gameStateModified
 * method. All classes implementing this interface and which have signed on to
 * the GameEngine will be notified when the game state changes.
 *
 * Classes implementing this interface can choose what to do once they are
 * notified, since they hold the detail of the implementation of the
 * gameStateModified method.
 */
public interface GameListener {

    /**
     * gameStateModified is called when an update has occurred
     *
     * @param roomImageName is the name of the current room
     */
    void gameStateModified(String roomImageName);
}
