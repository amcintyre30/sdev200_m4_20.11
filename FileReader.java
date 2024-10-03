import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class FileReader {
    public static void main(String[] args) {
        // Ensure a filename is passed as an argument
        if (args.length != 1) {
            System.out.println("Usage: java FileReader <source-file>");
            System.exit(1);
        }

        String filename = args[0]; // Get the filename from the command line
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file); // Use Scanner to read the file

            Stack<Character> stack = new Stack<>(); // Stack to hold grouping symbols
            boolean isBalanced = true; // Track if grouping sybmols are balanced

            //Read the file line by line
            while (scanner.hasNextLine() && isBalanced) {
                String line = scanner.nextLine(); // Get each line from the file

                // Check each character in the line
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch); // Push opening symbols onto the stack
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty()) {
                            isBalanced = false; // No corresponding opening symbol
                            break;
                        }
                    }
                }
            }

            // If stack is not empty, there are unmatched opening symbols
            if (!stack.isEmpty()) {
                isBalanced = false;
            }

            // Print the result
            if (isBalanced) {
                System.out.println("Correct grouping pairs");
            } else {
                System.out.println("Incorrect grouping pairs");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    // Helper method to check if the symbols match
    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}