package view;
import model.SimulationModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SimulationView extends JFrame implements ActionListener {
    private final GridView myGridView;
    private final SimulationModel myModel;
    private final ControlPanelView controlPanelView;
    public SimulationView(SimulationModel model){

        this.myModel = model;
        this.myGridView = new GridView(model);
        this.controlPanelView = new ControlPanelView(model);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.myGridView, BorderLayout.CENTER);
        panel.add(this.controlPanelView, BorderLayout.SOUTH);
        super.add(panel);

        super.revalidate();
        super.repaint();

        super.setTitle("Simulation");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height));
        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(this.controlPanelView.sandLogicOn())this.myModel.calculateNextGeneration();
        if(this.controlPanelView.gameOfLifeLogicOn())this.myModel.gameOfLife();
        this.myGridView.repaint();
    }
}