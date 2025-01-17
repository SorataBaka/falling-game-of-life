package controller;

import model.SimulationModel;
import view.SimulationView;


public class SimulationController  {
    private SimulationModel model;
    private SimulationView view;
    public SimulationController(SimulationModel model, SimulationView view){
        this.model = model;
        this.view = view;
    }
}