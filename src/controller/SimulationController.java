package controller;
import javax.swing.*;
import model.SimulationModel;
import view.SimulationView;
public class SimulationController {
    private final SimulationModel model;
    private final SimulationView view;
    private final Timer timer;
    public SimulationController(SimulationModel model, SimulationView view){
        this.model = model;
        this.view = view;
        this.timer = new Timer(10, this.view);
        this.timer.start();
    }
}