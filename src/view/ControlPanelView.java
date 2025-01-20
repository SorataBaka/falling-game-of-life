package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.SimulationModel;
public class ControlPanelView extends JPanel implements ActionListener {
    private final JButton resetButton;
    private final JToggleButton sandLogic;
    private final JToggleButton gameOfLifeLogic;
    private final SimulationModel simulationModel;

    private final JLabel sandLogicLabel;
    private final JLabel gameOfLifeLogicLabel;
    public ControlPanelView(SimulationModel model) {
        this.simulationModel = model;
        this.resetButton = new JButton("Reset");
        this.sandLogic = new JToggleButton("Sand Logic", true);
        this.gameOfLifeLogic = new JToggleButton("Game of Life Logic", false);

        this.resetButton.addActionListener(this);
        this.sandLogic.addActionListener(this);
        this.gameOfLifeLogic.addActionListener(this);

        this.sandLogicLabel = new JLabel("Sand Logic: " + (this.sandLogic.isSelected()?"ON":"OFF"));
        this.gameOfLifeLogicLabel = new JLabel("Game of Life Login: " + (this.gameOfLifeLogic.isSelected()?"ON":"OFF"));

        this.setLayout(new FlowLayout());

        JPanel sandLogicPanel = new JPanel();
        sandLogicPanel.setLayout(new BorderLayout());
        sandLogicPanel.add(sandLogicLabel, BorderLayout.NORTH);
        sandLogicPanel.add(sandLogic, BorderLayout.CENTER);
        JPanel gameOfLifeLogicPanel = new JPanel();
        gameOfLifeLogicPanel.setLayout(new BorderLayout());
        gameOfLifeLogicPanel.add(gameOfLifeLogicLabel, BorderLayout.NORTH);
        gameOfLifeLogicPanel.add(gameOfLifeLogic, BorderLayout.CENTER);

        this.add(resetButton);
        this.add(sandLogicPanel);
        this.add(gameOfLifeLogicPanel);
    }
    public boolean sandLogicOn(){
        return sandLogic.isSelected();
    }
    public boolean gameOfLifeLogicOn(){
        return gameOfLifeLogic.isSelected();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.resetButton) {
            int[][] grid = this.simulationModel.getSimulationGrid();
            for(int i = 0; i < this.simulationModel.getWidth(); i++){
                for(int j = 0; j < this.simulationModel.getHeight(); j++){
                    grid[i][j] = 0;
                }
            }
        }
        if(e.getSource() == this.sandLogic) {
            this.sandLogicLabel.setText("Sand Logic: " + (this.sandLogic.isSelected()?"ON":"OFF"));
        }
        if(e.getSource() == this.gameOfLifeLogic) {
            this.gameOfLifeLogicLabel.setText("Game of Life Logic: " + (this.gameOfLifeLogic.isSelected()?"ON":"OFF"));
        }
    }
}
