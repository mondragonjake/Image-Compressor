import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class HuffmanCompression {

    Map<String, String> huffmanMap = new HashMap<>();
    Map<String, String> reverseHuffmanMap = new HashMap<>();

    public void readFile() {
        String fileName = "C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\huffman_tree.txt"; // Replace with the path to your file

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line using a comma delimiter
                String[] data = line.split(",");
 
                
                try {
                    String hexCode = data[0].trim();
                    String code = data[1].trim();
                    huffmanMap.put(hexCode, code);
                    reverseHuffmanMap.put(code, hexCode);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid line: " + line + " (" + e.getMessage() + ")");
                }
            }
            System.out.println("Huffman Map Loaded: " + huffmanMap);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public void compressImage() {
        String inputFileName = "C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\pixel_data.txt";
        String outputFileName = "C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\compressed_output.HUFF";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
    
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\n");
    
                try {
                    String hexCode = data[0].trim();
    
                    if (huffmanMap.containsKey(hexCode)) {
                        String huffmanCode = huffmanMap.get(hexCode);
                        writer.write(huffmanCode);
                    } else {
                        System.err.println("No Huffman code for gray value: " + hexCode);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid pixel data: " + line + " (" + e.getMessage() + ")");
                }
            }
    
            System.out.println("Compression complete. Output written to: " + outputFileName);
    
        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
        }
    }
    
    public void decompressImage(String inputCompressedFilePath, String outputImagePath, int width, int height) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputCompressedFilePath))) {
            System.out.println("Start of decompression");
    
            // Read the entire compressed data as a string
            StringBuilder compressedData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                compressedData.append(line.trim());  // Accumulate the compressed bitstream
            }
    
            // Initialize image to reconstruct
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int x = 0, y = 0;
            StringBuilder currentCode = new StringBuilder();
    
            // Decode each bit of the compressed data
            for (int i = 0; i < compressedData.length(); i++) {
                currentCode.append(compressedData.charAt(i));  // Append the current bit

                // Convert currentCode (StringBuilder) to String and then to Long (binary)
                String currentCodeStr = currentCode.toString();
                String currentCodeLong = currentCodeStr; // Convert binary to Long

                // Check if the current Huffman code matches any entry in the reverseHuffmanMap
                if (reverseHuffmanMap.containsKey(currentCodeLong)) {
                    String hexCode = reverseHuffmanMap.get(currentCodeLong);  // Retrieve hex code corresponding to Long

                    // Validate and convert hex to RGB integer directly
                    try {
                        if (hexCode.startsWith("#") && hexCode.length() == 7) {
                            int color = Integer.parseInt(hexCode.substring(1), 16);  // Convert hex to RGB integer
                            image.setRGB(x, y, color);  // Set pixel color
                        } else {
                            System.err.println("Invalid hex code: " + hexCode);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing hex color code: " + hexCode);
                    }

                    // Move to the next pixel position
                    x++;
                    if (x == width) {  // If we reach the end of the row
                        x = 0;  // Reset x to 0
                        y++;  // Move to the next row

                        // Ensure y does not exceed height
                        if (y == height) {
                            System.out.println("Image fully decompressed.");
                            break;  // Stop processing if we've reached the end of the image
                        }
                    }

                    // Reset currentCode for the next Huffman code
                    currentCode.setLength(0);  // Clear the StringBuilder
                }
            }



            // Write the reconstructed image to the output file
            ImageIO.write(image, "png", new File(outputImagePath));
            System.out.println("Image decompression completed. Output file: " + outputImagePath);

            
            // After decompression loop ends
            if (y == height && x == 0) {
                System.out.println("Image fully decompressed.");
            } else {
                System.err.println("Warning: Image may not be fully decompressed. Remaining pixels: (" + x + ", " + y + ")");
            }

        } catch (IOException e) {
            System.err.println("Error during image decompression: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
    
}

