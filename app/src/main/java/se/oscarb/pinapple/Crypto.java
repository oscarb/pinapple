package se.oscarb.pinapple;


/*
 * Interface for cryptographic actions as encrypting and decrypting messages
 */
public interface Crypto {

    // Encrypt and decrypt with key and message as int
    public int encrypt(int messagePlainText, int key);
    public int decrypt(int messageEncrypted, int key);


}
