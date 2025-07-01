package com.secusociale.portail.service;

import com.secusociale.portail.service.helpers.EnvService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.*;

public final class SecurityService {

    public static final String SECRAND_ALGO = "SHA1PRNG";
    public static final String SECRAND_PROVIDER = "SUN";

    public static final String DELIMITER = "#";
    public static String sault = EnvService.sault == null ? "fUjXn2r4u7x!A%D*" : EnvService.sault;

    public static String encrypt(String strClearText, String strKey) throws Exception {
        String strData = "";
        strKey = !StringUtils.isEmpty(strKey) ? strKey : "fUjXn2r4u7x!A%D*";

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes());
            strData = new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

    public static String decrypt(String strEncrypted, String strKey) throws Exception {
        String strData = "";
        strKey = !StringUtils.isEmpty(strKey) ? strKey : "fUjXn2r4u7x!A%D*";
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            decryptAES(strEncrypted, skeyspec);
            byte[] decrypted = cipher.doFinal(strEncrypted.getBytes());
            strData = new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }

    /**
     * Proceed to an encryption/decryption by block
     *
     * @param bytes  the bytes to encrypt
     * @param mode   encryption or decryption
     * @param cipher the cipher used
     * @return
     * @throws Exception
     */
    private static byte[] _blockCipher(byte[] bytes, int mode, Cipher cipher, int keySize) throws IllegalBlockSizeException,
        BadPaddingException, IOException {
        //Compute block length following key size
        int length;
        if (keySize == 2048)
            length = (mode == Cipher.ENCRYPT_MODE) ? 245 : 256;
        else
            length = (mode == Cipher.ENCRYPT_MODE) ? 100 : 128;
        //Begin encrypting by block
        byte[] scrambled = new byte[0];
        byte[] toReturn = new byte[0];
        byte[] buffer = new byte[length];
        for (int i = 0; i < bytes.length; i++) {
            if ((i > 0) && (i % length == 0)) {
                scrambled = cipher.doFinal(buffer);
                toReturn = _append(toReturn, scrambled);
                int newlength = length;
                if (i + length > bytes.length) {
                    newlength = bytes.length - i;
                }
                buffer = new byte[newlength];
            }
            buffer[i % length] = bytes[i];
        }
        scrambled = cipher.doFinal(buffer);
        toReturn = _append(toReturn, scrambled);
        return toReturn;
    }

    private static byte[] _append(byte[] prefix, byte[] suffix) {
        byte[] toReturn = new byte[prefix.length + suffix.length];
        for (int i = 0; i < prefix.length; i++) {
            toReturn[i] = prefix[i];
        }
        for (int i = 0; i < suffix.length; i++) {
            toReturn[i + prefix.length] = suffix[i];
        }
        return toReturn;
    }

    public static String asymEncrypt(String algo, String plaintext, PublicKey cle) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.ENCRYPT_MODE, cle);
        byte[] bytes = plaintext.getBytes("UTF-8");
        byte[] encrypted = _blockCipher(bytes, Cipher.ENCRYPT_MODE, cipher, cle.getEncoded().length);
        return Hex.encodeHexString(encrypted);
    }

    public static String asymDecrypt(String algo, String encrypted, PrivateKey cle) throws NoSuchPaddingException,
        NoSuchAlgorithmException, InvalidKeyException, DecoderException, BadPaddingException, IllegalBlockSizeException,
        IOException {
        Cipher cipher = Cipher.getInstance(algo);
        cipher.init(Cipher.DECRYPT_MODE, cle);
        byte[] bts = Hex.decodeHex(encrypted.toCharArray());
        byte[] decrypted = _blockCipher(bts, Cipher.DECRYPT_MODE, cipher, cle.getEncoded().length);
        return new String(decrypted, "UTF-8");
    }

    public static String encryptAES(String plaintext, SecretKey cle) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(Algorithms.AES_CBC_PKCS5PADDING_128.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, cle);

        byte[] encrypted = cipher.doFinal(plaintext.getBytes());

        String ivStr = Hex.encodeHexString(cipher.getIV());
        return ivStr + DELIMITER + Hex.encodeHexString(encrypted);
    }

    public static String decryptAES(String encrypted, SecretKey key) throws Exception {
        String[] parts = encrypted.split(DELIMITER);
        if (parts.length < 2) throw new Exception("Bad encryption format");

        byte[] iv = Hex.decodeHex(parts[0].toCharArray());
        String encryptedHex = encrypted.substring(parts[0].length() + 1);

        Cipher cipher = Cipher.getInstance(Algorithms.AES_CBC_PKCS5PADDING_128.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        byte[] decryptedBytes = cipher.doFinal(Hex.decodeHex(encryptedHex.toCharArray()));

        return new String(decryptedBytes, "UTF-8");
    }

    public static String decrypText(String encryptedText) throws Exception {
        if (sault == null) {
            throw new Exception("No crypto key is defined");
        }
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(sault);
            encryptor.setStringOutputType(CommonUtils.STRING_OUTPUT_TYPE_HEXADECIMAL);
            String plainText = encryptor.decrypt(encryptedText);
            return plainText;
        }
        // if the decryption operation fails, ommitting any further information about the cause for security reasons.
        catch (EncryptionOperationNotPossibleException ex) {

            throw new Exception("Impossible de décrypter le texte");
        }
        // if initialization could not be correctly done (for example, no password has been set).
        catch (EncryptionInitializationException ex) {
            throw new Exception("Impossible de décrypter le texte");
        }
    }

    public static String encrypText(String plainText) throws Exception {
        if (sault == null) {
            throw new Exception("No crypto key is defined");
        }
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(sault);
            encryptor.setStringOutputType(CommonUtils.STRING_OUTPUT_TYPE_HEXADECIMAL);
            String encryptedText = encryptor.encrypt(plainText);
            return encryptedText;
        }
        // if the decryption operation fails, ommitting any further information about the cause for security reasons.
        catch (EncryptionOperationNotPossibleException ex) {
            throw new Exception("Impossible d'encrypter le texte");
        }
        // if initialization could not be correctly done (for example, no password has been set).
        catch (EncryptionInitializationException ex) {
            throw new Exception("Impossible d'encrypter le texte");
        }

    }
}
