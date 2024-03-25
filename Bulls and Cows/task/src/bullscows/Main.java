package bullscows;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        
        int lengthOfSecretCode = 0;
        
        String test = scanner.nextLine();
        
        if (test.matches("\\d+")) {
            lengthOfSecretCode = Integer.parseInt(test);
        } else {
            System.out.println("Error");
            System.exit(0);
        }
        

        System.out.println("Input the number of possible symbols in the code:");

        int numberOfSymbols = 0;

        String test2 = scanner.nextLine();

        if (test2.matches("\\d+")) {
            numberOfSymbols = Integer.parseInt(test2);
        } else {
            System.out.println("Error");
            System.exit(0);
        }
        if (numberOfSymbols < lengthOfSecretCode || numberOfSymbols > 36 || lengthOfSecretCode > 9 || lengthOfSecretCode <= 0) {
            System.out.println("Error");
            System.exit(0);
        }
        grader(randomNumberGenerator(lengthOfSecretCode, numberOfSymbols));    }
    public static String randomNumberGenerator(int input, int range) {

        StringBuilder secretCode = new StringBuilder();
        String possibleChars = "0123456789abcdefghijklmnopqrstuvwxyz";
        List<Character> listOfChars = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            listOfChars.add(possibleChars.charAt(i));
        }
        char used = possibleChars.charAt(range - 1);
        Collections.shuffle(listOfChars);
        StringBuilder charsForCode = new StringBuilder();
        for (char c : listOfChars) {
            charsForCode.append(c);
        }
        for (int i = 0; secretCode.length() < input; i++) {
            secretCode.append(charsForCode.charAt(i));
        }
        String codeLength = "";
        for (int i = 0; i < secretCode.length(); i++) {
            codeLength = codeLength + "*";
        }
        int max = charsForCode.length() - 1;
        System.out.println("The secret is prepared: " + codeLength + (range < 11 ? " (0-" + max + ")" : " (0-9, a-" + used + ").") );
        return secretCode.toString();
    }
    public static void grader(String secretCode) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Okay, let's start a game!");
        int turnCounter = 1;
        while (true) {
            int bulls = 0;
            int cows = 0;
            System.out.println("Turn " + turnCounter + ":");
            String guess = scanner.next();

            int guessLength = guess.length();
            int codeLength = secretCode.length();

            if (guessLength > codeLength) {
                System.out.println("Error");
                System.exit(0);
            }


            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == secretCode.charAt(i)) {
                    bulls++;
                } else if (guess.contains(String.valueOf(secretCode.charAt(i)))) {
                    cows++;
                }
            }
            String bullText = (bulls == 1) ? "bull" : "bulls";
            String cowText = (cows == 1) ? "cow" : "cows";
            if (bulls == 0 && cows == 0) {
                System.out.println("Grade: None " + bulls + " " + bullText + " and " + cows + " " + cowText);
            } else if (bulls == secretCode.length()) {
                System.out.println("Grade: " + bulls + " " + bullText);
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            } else {
                System.out.println("Grade: " + bulls + " " + bullText + " and " + cows + " " + cowText);
            }
            turnCounter += 1;
        }
    }
    public static void main(String[] args) {
        start();
    }
}