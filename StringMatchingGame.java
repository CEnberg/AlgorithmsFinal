import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class StringMatchingGame {

    private static String[] solutions = {"BNDM", "Brute Force", "Horspool", "Rabin Karp"};
    private static String solutionCode;
    private static int[] matches;
    
    private static double[] generateTimes(String text, String pattern) {
        BNDM bndm = new BNDM();
        BruteForce bf = new BruteForce();
        Horspool hs = new Horspool();
        RabinKarp rk = new RabinKarp();

        double[] times = new double[4];
        matches = new int[4];

        long startTime, endTime; 

        startTime = System.currentTimeMillis();
        matches[0] = bndm.stringMatch(pattern, text);
        endTime = System.currentTimeMillis();
        times[0] = (endTime - startTime);

        startTime = System.currentTimeMillis();
        matches[1] = bf.stringMatch(pattern, text);
        endTime = System.currentTimeMillis();
        times[1] = (endTime - startTime);

        startTime = System.currentTimeMillis();
        matches[2] = hs.stringMatch(pattern, text);
        endTime = System.currentTimeMillis();
        times[2] = (endTime - startTime);

        startTime = System.currentTimeMillis();
        matches[3] = rk.rabinKarp(text, pattern);
        endTime = System.currentTimeMillis();
        times[3] = (endTime - startTime);

        shuffleTimes(times);
        return times;
    }

    private static void shuffleTimes(double[] times) {
        Random random = new Random();
        char[] code = {'c', 'j', 'e', 'a'};
        int n = 4;
        for (int i = n ; i > 1 ; i--) {
            int j = random.nextInt(i); // Randomization is exclusive
            double temp = times[i-1];
            times[i-1] = times[j];
            times[j] = temp;
            
            String stringTemp = solutions[i-1];
            solutions[i-1] = solutions[j];
            solutions[j] = stringTemp;

            char cTemp = code[i-1];
            code[i-1] = code[j];
            code[j] = cTemp;
        }

        createSolutionCode(code);
    }

    private static void createSolutionCode(char[] codeArray) {
        StringBuilder sb = new StringBuilder();
        for (char letter : codeArray) {
            sb.append(letter);
        }
        solutionCode = sb.toString();
    }

    private static boolean compareAnswers(String[] guesses) {
        boolean correct = true;
        for (int i = 0; i < 4; i++) {
            if (!guesses[i].equals(solutions[i])) {
                correct = false;
            }
        }
        return correct;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean continuePlay = true;

        while (continuePlay) {
            System.out.println("Enter a (long) string: ");
            String text = s.nextLine();
            System.out.println("Now enter a pattern: ");
            String pattern = s.nextLine();

            double[] times = generateTimes(text, pattern);
            System.out.println("Matches: " + Arrays.toString(matches));
            System.out.println("\nRandomization seed: " + solutionCode);
            System.out.println("Current times (in milliseconds): " + Arrays.toString(times));
            String[] answers = new String[4];

            boolean isCorrect = false;
            System.out.println("\n\nNow let's figure out which algorithm achieved which time!\n\n");
            while (!isCorrect) {
                System.out.println("Input guess for first time (BNDM, Brute Force, Horspool, Rabin Karp): ");
                answers[0] = s.nextLine();
                System.out.println("Input guess for second time (BNDM, Brute Force, Horspool, Rabin Karp): ");
                answers[1] = s.nextLine();
                System.out.println("Input guess for third time (BNDM, Brute Force, Horspool, Rabin Karp): ");
                answers[2] = s.nextLine();
                System.out.println("Input guess for final time (BNDM, Brute Force, Horspool, Rabin Karp): ");
                answers[3] = s.nextLine();
                isCorrect = compareAnswers(answers);
                if (isCorrect) {
                    System.out.println("\n\nYou got it right!\n");
                }
                else {
                    System.out.println("Wrong! Try again...\n\n");
                    System.out.println("Randomization seed: " + solutionCode);
                    System.out.println("\n\n\nCurrent times: " + Arrays.toString(times) + "\n");
                }
            }
            
            System.out.println("Play again? (Y/N): ");
            if (s.nextLine().equals("N")) {
                continuePlay = false;
            }
            else {
                System.out.println("");
            }
        }

        System.out.println("Hope you had fun! :))))))))");

        s.close();
    }
}