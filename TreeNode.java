/**
 * This class represents a node in augmented binary search tree
 */
class TreeNode {

    private double key;

    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    private int size;
    private int height;

    TreeNode(double key) {
        this.key = key;
    }

    double getKey() {
        return key;
    }

    void setKey(double key) {
        this.key = key;
    }

    TreeNode getLeft() {
        return left;
    }

    void setLeft(TreeNode left) {
        this.left = left;
    }

    TreeNode getRight() {
        return right;
    }

    void setRight(TreeNode right) {
        this.right = right;
    }

    TreeNode getParent() {
        return parent;
    }

    void setParent(TreeNode parent) {
        this.parent = parent;
    }

    int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    /**
     * Retrieve the other child of the given node
     * @param child The child not be retrieved, i.e. this child
     *              is checked against the parent to find the other child
     * @return A reference to the other child
     */
    TreeNode getOtherChild(TreeNode child) {
        if (getLeft() == child) {
            return getRight();
        }
        return getLeft();
    }
}
