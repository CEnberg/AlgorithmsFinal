import java.util.HashMap;

/**
 * An implementation of Horspool's Algorithm for string matching
 */
public class Horspool {
    HashMap<Character, Integer> shiftTable;
    int patternLength;
    int matches;

    /**
     * Finds all occurences of the pattern in the full input using Horspool's algorithm.
     * @param pattern
     * @param input
     * @return the number of matches found, as an integer
     */
    public int stringMatch(String pattern, String input) {
        matches = 0; // Resets matches to 0
        patternLength = pattern.length();
        shiftTable = generateShiftTable(pattern);

        int index = pattern.length() - 1;
        while (index < input.length()) {
            int temporaryIndex = index;
            // System.out.println("set index to: " + index);
            char c;
            for (int i = patternLength - 1; i >= 0; i--) { // Iterates backward over the pattern, so right-to-left over the string.
                c = input.charAt(temporaryIndex);
                // System.out.println("checking: " + c);
                if (c != pattern.charAt(i)) { // If the character doesn't match, shift accordingly.
                    index += getShift(c);
                    break;
                }
                if (i == 0) { // If the entire pattern matches, increase matches by 1, and shift the pattern 1 character right.
                    // System.out.println("match at index : " + index);
                    index++;
                    matches++;
                }
                temporaryIndex--;
            }
        }
        return matches;
    }

    /**
     * Generates a shift table used in Horspool's Algorithm,
     * based on how far each letter in the pattern is from the right side of the string
     * @param pattern
     * @return
     */
    private HashMap<Character, Integer> generateShiftTable(String pattern) {
        HashMap<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < pattern.length() - 1; i++) {
            table.put(pattern.charAt(i), patternLength - i - 1);
        }
        // System.out.println(table.toString());
        return table;
    }

    /**
     * Retrieves the associated shift for a given character in the string.
     * If it's not inside the shift table, the pattern length is returned.
     * @param character
     * @return
     */
    private int getShift(char character) {
        return shiftTable.getOrDefault(character, patternLength);
    }

    // public static void main(String[] args) {
    //     Horspool horspool = new Horspool();
    //     int numMatches = horspool.stringMatch("rain", "rain, rain, rain. I hate the rain.");
    //     System.out.println(numMatches);

        // System.out.println(horspool.matchString("ENDGAME", "ENDGAMEAVENGERSENDGAMEENDGAME:ENDGAMEENDGAME"));
        // // System.out.println(horspool.getShift('C'));
        // System.out.println(horspool.matchString("NAM", "A MAN, A PLAN, A CANAL, PANAMA!"));
        // System.out.println(horspool.matchString("CANDLE", "A MAN, A PLAN, A CANAL, PANAMA!"));
        // System.out.println(horspool.matchString("CANAL", "A MAN, A PLAN, A CANAL, PANAMA!"));

        // System.out.println(horspool.matchString("HOBBIT", "IN A HOLE IN THE GROUND THERE LIVED A HOBBIT."));
    // }
}