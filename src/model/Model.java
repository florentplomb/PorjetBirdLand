package model;

import view.GameListener;

public interface Model {

    public void interpretCommand(String commandLine);

    public void addGameListener(GameListener gl);
}
