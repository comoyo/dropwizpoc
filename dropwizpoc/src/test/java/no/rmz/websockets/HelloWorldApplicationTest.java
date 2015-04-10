package no.rmz.websockets;

import static junit.framework.Assert.assertEquals;

import no.rmz.dropwizpoc.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.*;
import org.glassfish.jersey.test.*;
import org.junit.*;



/**
 * Just testing that the test framework is working.
 * This is the kind of test that should be deleted
 * once we're sure that the test harness
 * is actually doing its thing.
 */
public final class HelloWorldApplicationTest extends JerseyTest {


    @Override
    protected ResourceConfig configure() {
        return new ResourceConfig(HelloWorldApplication.class);
    }

    @Test
    public void test() {
        final Response response = target("hello-world").request().get();
        // final String hello = target("hello-world").request().get(String.class);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        final Object entity = response.getEntity();
        assertEquals("Hello World!", entity);
    }
}
