package com.geektrust.tameofthrones;

import java.util.HashMap;
import java.util.Map;

/**
 * This class does the job of verifying if the emblem of kingdom is present in
 * the message.
 *
 * @author sushil
 */
public class MessageVerifier {
    private static final Character UPPERCASE_A = 'A';
    private static final Character LOWERCASE_A = 'a';
    private static final int TOTAL_ALPHABETS = 26;
    private static final char SPACE = ' ';

    /* ==================================
     * PRIVATE METHODS
     * ==================================
     */

    /**
     * Utility method to count the number of characters in a string and stores
     * it in hashmap
     *
     * @param string string for which count of characters needs to be calculated
     * @return map of Character to its occurrence
     */
    private static Map<Character, Integer> getCharCount(String string) {
        Map<Character, Integer> characterCountMap = new HashMap<>();
        int lengthOfStr = string.length();

        for (int i = 0; i < lengthOfStr; ++i) {
            Character ch = Character.toLowerCase(string.charAt(i));
            characterCountMap.compute(ch, (character, count) -> count == null ? 1 : count + 1);
        }
        return characterCountMap;
    }

    /**
     * For each character in emblem map, this method compares its count with the
     * count of the same character in message map returns true if the message
     * has at least the same count of characters as in emblem otherwise false
     *
     * @param emblemMap  count of each character in emblem
     * @param messageMap count of each character in message
     * @return true if all message contains at least same number of characters as in emblem, otherwise false
     */
    private static boolean compareCharacters(Map<Character, Integer> emblemMap, Map<Character, Integer> messageMap) {
        return emblemMap.keySet()
                .stream()
                .allMatch(key -> messageMap.containsKey(key) && messageMap.get(key) >= emblemMap.get(key));
    }

    /**
     * This method returns the decrypted letter by moving "key" letters anti-clockwise on the message wheel.
     *
     * @param msgChar character of the message
     * @param key     secret key used to get the encrypted character
     * @return encrypted character
     */
    private static char rotate(int msgChar, int key) {
        int rotatedCharIndex;
        char upperOrLowerCaseA = Character.isUpperCase(msgChar) ? UPPERCASE_A : LOWERCASE_A;
        rotatedCharIndex = (TOTAL_ALPHABETS + (msgChar - upperOrLowerCaseA - key)) % TOTAL_ALPHABETS;
        return (char) (upperOrLowerCaseA + rotatedCharIndex);
    }

    /* ========================================
     * PUBLIC METHODS
     * ========================================
     */

    /**
     * This method decrypts the message by doing reverse of Ceasar Cipher.
     *
     * @param message   message that needs to be decrypted
     * @param secretKey secret key with which message needs to be decrypted
     * @return decrypted message
     */
    public static String decryptMessage(String message, int secretKey) {
        StringBuilder decryptedMsg = new StringBuilder();
        message.chars()
                .filter(msgChar -> msgChar != SPACE)
                .mapToObj(msgChar -> rotate(msgChar, secretKey))
                .forEach(decryptedMsg::append);

        return decryptedMsg.toString();
    }

    /**
     * This method verifies the message by comparing counts of characters stored
     * in both the maps
     *
     * @param emblem  emblem of the kingdom that received the message
     * @param message message received by the kingdom
     * @return true if message contains all characters of emblem, otherwise false
     */
    public static boolean verify(String message, String emblem) {
        String decryptedMsg = MessageVerifier.decryptMessage(message, emblem.length());

        // store the count of all the characters of message
        Map<Character, Integer> charCountOfMsg = getCharCount(decryptedMsg);

        // store the count of all the characters of the emblem of kingdom
        Map<Character, Integer> charCountOfEmblem = getCharCount(emblem);

        return compareCharacters(charCountOfEmblem, charCountOfMsg);
    }
}