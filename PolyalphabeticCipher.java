//package encryption;

import java.util.ArrayList;
import java.util.Scanner;

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
public class PolyalphabeticCipher {
    
    private final int MAX = 10;
    
    private ArrayList<String> keys;
    private String key;
    private String cipherText;
    
    /**
     * Constructor initializes the instance variable keys
     */
    public PolyalphabeticCipher() {
        keys = new ArrayList<>();
    }
    
    /**
     * generates random keys and length MAX. The generated keys are stored
     * in the instance variable keys.
     */
    public void generatekeys() {
        
        for(int i = 0; i < MAX; i++) {
            keys.add(newKey());
        }
    }
    
    /**
     * returns the generated random keys.
     * @return ArrayList<String>
     */
    public ArrayList<String> getKeys() {
        return this.keys;
    }
    
    /**
     * returns the cipher text
     * @return String
     */
    public String getCipherText() {
            return this.cipherText;
    }
    
    /**
     * Encrypts a given text and a given key index in the keys list, and returns
     * an encrypted text.
     * @param plainText String
     * @param key integer
     * @return String
     */
    public String encrypt(String plainText, int key) {
        StringBuilder cipherText = new StringBuilder();
        this.key = keys.get(key);
        // iterate through the plain text and call the cipher method for each
        // character
        for(int i = 0; i < plainText.length(); i++) {
            cipherText.append(cipher(plainText.charAt(i), i % MAX));
        }
        // store the cipher text
        this.cipherText = cipherText.toString();
        
        return cipherText.toString();
    }
    
    /**
     * Encrypts a given text and a given a key, and returns an encrypted text.
     * @param plainText String
     * @param key String
     * @return String
     */
    public String encrypt(String plainText, String key) {
        StringBuilder cipherText = new StringBuilder();
        this.key = key;
        // iterate through the plain text and call the cipher method for each
        // character
        for(int i = 0; i < plainText.length(); i++) {
            cipherText.append(cipher(plainText.charAt(i), i % MAX));
        }
        // store the cipher text
        this.cipherText = cipherText.toString();
        
        return cipherText.toString();
    }
    
    private char cipher(char character, int index) {
        
        // set the mod and offset values depending on the character int value
        int mod = 32;
        int offset;
        if((int) character < 64) {
            offset = 32;
        } else if((int) character < 96) {
            offset = 64;
        } else {
            offset = 96;
        }
        // this is the cipher algorithm used we add the key character to
        // the plaintext character and calculate the modulo and adds an offset
        // depending on the character int value
        int c = Math.floorMod((int)character + (int)key.charAt(index), mod) + offset;
        
        return (char)c;
    }
    
    /**
     * Decrypts a given cipher text and a key index in the keys list, and returns
     * a the plain text.
     * @param cipherText String
     * @param key integer
     * @return String
     */
    public String decrypt(String cipherText, int key) {
        StringBuilder plainText = new StringBuilder();
        this.key = keys.get(key);
        // iterate through the plain text and call the decipher method for each
        // character
        for(int i = 0; i < cipherText.length(); i++) {
            plainText.append(decipher(cipherText.charAt(i), i % MAX));
        }
        
        return plainText.toString();
    }
    
    /**
     * Decrypts a given cipher text and a key, and returns a the plain text.
     * @param cipherText String
     * @param key String
     * @return String
     */
    public String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        this.key = key;
        // iterate through the plain text and call the decipher method for each
        // character
        for(int i = 0; i < cipherText.length(); i++) {
            plainText.append(decipher(cipherText.charAt(i), i % MAX));
        }
        
        return plainText.toString();
    }
    
    private char decipher(char character, int index) {
        
        // set the mod and offset values depending on the character int value
        int mod = 32;
        int offset;
        if((int) character < 64) {
            offset = 32;
        } else if((int) character < 96) {
            offset = 64;
        } else {
            offset = 96;
        }
        
        // this is the decipher algorithm used we substruct the key character
        // from the cipher character and calculate the modulo and adds an offset
        // depending on the character int value
        int c = Math.floorMod((int)character - (int)key.charAt(index), mod) + offset;
        
        return (char)c;
    }

    public String newKey() {
        return new KeyGenerator().generateNewKey();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PolyalphabeticCipher polyAlphaCipher = new PolyalphabeticCipher();
        // generate random keys
        polyAlphaCipher.generatekeys();
        // display the generated keys
        System.out.println("Generated Keys");
        int i = 1;
        for(String key: polyAlphaCipher.keys) {
            System.out.println(i + ". " + key);
            i++;
        }
        // prompt user for a key choice
        System.out.print("\tChose a key: ");
        // read user choice
        Scanner input = new Scanner(System.in);
        String userChoice = input.nextLine();
        // retrieve the chosen key
        String key = polyAlphaCipher.getKeys().get(Integer.parseInt(userChoice) - 1);
        System.out.println();
        // prompt user for plain text
        System.out.println("Enter your text: ");
        String plainText = input.nextLine();
        System.out.println();
        // encrypt the plain text
        System.out.println("Encrypted text using key: " + key);
        System.out.println(polyAlphaCipher.encrypt(plainText, key));
        System.out.println();
        // decrypt the plain text
        System.out.println("Decrypted text using key: " + key);
        System.out.println(polyAlphaCipher.decrypt(polyAlphaCipher.getCipherText(), key));
        System.out.println();
        
        
    }
    
}
