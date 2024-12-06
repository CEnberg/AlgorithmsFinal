import java.util.ArrayList;
import java.util.*;

public class RabinKarp {
    static int a = 256;
    static int n = 101;
    static ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));

    public static ArrayList<Integer> hashList(String string){
        ArrayList<Integer> newList = new ArrayList<>();
        int k = string.length();
        int count = 1;
        System.out.println("Now hashing: " + string);
        for (int i = 0; i < k; i++) {
            String currentChar = string.substring(i, i+1);
            int stringValue = alphabet.indexOf(currentChar) + 1;
            int hashValue = ((stringValue * a^(k-count)) % n);
            System.out.println("adding: " + currentChar + " with a sval of: " + stringValue + " and a hash of: " + hashValue);
            newList.add(hashValue);
            count++;
        }
        return newList;
    }

    public static int listToValue(ArrayList<Integer> input, int start, int end){
        int total = 0;
        for (int i = start-1; i <= end-1; i++) {
            System.out.println("added: " + input.get(i));
            total = total + input.get(i);
        }
        System.out.println("total: " + total);
        return total;
    }



    public static int rabinKarp(String mainString, String pattern){
        int numOfMatches = 0;
        int n = mainString.length();
        int m = pattern.length();
        ArrayList<Integer> mainHash = hashList(mainString);
        ArrayList<Integer> patternHash = hashList(pattern);
        int hpattern = listToValue(patternHash, 1, m);
        System.out.println("hpattern = " + hpattern);
        for (int i = 1; i < (n - m + 1); i++) {
            System.out.println(mainString.substring(i-1, i+m-1));
            int hs = listToValue(mainHash, i, i+m-1);
            if (hs == hpattern){
                if (mainString.substring(i-1, i+m-2).equals(pattern)){
                    numOfMatches++;
                }
            }
        }
        return numOfMatches;
    }

    public static void main(String[] args) {
        System.out.println(rabinKarp("banana", "ana"));
    }
}
