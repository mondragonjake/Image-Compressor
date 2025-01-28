import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    PNGHexExtractor extraxtHexCode = new PNGHexExtractor();
    Node root;

    // Method to create a priority queue from hex codes and frequencies
    private PriorityQueue<Node> createNode(String[] hexCode, int[] hexCodeFreq, int n) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(n, new MyComparator());
        for (int i = 0; i < n; i++) {
            Node node = new Node(hexCode[i], hexCodeFreq[i]);
            priorityQueue.add(node);
        }
        return priorityQueue;
    }

    // Method to create the root of the Huffman tree
    private Node createRootNode(PriorityQueue<Node> priorityQueue) {
        while (priorityQueue.size() > 1) {
            Node x = priorityQueue.poll();
            Node y = priorityQueue.poll();
            Node parent = new Node(x.data + y.data);  // Internal node frequency
            parent.left = x;
            parent.right = y;
            priorityQueue.add(parent);
        }
        return priorityQueue.poll(); // The last remaining node is the root
    }

    // Method to generate Huffman codes and write them to the file
    public void printHuffmanCode(Node root, String s, BufferedWriter writer) throws IOException {
        if (root.left == null && root.right == null && root.hexCode != null) {
            // Leaf node, write the Huffman code to the file
            writer.write(root.hexCode + "," + s + "\n");
            return;
        }
        if (root.left != null) printHuffmanCode(root.left, s + "0", writer);
        if (root.right != null) printHuffmanCode(root.right, s + "1", writer);
    }

    public void createHuffanTree() {
        extraxtHexCode.trainImage();
        if (extraxtHexCode.hexFrequencyMap.isEmpty()) {
            System.err.println("Frequency map is empty. Cannot compress.");
            return;
        }
    
        // Prepare the arrays for hex codes and frequencies
        String[] hexCodeArray = new String[extraxtHexCode.hexFrequencyMap.size()];
        int[] hexCodeFreq = new int[extraxtHexCode.hexFrequencyMap.size()];
    
        int index = 0;
        for (Map.Entry<String, Integer> entry : extraxtHexCode.hexFrequencyMap.entrySet()) {
            hexCodeArray[index] = entry.getKey();
            hexCodeFreq[index] = entry.getValue();
            index++;
        }
    
        // Create the priority queue and build the Huffman tree
        PriorityQueue<Node> priorityQueue = createNode(hexCodeArray, hexCodeFreq, extraxtHexCode.hexFrequencyMap.size());
        root = createRootNode(priorityQueue);
    
        // Write the Huffman codes to the file
        File huffmanCodeFile = new File("C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\huffman_tree.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(huffmanCodeFile))) {
            // Only open BufferedWriter once here
            printHuffmanCode(root, "", writer);  // Generate and write codes to file
            System.out.println("Huffman codes successfully written to huffman_tree.txt");
        } catch (IOException e) {
            System.err.println("Error writing to huffman_tree.txt: " + e.getMessage());
        }
    
    }
}
