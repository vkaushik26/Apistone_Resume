package com.resume.utils;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncryptionAndDecryption {

    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec keySpec;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String encryptionKey;
    private String encryptionScheme;
    SecretKey key;

    public EncryptionAndDecryption() throws Exception {
    	encryptionKey = "ThisIsSpartaThisIsSparta";
    	encryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = encryptionKey.getBytes(UNICODE_FORMAT);
        keySpec = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(encryptionScheme);
        cipher = Cipher.getInstance(encryptionScheme);
        key = skf.generateSecret(keySpec);
    }
    
    /**
     * 
     * @param unencryptedString, take string
     * @return, an encrypted string
     */

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    /**
     * 
     * @param encryptedString, takes an encrypted string
     * @return, decrypted string
     */
    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

}


