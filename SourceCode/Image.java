

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
public class Image {

    ImageIcon image;
    JLabel imageLabel;
            
            // Output file for storing pixel hex data
            File outputFile = new File("C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\pixel_data.txt");
            // Input PNG image path
            String imagePath = "C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\TrialImage\\lenna.png";
            Map<String, Integer> hexFrequencyMap = new HashMap<>();
            int width, height;


    public Image() {
    }

    //para ma view an image
    public void ViewImage() {
        image = new ImageIcon("C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\TrialImage\\lenna.png");
        imageLabel = new JLabel(image);
    }

    public void ViewCompressedImage(BufferedImage decompressImage) {
        
        ImageIcon imageIcon = new ImageIcon(decompressImage);

        // Create a JLabel to display the ImageIcon
        imageLabel = new JLabel(imageIcon);
    }

    

    //para ma extract an RGB values
    public void trainImage() {
        try {
            // Load the PNG image
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Get image dimensions
            width = image.getWidth();
            height = image.getHeight();

            // Use BufferedWriter to write pixel data to the output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                // Loop through each pixel
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // Get the RGB value of the pixel
                        int pixel = image.getRGB(x, y);

                        // Extract ARGB components
                        int alpha = (pixel >> 24) & 0xFF; // Alpha component
                        int red = (pixel >> 16) & 0xFF;   // Red component
                        int green = (pixel >> 8) & 0xFF;  // Green component
                        int blue = pixel & 0xFF;         // Blue component

                        // Convert to hexadecimal
                        String hexValue = String.format("#%02X%02X%02X", red, green, blue);
                        hexFrequencyMap.put(hexValue, hexFrequencyMap.getOrDefault(hexValue, 0) + 1);

                        // Write hex value to the file
                        writer.write(hexValue + System.lineSeparator());    
                    }
                }
                System.out.println("pixel_data.txt successfully created.");
            }
            int count=0;

            // System.out.println("Hex Color Frequency Map:");
            // for (Map.Entry<String, Integer> entry : hexFrequencyMap.entrySet()) {
            //     System.out.println(entry.getKey() + ": " + entry.getValue());
            //     count++;
            // }
            System.out.println("doneeee");
            System.out.println("count: "+count);
        } catch (IOException e) {
            System.err.println("Error processing the PNG file: " + e.getMessage());
        }
    }
    
}
