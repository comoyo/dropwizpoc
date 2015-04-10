package no.rmz.websockets;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.*;

import com.google.common.base.*;

/**
 * @ServerEndpoint gives the relative name for the end point This will be
 * accessed via ws://localhost:8080/EchoChamber/echo Where "localhost" is the
 * address of the host, "EchoChamber" is the name of the package and "echo" is
 * the address to access this class from the server
 */
@ServerEndpoint("/echo")
public final class EchoServer {

    private final static Logger LOG = Logger.getLogger(EchoServer.class.getName());

    /**
     * @param session
     * @OnOpen allows us to intercept the creation of a new session. The session
     * class allows us to send data to the user. In the method onOpen, we'll let
     * the user know that the handshake was successful.
     */
    @OnOpen
    public void onOpen(final Session session) {
        Preconditions.checkNotNull(session);
        LOG.info(session.getId() + " has opened a connection");
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            LOG.log(Level.ERROR, "Couldn't open session", ex);
        }
    }

    /**
     * When a user sends a message to the server, this method will intercept the
     * message and allow us to react to it. For now the message is read as a
     * String.
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(final String message, final Session session) {
        Preconditions.checkNotNull(message);
        Preconditions.checkNotNull(session);
        LOG.info("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            LOG.log(Level.ERROR, "Couldn't react to incoming message", ex);
        }
    }

    /**
     * The user closes the connection.
     *
     * Note: you can't send messages to the client from this method
     *
     * @param session
     */
    @OnClose
    public void onClose(final Session session) {
        Preconditions.checkNotNull(session);
        LOG.info("Session " + session.getId() + " has ended");
    }
}
