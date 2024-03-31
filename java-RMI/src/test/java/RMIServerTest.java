import static org.junit.jupiter.api.Assertions.*;

import com.server.RMIServer;
import org.junit.jupiter.api.Test;

public class RMIServerTest {

    @Test
    public void testServerStartsSuccessfully() {
        try {
            // Créer une instance de RMIServer
            RMIServer rmiServer = new RMIServer();

            // Appeler la méthode main du serveur
            RMIServer.main(new String[]{});
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}

