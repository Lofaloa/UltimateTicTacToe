package atlg4.server.g47923;

import anagram.exception.ModelException;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.players.User;
import java.io.IOException;
import java.time.LocalDateTime;
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

    private void updatePlayers() {
        System.out.println("");
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste des joueurs ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getNbConnected()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("NAME").append("\n");
        for (User member : model.getPlayers()) {
            builder.append(member.getId()).append("\t");
            builder.append(member.getAddress()).append("\t");
            builder.append(member.getName()).append("\n");
        }
        System.out.println(builder);
        System.out.println("");
    }

    private void updateMessage(Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Message recu ---- ----\n");
        builder.append(LocalDateTime.now()).append(" \n");
        builder.append("Type : ").append(message.getType()).append("\n");
        builder.append("De : ").append(message.getAuthor()).append("\t");
        builder.append("Pour : ").append(message.getRecipient()).append("\n");
        builder.append("Contenu\t").append(message.getContent());
        builder.append("\n");
        System.out.println(builder);
    }

    @Override
    public void update(Observable o, Object arg) {
        updatePlayers();
        if (arg != null) {
            Message message = (Message) arg;
            updateMessage(message);
        }
    }

}
