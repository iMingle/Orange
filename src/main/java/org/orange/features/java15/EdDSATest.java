package org.orange.features.java15;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

/**
 * @author mingle
 */
public class EdDSATest {
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Ed25519");
        KeyPair kp = kpg.generateKeyPair();

        byte[] msg = "dayang".getBytes(StandardCharsets.UTF_8);

        Signature sig = Signature.getInstance("Ed25519");
        sig.initSign(kp.getPrivate());
        sig.update(msg);
        byte[] s = sig.sign();

        String encodedString = Base64.getEncoder().encodeToString(s);
        System.out.println(encodedString);
    }
}
