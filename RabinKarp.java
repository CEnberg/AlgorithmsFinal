import java.util.ArrayList;
import java.util.*;

/**
 * An implementation of the Rabin-Karp string matching algorithm
 */
public class RabinKarp {
    private static int a = 256;
    private static int n = 101;
    private static ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));

    /**
     * Creates a list of the numerical values of a given string. 
     * For example the string abc will return a list of [1,2,3] 
     * and the string zyx will return [26,25,24].
     * @param string
     * @return The numerical representation of string, in an ArrayList
     */
    private ArrayList<Integer> hashList(String string){
        ArrayList<Integer> newList = new ArrayList<>();
        int k = string.length();
        int count = 1;
        // System.out.println("Now hashing: " + string);
        for (int i = 0; i < k; i++) {
            String currentChar = string.substring(i, i+1);
            int stringValue = alphabet.indexOf(currentChar) + 1;
            // System.out.println("adding: " + currentChar + " with a sval of: " + stringValue);
            newList.add(stringValue);
            count++;
        }
        return newList;
    }

    /**
     * Creates a hash value of the numbers in a list from indexes start-1 to end-1.
     */
    private int listToValue(ArrayList<Integer> input, int start, int end){
        int total = 0;
        int count = 1;
        for (int i = start-1; i <= end-1; i++) {
            // System.out.println("added: " + input.get(i));
            int value = (input.get(i) * (a^(start-end-count))) % n;
            // System.out.println("added: " + value);
            total = total + value;
            count++;
        }
        // System.out.println("total: " + total);
        return total;
    }

    /**
     * Finds the number of occurences of the pattern in the main string,
     * using the rabin-Karp matching algorithm.
     * @param mainString A long string to search through
     * @param pattern A string of characters to search for in mainString
     * @return the number of matches found, as an integer.
     */
    public int rabinKarp(String mainString, String pattern){
        int numOfMatches = 0;
        int n = mainString.length();
        int m = pattern.length();
        ArrayList<Integer> mainHash = hashList(mainString);
        ArrayList<Integer> patternHash = hashList(pattern);
        int hpattern = listToValue(patternHash, 1, m);
        // System.out.println("hpattern = " + hpattern);
        for (int i = 1; i < (n - m + 1); i++) {
            // System.out.println(mainString.substring(i-1, i+m-1));
            int hs = listToValue(mainHash, i, i+m-1);
            if (hs == hpattern){
                if (mainString.substring(i-1, i+m-1).equals(pattern)){
                    numOfMatches++;
                }
            }
        }
        return numOfMatches;
    }

    // public static void main(String[] args) {
    //     System.out.println(rabinKarp("banana", "an")); //expected 2
    //     System.out.println(rabinKarp("my good friend olivia is going to ontario on monday.", "o")); //expected 9
    //     System.out.println(rabinKarp("my good friend olivia is going to ontario on monday.", "on")); //expected 3
    // }
}
