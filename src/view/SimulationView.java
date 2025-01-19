package view;
import model.SimulationModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SimulationView extends JFrame implements ActionListener {
    private final GridView myGridView;
    private final SimulationModel myModel;
    public SimulationView(SimulationModel model){
        super.setTitle("Simulation");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
        super.setLayout(new BorderLayout());
        this.myModel = model;
        this.myGridView = new GridView(model);
        super.add(this.myGridView, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {
        this.myModel.calculateNextGeneration();
        this.myGridView.repaint();
    }
}