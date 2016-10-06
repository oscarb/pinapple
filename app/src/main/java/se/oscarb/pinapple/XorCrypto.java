package se.oscarb.pinapple;

/*
 * Cryptographic actions using Xor
 */
public class XorCrypto implements Crypto {



    @Override
    public int encrypt(int messagePlainText, int key) {
        return key ^ messagePlainText;
    }

    public int encrypt(String messagePlainText, String key) {

        return encrypt(Integer.valueOf(messagePlainText), Integer.valueOf(paddedKey))
    }

    @Override
    public long decrypt(long messageEncrypted, int key) {
        return key ^ messageEncrypted;
    }


    private static String repeatKey(String key, int length) {
        String paddedKey = key;
        for (int i = 0; paddedKey.length() < length; i++) {
            paddedKey = key.charAt(key.length() - 1 - i % key.length()) + paddedKey;
        }
        return paddedKey;
    }
}
