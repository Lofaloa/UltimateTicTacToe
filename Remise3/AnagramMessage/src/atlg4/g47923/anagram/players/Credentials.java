package atlg4.g47923.anagram.players;

/**
 * Represents the credentials of a user.
 *
 * @author Logan Farci (47923)
 */
public class Credentials {

    private final String login;
    private final String host;
    private final int port;

    /**
     * Constructs a new Credentials with the specified login, host and port.
     *
     * @param login is the specified login.
     * @param host is the specified host.
     * @param port is the specified port.
     */
    public Credentials(String login, String host, int port) {
        this.login = login;
        this.host = host;
        this.port = port;
    }

    /**
     * Gets the login of this credentials.
     * 
     * @return the login of this credentials.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets the host of this credentials.
     * 
     * @return the host of this credentials.
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the port of this credentials.
     * 
     * @return the port of this credentials.
     */
    public int getPort() {
        return port;
    }

}
