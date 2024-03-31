import static org.junit.jupiter.api.Assertions.*;

import com.client.RMIClient;
import org.junit.jupiter.api.Test;

public class RMIClientTest {

    @Test
    public void testClientConnectsToServer() {
        try {
            // Appeler la méthode main du client
            RMIClient.main(new String[]{});
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}


