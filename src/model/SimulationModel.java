package model;

import java.util.Random;

public class SimulationModel {
    private final int gridHeight;
    private final int gridWidth;
    private int [][] simulationGrid;
    public SimulationModel(int height, int width){
        this.gridHeight = height;
        this.gridWidth = width;
        this.simulationGrid = new int[gridWidth][gridHeight];
        //Initialize the entire grid to be 0
        //Just for test, set some cells to 1
        for(int i = 0; i < this.gridWidth; i++){
            for(int j = 0; j < this.gridHeight; j++){
               this.simulationGrid[i][j] = 0;
            }
        }
    }
    public int getHeight(){
        return this.gridHeight;
    }
    public int getWidth(){
        return this.gridWidth;
    }
    public int[][] getSimulationGrid(){
        return this.simulationGrid;
    }
    public void calculateNextGeneration(){
        int [][] newGeneration = new int[gridWidth][gridHeight];
        for(int j = this.gridHeight - 1; j >= 0; j--) {
            for (int i = 0; i < this.gridWidth; i++) {
                if (this.simulationGrid[i][j] == 1) {
                    Random random = new Random();
                    boolean moved = false;
                    if (j + 1 < this.gridHeight && this.simulationGrid[i][j + 1] == 0) {
                        moved = true;
                        newGeneration[i][j + 1] = 1;
                    } else {
                        if (random.nextBoolean()) {
                            if (j + 1 < this.gridHeight && i - 1 >= 0 && this.simulationGrid[i - 1][j + 1] == 0) {
                                moved = true;
                                newGeneration[i - 1][j + 1] = 1;
                            } else if (j + 1 < this.gridHeight && i + 1 < this.gridWidth && this.simulationGrid[i + 1][j + 1] == 0) {
                                moved = true;
                                newGeneration[i + 1][j + 1] = 1;
                            }
                        } else {
                            if (j + 1 < this.gridHeight && i + 1 < this.gridWidth && this.simulationGrid[i + 1][j + 1] == 0) {
                                moved = true;
                                newGeneration[i + 1][j + 1] = 1;
                            } else if (j + 1 < this.gridHeight && i - 1 >= 0 && this.simulationGrid[i - 1][j + 1] == 0) {
                                moved = true;
                                newGeneration[i - 1][j + 1] = 1;
                            }
                        }
                    }
                    if (!moved) {
                        newGeneration[i][j] = 1;
                    }
                    random = null;
                }
            }
        }
        this.simulationGrid = newGeneration;
        newGeneration = null;
        System.gc();
    }
}