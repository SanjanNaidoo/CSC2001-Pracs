import java.io.*;
import java.util.*;

/**
 * Array-based Knowledge Base implementation.
 */
class KBArray {
    private Statement[] statements;
    private int size;

    public KBArray(int capacity) {
        statements = new Statement[capacity];
        size = 0;
    }

    public void loadFromFile(String filename) {
        File dataset = new File("src/" + filename);
        try {
            Scanner fileScanner = new Scanner(dataset);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    addOrUpdateStatement(new Statement(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
            fileScanner.close();
            System.out.println(filename + " has been added to the knowledge base.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public void addOrUpdateStatement(Statement newStatement) {
        for (int i = 0; i < size; i++) {
            if (statements[i].term.equals(newStatement.term)) {
                if (statements[i].confidence < newStatement.confidence) {
                    statements[i] = newStatement;
                }
                return;
            }
        }
        if (size < statements.length) {
            statements[size++] = newStatement;
        }
    }

    public Statement searchByTerm(String term) {
        for (int i = 0; i < size; i++) {
            if (statements[i].term.equals(term)) {
                return statements[i];
            }
        }
        return null;
    }
}

/**
 * Main application class for Array-based Knowledge Base.
 */
public class GenericsKbArrayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KBArray kb = new KBArray(1000);

        while (true) {
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for a statement in the knowledge base by term");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;
            if (input.matches("\\d")) {
                choice = Integer.parseInt(input);
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String filename = scanner.nextLine();
                    kb.loadFromFile(filename);
                    break;
                case 2:
                    System.out.print("Enter the term: ");
                    String term = scanner.nextLine();
                    System.out.print("Enter the statement: ");
                    String statement = scanner.nextLine();
                    System.out.print("Enter the confidence score: ");
                    try {
                        double confidence = Double.parseDouble(scanner.nextLine());
                        kb.addOrUpdateStatement(new Statement(term, statement, confidence));
                        System.out.println("Statement added or updated successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid confidence score. Please enter a valid number.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the term to search: ");
                    term = scanner.nextLine();
                    Statement result = kb.searchByTerm(term);
                    if (result != null) {
                        System.out.println("Statement found: " + result.sentence + " (Confidence score: " + result.confidence + ")");
                    } else {
                        System.out.println("No statement found for the term.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}






