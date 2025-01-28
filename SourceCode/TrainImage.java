import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;

class TrainImage {


    
    

    public static void main(String[] args) {

        Huffman huffmanTree = new Huffman();
        huffmanTree.createHuffanTree();

        HuffmanCompression h = new HuffmanCompression();
        h.readFile();
        h.compressImage();
        h.decompressImage("C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\Data\\compressed_output.HUFF", "C:\\Users\\jbmon\\OneDrive\\Desktop\\SY-SS\\CMSC 123\\CMSC 123 Lab\\Tryla\\CompressedImage\\output.png", huffmanTree.extraxtHexCode.width, huffmanTree.extraxtHexCode.height);
        System.out.println("width: "+huffmanTree.extraxtHexCode.width);
        System.out.println("height: "+huffmanTree.extraxtHexCode.height);

        
    }
}
