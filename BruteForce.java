public class BruteForce {
    public int stringMatch(String pattern, String input) {
        int numMatches = 0;
        for (int i = 0; i < input.length(); i++) {
            boolean match = true;
            for (int j = 0; j < pattern.length(); j++) {
                int index = i + j;
                if (index > input.length()) {
                    match = false;
                    break;
                }
                if (input.charAt(i + j) != pattern.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                numMatches++;
            }
        }

        return numMatches;
    }
    public static void main(String args[]) {
        BruteForce bf = new BruteForce();
        int numMatches = bf.stringMatch("rain", "rain, rain, rain. I hate the rain.");
        System.out.println(numMatches);
    }

}