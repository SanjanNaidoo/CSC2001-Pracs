/**
 * Node class to be used in BST class
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
