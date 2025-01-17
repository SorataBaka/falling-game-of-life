package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SimulationView extends JFrame{
    private JPanel simulationGridPanel;
    private JPanel controlPanel;
    public SimulationView(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel mainPanel = new JPanel();
        super.getContentPane().add(mainPanel);
        super.setLocation(0, 0);
        super.setSize(screenSize);
        super.setTitle("Falling Game of Life");
    }
}