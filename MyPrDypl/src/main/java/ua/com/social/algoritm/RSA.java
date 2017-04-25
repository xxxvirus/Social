package ua.com.social.algoritm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSA {

	public static KeyPair generateKeyPair(){
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPairGenerator.generateKeyPair();
    }

    public static String encrypt(String plaintext, PublicKey key){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes());
            return Base64.encodeBase64String(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String ciphertext, PrivateKey key){
        try {
        	Cipher cipher = Cipher.getInstance("RSA");
        	cipher.init(Cipher.DECRYPT_MODE, key);
        	byte[] plainText = cipher.doFinal(Base64.decodeBase64(ciphertext));
            return new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
