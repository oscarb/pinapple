package se.oscarb.pinapple;

/*
 * Cryptographic actions using Xor
 */
public class XorCrypto implements Crypto {

    /*
        Encrypt
     */

    public long encrypt(long messagePlainText, long key) {
        return messagePlainText ^ repeatKey(key, getLength(messagePlainText));
    }


    public long encrypt(long messagePlainText, int key) {
        return messagePlainText ^ repeatKey(key, getLength(messagePlainText));
    }

    public long encrypt(String messagePlainText, String key) {
        return encrypt(Long.valueOf(messagePlainText), Integer.valueOf(key));
    }

    /*
        Decrypt
     */

    public long decrypt(long messagePlainText, int key) {
        return messagePlainText ^ repeatKey(key, getLength(messagePlainText));
    }

    public long decrypt(String messagePlainText, String key) {
        return encrypt(Long.valueOf(messagePlainText), Integer.valueOf(key));
    }

    /*
       Utilities
     */

    private static long repeatKey(int key, int length) {
        return repeatKey(String.valueOf(key), length);
    }

    private static long repeatKey(long key, int length) {
        return repeatKey(String.valueOf(key), length);
    }


    private static long repeatKey(String key, int length) {
        String paddedKey = key;
        for (int i = 0; paddedKey.length() < length; i++) {
            paddedKey = paddedKey + key.charAt(i % key.length());
        }
        return Long.valueOf(paddedKey);
    }

    private static int getLength(int number) {
        return String.valueOf(number).length();
    }

    private static int getLength(long number) {
        return String.valueOf(number).length();
    }


}
