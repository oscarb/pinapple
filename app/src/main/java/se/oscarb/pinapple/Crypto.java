package se.oscarb.pinapple;


/*
 * Interface for cryptographic actions as encrypting and decrypting messages
 */
public interface Crypto {

    // Encrypt and decrypt with key and message as int

    // Encrypt and decrypt with message as long
    long encrypt(long messagePlainText, int key);

    long decrypt(long messageEncrypted, int key);


}
