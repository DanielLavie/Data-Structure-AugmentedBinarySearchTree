import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class represents an augmented binary search tree
 */
public class Tree {

    private TreeNode root;

    /**
     * Construct an empty tree
     */
    Tree() {
        this.root = null;
    }

    /**
     * Find the node in the tree with the given key
     *
     * @param keyToLookUp   The key to look up in the tree
     * @return              A reference to the node with the specific key
     *                      if such node exists, null otherwise
     */
    TreeNode find(double keyToLookUp) {

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
    void insert(TreeNode newNode) {

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

            // Only one node with the same value can exist. Return.
            if (newNode.getKey() == nodeIterator.getKey()) {
                return;
            }

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
            ++currentHeight;
        }
    }

    /**
     * Delete a node from this tree.
     * This function assumes that this node actually exists in the tree.
     *
     * @param deleteNode The node to be deleted
     */
    void delete (TreeNode deleteNode) {
        // Node is null, nothing to do.
        if (deleteNode == null)
            return;

        // Check if the given node has two child's, to decide if the
        // successor should be found
        TreeNode referenceNode;
        if (hasAtMostOneChild(deleteNode)) {
            referenceNode = deleteNode;
        }
        else {
            referenceNode = findSuccessor(deleteNode);
        }

        // At this point, reference node should have at most 1 child
        TreeNode referenceNodeChild;
        if (null != referenceNode.getLeft()) {
            referenceNodeChild = referenceNode.getLeft();
        }
        else {
            referenceNodeChild = referenceNode.getRight();
        }

        // Set the child node parent to the old parent of reference node
        if (null != referenceNodeChild) {
            referenceNodeChild.setParent(referenceNode.getParent());
        }

        // Handle the case we deleted the last node in the tree
        if (null == referenceNode.getParent()) {
            root = referenceNodeChild;
        }
        // Check if reference node is left or right child of it's parent
        else if (referenceNode == referenceNode.getParent().getLeft()) {
            referenceNode.getParent().setLeft(referenceNodeChild);
        }
        else {
            referenceNode.getParent().setRight(referenceNodeChild);
        }

        // Check if we need to update the node key after swapping
        if (referenceNode != deleteNode) {
            deleteNode.setKey(referenceNode.getKey());
        }

        // Update tree size and height according to the deletion
        TreeNode nodeIterator = referenceNode.getParent();
        int heightCounter = referenceNode.getHeight();
        while (null != nodeIterator) {

            if (hasAtMostOneChild(nodeIterator)) {
                nodeIterator.setHeight(nodeIterator.getHeight() - 1);
            }

            else if (nodeIterator.getOtherChild(referenceNode).getHeight() <
                    heightCounter) {
                nodeIterator.setHeight(nodeIterator.getHeight() - 1);
            }

            nodeIterator.setSize(nodeIterator.getSize() - 1);

            referenceNode = nodeIterator;
            nodeIterator = nodeIterator.getParent();
            ++heightCounter;
        }
    }

    /**
     * @return True if node has at most one child, false otherwise
     */
    private boolean hasAtMostOneChild(TreeNode node) {
        return (null == node.getLeft() || null == node.getRight());
    }

    /**
     * Returns the successor of the given node.
     * This function assumes that the given node is in the tree.
     *
     * @param node A reference to a node in the tree, The successor will be
     *             found according to these node.
     * @return A reference to the successor node
     */
    private TreeNode findSuccessor(TreeNode node) {

        if (null != node.getRight()) {

            // In case we need to find the minimum node in the right sub-tree,
            return getMinimumNode(node.getRight());
        }
        else {

            // In case we need to find the lowest ancestor of node
            // whose left sub-tree contains node

            TreeNode nodeParent = node.getParent();
            while (null != nodeParent &&
                    null != nodeParent.getRight() &&
                    node == nodeParent.getRight()) {
                node = nodeParent;
                nodeParent = nodeParent.getParent();
            }
            return nodeParent;
        }
    }

    /**
     * Returns the minimum node in the tree.
     *
     * @param node The root of the tree to searched
     * @return A refrence to the minimum node
     */
    private TreeNode getMinimumNode(TreeNode node) {

        while (null != node.getLeft()) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * A helper function for OSSelect to retrieve the node with the given rank in the tree.
     * rank 1 means the smallest node in the tree.
     *
     * @param requiredRank The rank to be found in the tree
     * @param node The current node to check
     * @return A reference to the node with the given rank
     */
    private TreeNode OSSelect(int requiredRank, TreeNode node) {
        if (node == null) return null;

        int rank;
        if (node.getLeft() != null) {
            rank = node.getLeft().getSize() + 1;
        }
        else {
            rank = 0;
        }


        if (requiredRank == rank) {
            return node;
        }
        else if (requiredRank < rank) {
            return OSSelect(requiredRank, node.getLeft());
        }
        else {
            return OSSelect(requiredRank - rank, node.getRight());
        }
    }

    /**
     * Retrieve the node with the given rank in the tree.
     * rank 1 means the smallest node in the tree.
     *
     * @param requiredRank The rank to be found in the tree
     * @return A reference to the node with the given rank
     */
    public TreeNode OSSelect(int requiredRank) {
        return OSSelect(requiredRank, root);
    }

    /**
     * Retrieve the rank of the given tree node.
     * This function assumes that this node actually exists in the tree.
     *
     * @param requiredNode The node to be searched for it's rank
     * @return The rank of the given node
     */
    public int OSRank(TreeNode requiredNode) {
        int rank;
        if (requiredNode.getLeft() != null) {
            rank = requiredNode.getLeft().getSize() + 1;
        }
        else {
            rank = 0;
        }

        TreeNode current = requiredNode;
        while (current != root) {
            if (current == current.getParent().getRight()) {
                rank = rank + current.getLeft().getSize() + 1;
            }
            current = current.getParent();
        }
        return rank;
    }

    /**
     * Helper function that calculates balance with heights.
     *
     * @param currentNode The root of the sub tree to be checked for balance
     * @return True if the sub tree is balanced, false otherwise
     */
    private int checkBalance(TreeNode currentNode)
    {
        if (currentNode == null)
        {
            return 0;
        }

        // Check if left sub-tree is balanced
        int leftSubtreeHeight = checkBalance(currentNode.getLeft());
        if (leftSubtreeHeight == -1) return -1;

        // Check if right sub-tree is balanced
        int rightSubtreeHeight = checkBalance(currentNode.getRight());
        if (rightSubtreeHeight == -1) return -1;

        // If both sub-trees are balanced, check the difference of heights
        // should be less than or equal to 2logN
        if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 2 * Math.log(currentNode.getSize()))
        {
            return -1;
        }

        // If tree rooted at this node is balanced, return height.
        return (Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1);
    }

    /**
     * Check if a sub-tree of the this tree is balanced.
     * A balanced tree means - the height of the sub-tree root is less than or
     * equals to 2logN, when N is the number of nodes in the subtree.
     *
     * @param node The root of the sub tree to be checked for balance
     * @return True if the sub tree is balanced, false otherwise
     */
    public boolean isBalanced(TreeNode node) {
        return checkBalance(node) > 0;
    }

    /**
     * @return True if the tree is empty (has no nodes in it), false otherwise
     */
    private boolean isEmpty() {
        return null == root;
    }
}
