package myBlowfish;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

public class ApiFileCipherTest {

    @TempDir
    Path tempDir;

    @Test
    void testRead() throws Exception {
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, "Hello World");
        String content = ApiFileCipher.read(file.toString());
        assertEquals("Hello World", content);
    }

    @Test
    void testEncryptAndDecrypt() throws Exception {
        Key key = ApiBlowfish.generateKey();
        Path file = tempDir.resolve("plain.txt");
        Files.writeString(file, "Secret message");

        ApiFileCipher.encrypt(file.toString(), key);
        String decrypted = ApiFileCipher.decrypt(file + ".cryp", key);

        assertEquals("Secret message", decrypted);
    }

    @Test
    void testEncryptCreatesFile() throws Exception {
        Key key = ApiBlowfish.generateKey();
        Path file = tempDir.resolve("doc.txt");
        Files.writeString(file, "Contenu");

        ApiFileCipher.encrypt(file.toString(), key);

        assertTrue(Files.exists(tempDir.resolve("doc.txt.cryp")));
    }
}

