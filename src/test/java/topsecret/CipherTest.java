package topsecret;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CipherTest {
    @Test
    void decipher_lowercase() throws Exception {
        Path keyPath = Path.of("ciphers/key.txt");
        Cipher cipher = new Cipher(keyPath);

        assertEquals("hello", cipher.decipher("ifmmp"));
    }

    @Test
    void decipher_numbers() throws Exception {
        Path keyPath = Path.of("ciphers/key.txt");
        Cipher cipher = new Cipher(keyPath);

        assertEquals("123", cipher.decipher("234"));
    }

    @Test
    void invalidKey_throwsExecption() {
        Path badKey = Path.of("ciphers/key.txt");

        assertThrows(IllegalArgumentException.class, () -> {new Cipher(badKey);
        });
    }

    @Test
    void leaves_unknowns_unchaged() throws IOException {
        Path keyPath = Path.of("ciphers/key.txt");
        Cipher cipher = new Cipher(keyPath);

        assertEquals("hello!", cipher.decipher("ifmmp!"));
    }
}