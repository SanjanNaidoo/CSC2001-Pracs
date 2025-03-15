/**
 * BST-based Knowledge Base implementation.
 */

 import java.io.*;
import java.util.*;

class BST {
    private Node root;

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
        root = addOrUpdate(root, newStatement);
    }

    private Node addOrUpdate(Node node, Statement statement) {
        if (node == null) {
            return new Node(statement);
        }
        if (statement.term.compareTo(node.statement.term) < 0) {
            node.left = addOrUpdate(node.left, statement);
        } else if (statement.term.compareTo(node.statement.term) > 0) {
            node.right = addOrUpdate(node.right, statement);
        } else {
            if (statement.confidence > node.statement.confidence) {
                node.statement = statement;
            }
        }
        return node;
    }

    public Statement searchByTerm(String term) {
        return search(root, term);
    }

    private Statement search(Node node, String term) {
        if (node == null) {
            return null;
        }
        if (term.equals(node.statement.term)) {
            return node.statement;
        }
        return term.compareTo(node.statement.term) < 0 ? search(node.left, term) : search(node.right, term);
    }
}
