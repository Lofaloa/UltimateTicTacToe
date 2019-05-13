package atlg4.server.g47923;

import anagram.exception.ModelException;
import anagram.model.Facade;
import anagram.model.Model;
import atlg4.g47923.anagram.message.AnswerMessage;
import atlg4.g47923.anagram.message.EndGameMessage;
import atlg4.g47923.anagram.message.FailureMessage;
import atlg4.g47923.anagram.message.HintMessage;
import atlg4.g47923.anagram.message.HintRequestMessage;
import atlg4.g47923.anagram.message.LoginValidationMessage;
import atlg4.g47923.anagram.message.Message;
import atlg4.g47923.anagram.message.PassCurrentWordMessage;
import atlg4.g47923.anagram.message.PlayersMessage;
import atlg4.g47923.anagram.message.ProfileMessage;
import atlg4.g47923.anagram.message.ProposalMessage;
import atlg4.g47923.anagram.message.StatisticsMessage;
import atlg4.g47923.anagram.message.WordMessage;
import atlg4.g47923.anagram.players.GameStatistics;
import atlg4.g47923.anagram.players.Players;
import atlg4.g47923.anagram.players.User;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AnagramServer is used to handle the connections of the clients during a game
 * of Anagram.
 *
 * @author Logan Farci (47923)
 */
public class AnagramServer extends AbstractServer {

    private static final int PORT = 12_345;

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE,
                    "NetworkInterface error", e);
        }
        return null;
    }

    private int currentClientId;
    private final Players players;
    private final HashMap<Integer, Facade> games;

    /**
     * Constructs the server. Build a thread to listen connection request.
     *
     * @throws IOException if an I/O error occurs when creating the server
     * socket.
     */
    public AnagramServer() throws IOException, ModelException {
        super(PORT);
        this.currentClientId = 0;
        this.players = new Players();
        this.games = new HashMap<>();
        this.listen();
    }

    /**
     * Gets the server IP address.
     *
     * @return the server IP address.
     */
    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown";
        }
        return getLocalAddress().getHostAddress();
    }

    /**
     * Gets the players connected to this server.
     *
     * @return the players connected to this server.
     */
    public Players getPlayers() {
        return players;
    }

    /**
     * Return the number of connected users.
     *
     * @return the number of connected users.
     */
    public int getNbConnected() {
        return getNumberOfClients();
    }

    /**
     * Quits the server and closes all aspects of the connection to clients.
     *
     * @throws IOException
     */
    public void quit() throws IOException {
        this.stopListening();
        this.close();
    }

    /**
     * Return the next client id.
     *
     * @return the next client id.
     */
    final synchronized int getNextId() {
        currentClientId++;
        return currentClientId;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        switch (message.getType()) {
            case PROFILE:
                handle((ProfileMessage) message, client);
                break;
            case PROPOSAL:
                handle((ProposalMessage) message, client);
                break;
            case PASS_CURRENT_WORD:
                handle((PassCurrentWordMessage) message, client);
                break;
            case HINT_REQUEST:
                handle((HintRequestMessage) message, client);
            case WORD:
            case ANSWER:
            case PLAYERS:
            case STATISTICS:
            case FAILURE:
            case END_OF_GAME:
            case LOGIN_VALIDATION:
                break;
            default:
                throw new IllegalArgumentException("Message type unkown: "
                        + message.getType());
        }
        setChanged();
        notifyObservers(message);
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        int playerID = players.add(getNextId(), client.getName(), client.getInetAddress());
        client.setInfo("ID", playerID);
        sendToAllClients(new PlayersMessage(players));
        setChanged();
        notifyObservers();
    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        super.clientException(client, exception);
        // envoie d'exception au client
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        try {
            client.close();

            players.remove((int) client.getInfo("ID"));
            games.remove((int) client.getInfo("ID"));
            sendToAllClients(new PlayersMessage(players));
            setChanged();
            notifyObservers();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Impossible de déconnecter le client!", ex);
        }
    }

    void sendToClient(Message message, User recipient) {
        sendToClient(message, recipient.getId());
    }

    void sendToClient(Message message, int clientId) {
        Thread connection = null;
        for (Thread clientConnection : getClientConnections()) {
            ConnectionToClient client = (ConnectionToClient) clientConnection;
            if (((int) client.getInfo("ID")) == clientId) {
                connection = clientConnection;
            }
        }
        if (connection == null) {
            throw new IllegalArgumentException("The client with id " + clientId
                    + " does not exist.");
        } else {
            try {
                ((ConnectionToClient) connection).sendToClient(message);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    void startNewGameFor(ConnectionToClient client) {
        try {
            int clientId = (int) client.getInfo("ID");
            Facade game = new Model();
            game.initialize();
            game.start();
            games.put((int) clientId, game);
            Message firstWord = new WordMessage(
                    clientId,
                    client.getName(),
                    game.nextWord()
            );
            sendToClient(firstWord, clientId);
        } catch (ModelException e) {
            System.err.println(e.getMessage());
        }
    }

    private void updateNbSolvedWords() {
        try {
            for (User player : players) {
                Facade game = games.get(player.getId());
                player.setNbSolvedWords(game.getNbSolvedWords());
            }
        } catch (ModelException e) {
            System.err.println(e.getMessage());
        }
    }

    private void sendGameStatisticsToClient(ConnectionToClient client) {
        try {
            int clientId = (int) client.getInfo("ID");
            Facade game = games.get(clientId);
            GameStatistics statistics = new GameStatistics(
                    game.getNbWords(),
                    game.getNbRemainingWords(),
                    game.getNbSolvedWords(),
                    game.getNbUnsolvedWords(),
                    game.getNbProposal()
            );
            StatisticsMessage message = new StatisticsMessage(
                    statistics,
                    clientId,
                    client.getName()
            );
            sendToClient(message, clientId);
        } catch (ModelException ex) {
            clientException(client, ex);
        }
    }

    private void sendEndGameMessageToClient(ConnectionToClient client) {
        try {
            int clientId = (int) client.getInfo("ID");
            Message message = new EndGameMessage(
                    clientId,
                    client.getName(),
                    games.get(clientId).isOver(),
                    games.get(clientId).canAskNextWord()
            );
            sendToClient(message, clientId);
        } catch (ModelException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean isUsedLogin(String login) {
        int frequency = 0;
        for (User player : players) {
            if (login.equals(player.getName())) {
                frequency++;
            }
        }
        return frequency > 0;
    }

    private void handle(ProfileMessage message, ConnectionToClient client) {
        User author = message.getAuthor();
        boolean isUsedLogin = isUsedLogin(author.getName());
        if (!isUsedLogin) {
            int playerId = (int) client.getInfo("ID");
            players.changeName(author.getName(), playerId);
            Message messageName = new ProfileMessage(playerId, author.getName());
            sendToClient(messageName, playerId);
            sendToAllClients(new PlayersMessage(players));
            startNewGameFor(client);
        }
        Message validation = new LoginValidationMessage(
                (int) client.getInfo("ID"),
                author.getName(),
                !isUsedLogin
        );
        sendToClient(validation, (int) client.getInfo("ID"));
    }

    private void handle(ProposalMessage proposal, ConnectionToClient client) {
        // TODO: lors du dernier tour est-ce que si le joueur fait une mauvaise
        // proposition, le jeu se finit?
        System.out.println("");
        try {
            Facade game = games.get(proposal.getAuthor().getId());
            if (game.propose((String) proposal.getContent())) {
                WordMessage nextWord = new WordMessage(
                        proposal.getAuthor().getId(),
                        proposal.getAuthor().getName(),
                        game.nextWord());
                updateNbSolvedWords();
                sendToClient(nextWord, proposal.getAuthor().getId());
                sendToAllClients(new PlayersMessage(players));
            } else {
                FailureMessage failure = new FailureMessage(
                        (String) proposal.getContent(),
                        proposal.getAuthor().getId(),
                        proposal.getAuthor().getName()
                );
                sendToClient(failure, proposal.getAuthor().getId());
            }
            sendGameStatisticsToClient(client);
            sendEndGameMessageToClient(client);
        } catch (ModelException ex) {
            clientException(client, ex);
        }
    }

    private void handle(PassCurrentWordMessage msg, ConnectionToClient client) {
        try {
            Facade game = games.get(msg.getAuthor().getId());
            String answer = game.pass();
            String nextWord = game.nextWord();
            Message answerMessage = new AnswerMessage(
                    msg.getAuthor().getId(),
                    msg.getAuthor().getName(),
                    answer
            );
            Message wordMessage = new WordMessage(
                    msg.getAuthor().getId(),
                    msg.getAuthor().getName(),
                    nextWord
            );
            sendToClient(answerMessage, msg.getAuthor());
            sendToClient(wordMessage, msg.getAuthor());
            sendGameStatisticsToClient(client);
            sendEndGameMessageToClient(client);
        } catch (ModelException ex) {

        }
    }

    private void handle(HintRequestMessage message, ConnectionToClient client) {
        Facade game = games.get(message.getAuthor().getId());
        Character hint = game.getCurrentWord().charAt(0);
        Message hintMessage = new HintMessage(
                message.getAuthor().getId(),
                message.getAuthor().getName(),
                hint
        );
        sendToClient(hintMessage, message.getAuthor());
    }

}
