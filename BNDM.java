import java.util.HashMap;

import static java.lang.Math.pow;

/**
 * A simple Backwards Nondeterministic DAWG (DIrected Acyclic Weighted Graph) Matching algorithm 
 * for detecting patterns in strings
 */
public class BNDM {

    /**
     * Finds all occurences of the given pattern within the given text,
     * using the BNDM approach.
     * Assumes the size of pattern is less than the size of text
     * @param pattern a (small) String object
     * @param text a (large) String object
     * @return the number of occurences, as an integer.
     */
    public int stringMatch(String pattern, String text) {
        int patternLength = pattern.length();
        HashMap<Character, Integer> bitmasks = new HashMap<>();
        setUpBitmask(pattern, text, bitmasks);

        int position = 0;
        int occurences = 0;

        while (position <= text.length() - patternLength) {
            int offset = patternLength;
            int lastSuffix = patternLength;
            int validSuffixes = (1 << patternLength) - 1; // initialize to 1111... in binary

            while ((validSuffixes != 0) && (validSuffixes < (1 << patternLength))) { 
            // While valid patterns are still possible, and we don't have a complete match.
                validSuffixes = validSuffixes & bitmasks.get(text.charAt(position+offset-1));
                offset--;
                if ((validSuffixes & ((1 << patternLength)-1)) != 0) { // If there is some match
                    if (offset > 0) {
                        lastSuffix = offset; //Ensures if match isn't fully in current window, it will be during the next loop
                    }
                    else {
                        occurences += 1;
                    }
                }
                validSuffixes *= 2; // validSuffixes << 1
            }
            position += lastSuffix;
        }

        return occurences;
    }

    /**
     * Helper method that sets up a bitmask for all characters present in the pattern and text.
     * If a character appears in the main text but not in the pattern, it's assigned 0.
     * Otherwise, it's assigned a number with corresponding bits similar to their positions in the pattern.
     * @param pattern
     * @param text
     * @param bitmask The bitmask to set up
     */
    private void setUpBitmask(String pattern, String text, HashMap<Character, Integer> bitmask) {
        for (char c : text.toCharArray()) {
            bitmask.put(c, 0);
        }
        int patternSize = pattern.length();
        int currentPos = (int) pow(2, patternSize - 1);
        for (char c : pattern.toCharArray()) {
            if (bitmask.containsKey(c)) {
                bitmask.replace(c, bitmask.get(c) + currentPos);
            }
            else {
                bitmask.put(c, 0);
            }
            currentPos /= 2; // currentPos >> 1
        }
    }

    // public static void main(String[] args) {
    //     BNDM bndm = new BNDM();
    //     String text = "rain, rain, rain. I hate the rain.";
    //     String pattern = "rain";
    //     System.out.println("Occurences found: " + bndm.stringMatch(pattern, text));
    // }
    
}