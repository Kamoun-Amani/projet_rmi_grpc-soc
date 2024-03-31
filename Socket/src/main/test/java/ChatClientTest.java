import static org.junit.jupiter.api.Assertions.*;

import com.ChatClient;
import org.junit.jupiter.api.Test;

public class ChatClientTest {

    @Test
    public void testClientConnectsToServer() {
        try {
            // Simuler la connexion du client au serveur
            ChatClient.main(new String[]{});
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}

