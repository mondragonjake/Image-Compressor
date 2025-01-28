import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.PriorityQueue;


import javax.swing.JButton;

public class Main implements ActionListener{

//ine nga class kay amo ine an main class

    HuffmanControlPanel controlPanel = new HuffmanControlPanel();
    HuffmanImagePanel imagePanel = new HuffmanImagePanel();
    Huffman huffmanTree = new Huffman();
    HuffmanCompression h = new HuffmanCompression();
    Image image;

    public Main(){
        new HuffmanGUI(imagePanel, controlPanel);

        //mga action listener para ha mga buttons
        controlPanel.view.addActionListener(this);
        controlPanel.train.addActionListener(this);
        controlPanel.compress.addActionListener(this);
        controlPanel.openFile.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        switch (clickedButton.getText()) {
            
            

            //code para makitan an picture na need ig compress
            case "VIEW":
                image = new Image();
                image.ViewImage();
                imagePanel.add(image.imageLabel, BorderLayout.CENTER);
                imagePanel.revalidate();
                imagePanel.repaint(); 
                image.trainImage();
                System.out.println("VIEW button clicked");
                break;

            case "TRAIN":

                //need ig modify: nadara if absolute path, pero dire nadara kun name la han image
                if(image!=null){
                    
                    
                    huffmanTree.createHuffanTree();
                }
                break;

            case "COMPRESS":

                //waray pa code
                if(image!=null){
                    h.readFile();
                    h.compressImage();
                    imagePanel.removeAll();
                    imagePanel.revalidate();
                    imagePanel.repaint(); 
                    
                }

                break;

            case "OPEN FILE":

                //waray pa gihap code
                h.decompressImage("C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\compressed_output.MMT", "C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\CompressedImage\\output.png", huffmanTree.extraxtHexCode.width, huffmanTree.extraxtHexCode.height);
                
                
                imagePanel.add(h.imageLabel, BorderLayout.CENTER);
                imagePanel.revalidate();
                imagePanel.repaint(); 
                System.out.println("VIEW button clicked");
                System.out.println("width: "+huffmanTree.extraxtHexCode.width);
                System.out.println("height: "+huffmanTree.extraxtHexCode.height);

                break;

            default:
                break;
        }

        
    }

    //main function
    public static void main(String[] args) {
        Main huffman = new Main();

    }
    
}

