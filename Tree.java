/**
 * This class represents an augmented binary search tree
 */
public class Tree {

    private TreeNode root;

    /**
     * Construct an empty tree
     */
    public Tree() {
        this.root = null;
    }

    /**
     * Find the node in the tree with the given key
     * @param keyToLookUp   The key to look up in the tree
     * @return              A reference to the node with the specific key
     *                      if such node exists, null otherwise
     */
    public TreeNode find(double keyToLookUp) {

        if (isEmpty()) {
            return null;
        }

        TreeNode currentNode = root;
        while (currentNode != null &&
                currentNode.getKey() != keyToLookUp) {
            if (currentNode.getKey() > keyToLookUp) {
                currentNode = currentNode.getLeft();
            }
            else {
                currentNode = currentNode.getRight();
            }
        }

        return currentNode;
    }

    /**
     * @return True if the tree is empty (has no nodes in it)
     */
    private boolean isEmpty() {
        return root == null;
    }
}
