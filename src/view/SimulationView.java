package view;
import controller.SimulationController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SimulationView extends JFrame implements ActionListener {
    private final GridView myGridView;
    private final SimulationController controller;
    private final ControlPanelView controlPanelView;
    public SimulationView(SimulationController controller){
        this.controller = controller;
        this.myGridView = new GridView(this.controller.getModel());
        this.controlPanelView = new ControlPanelView(this.controller);

        JLabel labelTitle = new JLabel("Game of Life + Sand Simulation");
        labelTitle.setBorder(BorderFactory.createEmptyBorder(20, 20,  20, 20));
        labelTitle.setFont(new Font("Arial", Font.BOLD, 50));
        labelTitle.setForeground(Color.BLACK);
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(labelTitle, BorderLayout.NORTH);
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
        if(this.controlPanelView.sandLogicOn()) this.controller.getModel().calculateNextGeneration();
        this.controller.getModel().gameOfLife();
        this.myGridView.repaint();
    }
}