import java.io.*;
import java.util.*;

public class GenericsKbBSTApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST kb = new BST();

        while (true) {
            System.out.println("\nChoose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for a statement in the knowledge base by term");
            System.out.println("4. Delete a statement by term");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > 5) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
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
                    System.out.print("Enter the term to delete: ");
                    term = scanner.nextLine().trim();
                    if (term.isEmpty()) {
                        System.out.println("Delete term cannot be empty.");
                        break;
                    }
                    kb.deleteTerm(term);;
                    System.out.println("Statement deleted if it existed.");
                    break;
                case 5:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


