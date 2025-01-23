package view;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.SimulationController;
public class ControlPanelView extends JPanel implements ActionListener, ChangeListener {
    private final SimulationController controller;

    private final JButton resetButton;
    private final JToggleButton sandLogic;
    private final JToggleButton gameOfLifeLogic;
    private final JSlider cursorRadiusSlider;
    private final JSlider simulationSpeedSlider;
    private final JSlider gameOfLifeMultiplierSlider;

    private final JLabel sandLogicLabel;
    private final JLabel gameOfLifeLogicLabel;
    private final JLabel cursorRadiusLabel;
    private final JLabel simulationSpeedLabel;

    public ControlPanelView(SimulationController controller) {
        this.controller = controller;
        Font defaultFont = new Font("Arial", Font.PLAIN, 20);

        //INITIALIZE ALL CONTROLS
        this.resetButton = new JButton("Reset");
        this.resetButton.setFont(defaultFont);

        this.sandLogic = new JToggleButton("Sand Logic", true);
        this.sandLogic.setFont(defaultFont);

        this.gameOfLifeLogic = new JToggleButton("Game of Life Logic", false);
        this.gameOfLifeLogic.setFont(defaultFont);

        this.cursorRadiusSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, this.controller.getModel().getRadius());
        this.simulationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, this.controller.getDelay());
        this.gameOfLifeMultiplierSlider = new JSlider(JSlider.HORIZONTAL, 0,10, this.controller.getModel().getGameOfLifeMultiplier());


        //ADD LISTENERS

        this.resetButton.addActionListener(this);
        this.sandLogic.addActionListener(this);
        this.gameOfLifeLogic.addActionListener(this);
        this.cursorRadiusSlider.addChangeListener(this);
        this.simulationSpeedSlider.addChangeListener(this);
        this.gameOfLifeMultiplierSlider.addChangeListener(this);


        // ALL LABELS
        this.sandLogicLabel = new JLabel("Sand Logic: " + (this.sandLogic.isSelected()?"ON":"OFF"));
        this.sandLogicLabel.setFont(defaultFont);
        this.sandLogicLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.gameOfLifeLogicLabel = new JLabel("Game of Life: " + this.gameOfLifeMultiplierSlider.getValue());
        this.gameOfLifeLogicLabel.setFont(defaultFont);
        this.gameOfLifeLogicLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.cursorRadiusLabel = new JLabel("Cursor Radius: " + (this.cursorRadiusSlider.getValue()));
        this.cursorRadiusLabel.setFont(defaultFont);
        this.cursorRadiusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.simulationSpeedLabel = new JLabel("Simulation Speed: " + (this.simulationSpeedSlider.getValue()));
        this.simulationSpeedLabel.setFont(defaultFont);
        this.simulationSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel sandLogicPanel = new JPanel();
        sandLogicPanel.setLayout(new BorderLayout());
        sandLogicPanel.add(sandLogicLabel, BorderLayout.NORTH);
        sandLogicPanel.add(sandLogic, BorderLayout.CENTER);

        JPanel gameOfLifeLogicPanel = new JPanel();
        gameOfLifeLogicPanel.setLayout(new BorderLayout());
        gameOfLifeLogicPanel.add(gameOfLifeLogicLabel, BorderLayout.NORTH);
        gameOfLifeLogicPanel.add(gameOfLifeMultiplierSlider, BorderLayout.CENTER);

        JPanel cursorRadiusPanel = new JPanel();
        cursorRadiusPanel.setLayout(new BorderLayout());
        cursorRadiusPanel.add(cursorRadiusLabel, BorderLayout.NORTH);
        cursorRadiusPanel.add(cursorRadiusSlider, BorderLayout.CENTER);

        JPanel simulationSpeedPanel = new JPanel();
        simulationSpeedPanel.setLayout(new BorderLayout());
        simulationSpeedPanel.add(simulationSpeedLabel, BorderLayout.NORTH);
        simulationSpeedPanel.add(simulationSpeedSlider, BorderLayout.CENTER);


        this.setLayout(new GridLayout(1, 5));

        this.add(resetButton);
        this.add(sandLogicPanel);
        this.add(gameOfLifeLogicPanel);
        this.add(cursorRadiusPanel);
        this.add(simulationSpeedPanel);
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    }
    public boolean sandLogicOn(){
        return sandLogic.isSelected();
    }
    public boolean gameOfLifeLogicOn(){
        return gameOfLifeLogic.isSelected();
    }
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == cursorRadiusSlider){
            this.cursorRadiusLabel.setText("Cursor Radius: " + (this.cursorRadiusSlider.getValue()));
            this.controller.getModel().setRadius(this.cursorRadiusSlider.getValue());
        }
        if(e.getSource() == simulationSpeedSlider){
            this.simulationSpeedLabel.setText("Simulation Speed: " + (this.simulationSpeedSlider.getValue()));
            this.controller.setDelay(this.simulationSpeedSlider.getValue());
        }
        if(e.getSource() == gameOfLifeMultiplierSlider){
            this.gameOfLifeLogicLabel.setText("Game Of Life: " + (this.gameOfLifeMultiplierSlider.getValue()));
            this.controller.getModel().setGameOfLifeMultiplier(this.gameOfLifeMultiplierSlider.getValue());
        }
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.resetButton) this.controller.getModel().resetGrid();
        if(e.getSource() == this.sandLogic) this.sandLogicLabel.setText("Sand Logic: " + (this.sandLogic.isSelected()?"ON":"OFF"));
    }
}
