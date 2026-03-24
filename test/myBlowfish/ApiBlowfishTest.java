package myBlowfish;

import org.junit.jupiter.api.Test;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

public class ApiBlowfishTest {

    @Test
    void testGenerateKey() throws Exception {
        Key key = ApiBlowfish.generateKey();
        assertNotNull(key);
        assertEquals("Blowfish", key.getAlgorithm());
    }

    @Test
    void testEncryptDecryptBytes() throws Exception {
        Key key = ApiBlowfish.generateKey();
        String original = "Hello World";
        byte[] encrypted = ApiBlowfish.encryptInByte(original.getBytes(), key);
        byte[] decrypted = ApiBlowfish.decryptInByte(encrypted, key);
        assertEquals(original, new String(decrypted));
    }

    @Test
    void testEncryptDecryptString() throws Exception {
        Key key = ApiBlowfish.generateKey();
        String original = "LA VIE EST BELLE !!!";
        String encrypted = ApiBlowfish.encryptInString(original, key);
        String decrypted = ApiBlowfish.decryptInString(encrypted, key);
        assertEquals(original, decrypted);
    }

    @Test
    void testEncryptedDiffersFromPlain() throws Exception {
        Key key = ApiBlowfish.generateKey();
        String original = "test";
        String encrypted = ApiBlowfish.encryptInString(original, key);
        assertNotEquals(original, encrypted);
    }
}

