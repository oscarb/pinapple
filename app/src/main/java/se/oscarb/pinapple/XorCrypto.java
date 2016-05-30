package se.oscarb.pinapple;

/*
 * Cryptographic actions using Xor
 */
public class XorCrypto implements Crypto {



    @Override
    public int encrypt(int key, int messagePlainText) {
        return key ^ messagePlainText;
    }

    @Override
    public int decrypt(int key, int messageEncrypted) {
        return key ^ messageEncrypted;
    }
}
