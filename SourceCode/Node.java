class Node {
    String hexCode; // Hex color code represented by the node
    Node left, right; // Left and right child nodes
    int data; // Frequency or weight of the node

    // Constructor for leaf nodes (with hex code and frequency)
    Node(){
        
    }

    Node(String hexCode, int data) {
        this.hexCode = hexCode;
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Constructor for internal nodes (used in tree building)
    Node(int data) {
        this.hexCode = null; // Non-leaf nodes don't have a hex code
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
