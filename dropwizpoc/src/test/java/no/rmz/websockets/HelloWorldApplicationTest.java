package no.rmz.websockets;

import static junit.framework.Assert.assertEquals;

import javax.ws.rs.*;

import org.glassfish.jersey.server.*;
import org.glassfish.jersey.test.*;

import com.sun.jersey.test.framework.*;


/**
 * Just testing that the test framework is working.
 * This is the kind of test that should be deleted
 * once we're sure that the test harness
 * is actually doing its thing.
 */
public class HelloWorldApplicationTest extends JerseyTest {

    public HelloWorldApplicationTest() throws Exception {
        super();
    }

    @Path("hello")
    public  static class HelloResource {

        @GET
        public String getHello() {
            return "Hello World!";
        }
    }

    @Override
    protected ResourceConfig configure() {
        return new ResourceConfig(HelloResource.class);
    }

    // @Test
    public void test() {
        final String hello = target("hello").request().get(String.class);
        assertEquals("Hello World!", hello);
    }
}
