import controller.SimulationController;
import model.SimulationModel;
import view.SimulationView;

public class FGameOfLife {
    public static void main(String[] args){
        SimulationModel model = new SimulationModel(10, 100, 100);
        SimulationView view = new SimulationView();
        new SimulationController(model, view);

        view.setVisible(true);
    }
}