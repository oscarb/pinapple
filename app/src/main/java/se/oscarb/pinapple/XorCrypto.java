package se.oscarb.pinapple;

/*
 * Cryptographic actions using Xor
 */
public class XorCrypto implements Crypto {



    @Override
    public int encrypt(int messagePlainText, int key) {
        return key ^ messagePlainText;
    }

    @Override
    public long decrypt(long messageEncrypted, int key) {
        return key ^ messageEncrypted;
    }
}
