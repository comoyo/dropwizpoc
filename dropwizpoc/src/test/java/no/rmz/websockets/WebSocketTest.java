
package no.rmz.websockets;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.*;

/** XXX The idea here is to make an unit test that
 *      uses this client, sends a message to the server and gets
 *      an echo back.
 * @author rmz
 */
@ServerEndpoint("/websocket")
public class WebSocketTest {

    private final static Logger LOG = Logger.getLogger(WebSocketTest.class.getName());

    @OnMessage
    public void onMessage(final String message, final Session session) throws IOException,
            InterruptedException {
        checkNotNull(message);
        checkNotNull(session);
        LOG.info("User input: " + message);
        session.getBasicRemote().sendText("Hello world Mr. " + message);
        // Sending message to client each 1 second
        for (int i = 0; i <= 25; i++) {
            session.getBasicRemote().sendText(i + " Message from server");
            Thread.sleep(1000);
        }
    }

    @OnOpen
    public void onOpen() {
       LOG.info("Client connected");
    }

    @OnClose
    public void onClose() {
       LOG.info("Connection closed");
    }
}