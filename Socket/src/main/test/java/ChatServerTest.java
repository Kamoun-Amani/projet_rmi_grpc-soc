import static org.junit.jupiter.api.Assertions.*;

import com.ChatServer;
import org.junit.jupiter.api.Test;

public class ChatServerTest {

    @Test
    public void testServerStartsSuccessfully() {
        try {
            // Simuler le démarrage du serveur
            ChatServer.main(new String[]{});
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
