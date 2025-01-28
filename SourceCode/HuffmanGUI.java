
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

//ine nga class kay para pag incorporate na han controlPanel ngan ImagePanel
public class HuffmanGUI extends JFrame {

    private GridBagConstraints gbc;

    //GUI = Frame + Panel
    public HuffmanGUI(JPanel imagePanel, JPanel controlPanel) {
        setTitle("Image Compressor");
        setLayout(new GridBagLayout());
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setBackground(Color.DARK_GRAY); 
        setResizable(false);

        gbc = new GridBagConstraints();

        //add the imagePanel
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.weightx = 0.8;
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH; 
        imagePanel.setBackground(Color.GRAY); 
        add(imagePanel, gbc); 

        //add the button panel
        gbc.gridx = 1; 
        gbc.weightx = 0.2; 
        controlPanel.setBackground(Color.DARK_GRAY); 
        add(controlPanel, gbc); 
        setVisible(true);
    }
}
