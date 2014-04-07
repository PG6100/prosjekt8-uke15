package no.nith.pg6100;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class ItemResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();

        //
        // Fjer kommentar for å få json støtte, pom.xml må også endres.
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * tester om vi får tilbake "Got it!" fra server.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("itemresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
