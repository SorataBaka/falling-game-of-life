import controller.SimulationController;
import model.SimulationModel;
import view.SimulationView;

public class FGameOfLife {
    public static void main(String[] args){
        SimulationModel model = new SimulationModel(500, 500);
        SimulationView view = new SimulationView(model);
        new SimulationController(model, view);
    }
}