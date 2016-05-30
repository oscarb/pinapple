package se.oscarb.pinapple;


/*
 * Interface for cryptographic actions as encrypting and decrypting messages
 */
public interface Crypto {

    // Encrypt and decrypt with key and message as int
    public int encrypt(int key, int messagePlainText);
    public int decrypt(int key, int messageEncrypted);


}
