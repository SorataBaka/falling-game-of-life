import controller.SimulationController;
import model.SimulationModel;
import view.SimulationView;

import java.awt.*;

public class FGameOfLife {
    public static void main(String[] args){
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        SimulationModel model = new SimulationModel(height/2, width);
        SimulationView view = new SimulationView(model);
        new SimulationController(model, view);
    }
}