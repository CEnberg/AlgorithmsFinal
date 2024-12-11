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
            Integer temporaryIndex = index;
            // System.out.println("set index to: " + index);
            Character c = null;
            for (int i = patternLength - 1; i >= 0; i--) { // Iterates backward over the pattern, so right-to-left over the string.
                c = input.charAt(temporaryIndex);
                if (c != pattern.charAt(i)) { // If the character doesn't match, shift accordingly.
                    index += getShift(c);
                    break;
                }
                if (i == 0) { // If the entire pattern matches, increase matches by 1, and shift the pattern 1 character right.
                    index++;
                    matches++;
                }
                temporaryIndex--;
            }
        }
        return matches;
    }

    /**
     * Generates and returns a HashMap shift table for the given pattern.
     */
    private HashMap<Character, Integer> generateShiftTable(String pattern) {
        HashMap<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < pattern.length() - 1; i++) {
            table.put(pattern.charAt(i), patternLength - i - 1);
        }
        return table;
    }

    /**
     * Searches the shift table for the given character, also handles characters not in the table.
     */
    public Integer getShift(Character character) {
        return shiftTable.getOrDefault(character, patternLength);
    }

    public static void main(String[] args) {
        Horspool horspool = new Horspool();
        int numMatches = horspool.stringMatch("rain", "rain, rain, rain. I hate the rain.");
        System.out.println(numMatches);

        System.out.println(horspool.stringMatch("ENDGAME", "ENDGAMEAVENGERSENDGAMEENDGAME:ENDGAMEENDGAME"));
        System.out.println(horspool.stringMatch("NAM", "A MAN, A PLAN, A CANAL, PANAMA!"));
        System.out.println(horspool.stringMatch("CANDLE", "A MAN, A PLAN, A CANAL, PANAMA!"));
        System.out.println(horspool.stringMatch("CANAL", "A MAN, A PLAN, A CANAL, PANAMA!"));

        System.out.println(horspool.stringMatch("HOBBIT", "IN A HOLE IN THE GROUND THERE LIVED A HOBBIT."));
    }
}