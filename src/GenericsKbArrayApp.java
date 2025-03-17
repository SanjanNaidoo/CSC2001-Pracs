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
        try (Scanner fileScanner = new Scanner(dataset)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    try {
                        double confidence = Double.parseDouble(parts[2]);
                        addOrUpdateStatement(new Statement(parts[0], parts[1], confidence));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid confidence score in file. Skipping line: " + line);
                    }
                } else {
                    System.out.println("Invalid data format. Skipping line: " + line);
                }
            }
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
        } else {
            System.out.println("Knowledge base is full. Cannot add more statements.");
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
        KBArray kb = new KBArray(20000);

        while (true) {
            System.out.println("\nChoose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for a statement in the knowledge base by term");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > 4) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String filename = scanner.nextLine().trim();
                    if (filename.isEmpty()) {
                        System.out.println("File name cannot be empty.");
                    } else {
                        kb.loadFromFile(filename);
                    }
                    break;
                case 2:
                    System.out.print("Enter the term: ");
                    String term = scanner.nextLine().trim();
                    if (term.isEmpty()) {
                        System.out.println("Term cannot be empty.");
                        break;
                    }
                    System.out.print("Enter the statement: ");
                    String statement = scanner.nextLine().trim();
                    if (statement.isEmpty()) {
                        System.out.println("Statement cannot be empty.");
                        break;
                    }
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
                    term = scanner.nextLine().trim();
                    if (term.isEmpty()) {
                        System.out.println("Search term cannot be empty.");
                        break;
                    }
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
