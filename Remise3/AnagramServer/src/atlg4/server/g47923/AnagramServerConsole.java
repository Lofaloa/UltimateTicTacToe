package atlg4.server.g47923;

import anagram.exception.ModelException;
import anagram.model.Facade;
import anagram.model.Model;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code> AnagramServerConsole </code> contains all the methods necessary to
 * manage the view in console mode of <code> AnagramServer </code>.
 */
public class AnagramServerConsole implements Observer {
    
    
    /**
     * Entry point to the anagram game server side.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) throws ModelException {
        try {
            AnagramServer server = new AnagramServer();
            AnagramServerConsole console = new AnagramServerConsole(server);
            server.addObserver(console);
            System.out.println("Server started\n");
        } catch (IOException ex) {
            Logger.getLogger(AnagramServerConsole.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    
    private final AnagramServer model;

    /**
     * Constructs the console view.
     *
     * @param model instant messaging server.
     */
    public AnagramServerConsole(AnagramServer model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
}
