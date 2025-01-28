

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.*;
import javax.swing.*;
import java.awt.*;

//ine nga class kay an paghimo han GUI para kanan mga buttons
public class HuffmanControlPanel extends JPanel{

    JButton view, train, compress, openFile;
    GridBagConstraints gbc;

    //panel para hit mga buttons: VIEW, TRAIN, COMPRESS, OPEN FILE
    public HuffmanControlPanel(){

        setSize(getPreferredSize());
        setLayout(new GridBagLayout());

        gbc=new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        createButtons();

        gbc.gridx=0;
        gbc.weightx=0;
        gbc.weighty=0;

        gbc.gridy=0;
        add(view, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        add(train, gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        add(compress, gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        add(openFile, gbc);
    }

    //create buttons here na gagamiton: view, train, compress, open compressed code
    public void createButtons(){

        view = new JButton("VIEW");
        view.setPreferredSize(new Dimension(150, 30));  
    
        train = new JButton("TRAIN");
        train.setPreferredSize(new Dimension(150, 30));  
    
        compress = new JButton("COMPRESS");
        compress.setPreferredSize(new Dimension(150, 30));  
    
        openFile = new JButton("OPEN FILE");
        openFile.setPreferredSize(new Dimension(150, 30));  
    } 

    
}
