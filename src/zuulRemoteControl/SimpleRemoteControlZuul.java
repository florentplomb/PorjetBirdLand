package zuulRemoteControl;

import communication.GameEngineProxy;
import model.Model;

// This class initializes a basic remote control for zuul on the localhost
public class SimpleRemoteControlZuul {

    public static void main(String[] args) {
        Model m = new GameEngineProxy();
        ((Thread) m).start();
        SimpleRemoteView remoteView = new SimpleRemoteView(m);
    }
}
