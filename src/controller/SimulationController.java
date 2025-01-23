package controller;

import javax.swing.*;
import model.SimulationModel;
import view.SimulationView;

import java.awt.*;

public class SimulationController {
    private final SimulationModel model;
    private final SimulationView view;
    private final Timer timer;
    private int timeDelay;
    public SimulationController(){
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.timeDelay = 10;

        this.model = new SimulationModel(height/2, width, this);
        this.view = new SimulationView(this);
        this.timer = new Timer(this.timeDelay, this.view);

        this.timer.start();
    }
    public SimulationModel getModel() {
        return model;
    }
    public SimulationView getView() {
        return view;
    }
    public int getDelay() {
        return timeDelay;
    }
    public void setDelay(int delay) {
        this.timeDelay = delay;
        this.timer.stop();
        this.timer.setDelay(delay);
        this.timer.start();
    }
}