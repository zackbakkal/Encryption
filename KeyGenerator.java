//package encryption;

import java.util.Random;

/**
 * COMP 400
 * Assignment 1
 * Project 2 
 * 
 * Class name: PolyalphabeticCipher.java
 * 
 * 
 * @author Zakaria
 * @date July-30-2018
 * S.ID: 3240240
 */
public class KeyGenerator {
    
    private final int MAX = 16;
    private final int INTERVAL_A = 126;
    private final int INTERVAL_B = 32;
    private final int OFFSET = 32;
    
    /**
     * Returns a random key of length MAX
     * @return String
     */
    public String generateNewKey() {
        // holds the generated key
        StringBuilder key = new StringBuilder();
        // used to generate random integers between 32 and 126
        Random random = new Random();
        // holds the random number generated from 32 to 126
        int character;
        // generate a random key of MAX characters
        for(int i = 0; i < MAX; i++) {
            character = random.nextInt(INTERVAL_A - INTERVAL_B) + OFFSET;
            key.append(Character.toString((char)character));        }
        
        return key.toString();
    }
}
