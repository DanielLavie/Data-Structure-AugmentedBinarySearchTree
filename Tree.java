import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
     *
     * @param keyToLookUp   The key to look up in the tree
     * @return              A reference to the node with the specific key
     *                      if such node exists, null otherwise
     */
    public TreeNode find(double keyToLookUp) {

        if (isEmpty()) {
            return null;
        }

        TreeNode currentNode = root;
        while (null != currentNode &&
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
     * Insert a given node in it's proper position in the tree, according to it's key
     *
     * @param newNode A reference to the node to be inserted into this tree
     */
    public void insert(TreeNode newNode) {

        if (null == newNode) {
            throw new RuntimeException("Please insert a valid tree node");
        }

        if (isEmpty()) {
            root = newNode;
            return;
        }

        // Find the correct place to insert the mew node

        TreeNode newNodeParent = null;
        TreeNode nodeIterator = root;

        while (null != nodeIterator) {
            newNodeParent = nodeIterator;
            if (newNode.getKey() < nodeIterator.getKey()) {
                nodeIterator = nodeIterator.getLeft();
            }
            else {
                nodeIterator = nodeIterator.getRight();
            }
        }

        // Insert the new node to it's correct place

        newNode.setParent(newNodeParent);
        if (newNode.getKey() < newNodeParent.getKey()) {
            newNodeParent.setLeft(newNode);
        }
        else {
            newNodeParent.setRight(newNode);
        }

        // Reset new node members
        newNode.setHeight(0);
        newNode.setSize(1);

        // Update size and height for the path of the new leaf
        nodeIterator = newNodeParent;
        int currentHeight = 1;
        while (null != nodeIterator) {
            nodeIterator.setSize(nodeIterator.getSize() + 1);

            if (nodeIterator.getHeight() < currentHeight) {
                nodeIterator.setHeight(currentHeight);
            }

            nodeIterator = nodeIterator.getParent();
        }
    }

    /**
     * Delete a node from this tree.
     * This function assumes that this node actually exists in the tree.
     *
     * @param deleteNode The node to be deleted
     */
    void delete (TreeNode deleteNode) {
        throw new NotImplementedException();
    }

    /**
     * Retrieve the node with the given rank in the tree.
     * rank 1 means the smallest node in the tree.
     *
     * @param requiredRank The rank to be found in the tree
     * @return A reference to the node with the given rank
     */
    TreeNode OSSelect (int requiredRank) {
        throw new NotImplementedException();
    }

    /**
     * Retrieve the rank of the given tree node.
     * This function assumes that this node actually exists in the tree.
     *
     * @param requiredNode The node to be searched for it's rank
     * @return The rank of the given node
     */
    int OSRank (TreeNode requiredNode) {
        throw new NotImplementedException();
    }

    /**
     * Check if a sub-tree of the this tree is balanced.
     * A balanced tree means - the height of the sub-tree root is less than or
     * equals to 2logN, when N is the number of nodes in the subtree.
     *
     * @param node The root of the sub tree to be checked for balance
     * @return True if the sub tree is balanced, false otherwise
     */
    boolean isBalanced (TreeNode node) {
        throw new NotImplementedException();
    }

    /**
     * @return True if the tree is empty (has no nodes in it), false otherwise
     */
    private boolean isEmpty() {
        return null == root;
    }
}
