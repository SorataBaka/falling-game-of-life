package model;
public class SimulationModel {
    private final int gridHeight;
    private final int gridWidth;
    private int [][] simulationGrid;
    private float velocity;

    public SimulationModel(float velocity, int height, int width){
        this.gridHeight = height;
        this.gridWidth = width;

        this.simulationGrid = new int[gridHeight][gridWidth];
        //Initialize the entire grid to be 0
        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){
                this.simulationGrid[i][j] = 0;
            }
        }
        this.velocity = velocity;
    }
}