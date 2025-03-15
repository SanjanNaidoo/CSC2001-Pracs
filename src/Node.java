/**
 * Node class for BST.
 */
class Node {
    Statement statement;
    Node left, right;

    public Node(Statement statement) {
        this.statement = statement;
        this.left = null;
        this.right = null;
    }
}
