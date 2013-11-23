package br.com.siec.resource;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * <p> Criptografa dados com um dos algoritimos definidos: SHA1, SHA256 e AES
 * 128. </p>
 *
 * @version 1.00 August 19, 2013
 * @author Josimar Alves
 */
public class EncryptData {

    private static final String ALGO = "AES";
    private static final byte[] keyValueAES = new byte[]{'S', 'o', 'S', 'e', 'i', 'Q', 'u',
        'e', 'N', 'a', 'd', 'a', 'S', 'e', 'i', '!'};

    public static String encryptSHA(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(data.getBytes());
            return bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Impossivel criptografar. " + e);
            return null;
        }
    }

    public static String encryptSHA256(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Impossivel criptografar. " + e);
            return null;
        }

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    public static String encryptAES128(String Data) throws Exception {
        try {
            Key key = generateKeyAES128();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encVal);
            return encryptedValue;
        } catch (Exception e) {
            System.out.println("Impossivel criptografar. " + e);
            return null;
        }
    }

    public static String decryptAES128(String encryptedData) {
        try {
            Key key = generateKeyAES128();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception e) {
            System.out.println("Impossivel criptografar. " + e);
            return null;
        }
    }

    private static Key generateKeyAES128() throws Exception {
        Key key = new SecretKeySpec(keyValueAES, ALGO);
        return key;
    }
}
