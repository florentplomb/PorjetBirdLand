package model;

import view.GameListener;

/**
 * Implementation of the Model interface
 *
 */

public interface Model {

    public void interpretCommand(String commandLine);

    public void addGameListener(GameListener gl);
}
