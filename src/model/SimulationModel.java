package model;

import controller.SimulationController;

import java.awt.*;
import java.util.Random;

public class SimulationModel {
    private final int gridHeight;
    private final int gridWidth;
    private final int [][] simulationGrid;
    private int radius;
    private final SimulationController controller;
    private int gameOfLifeMultiplier;
    private final Random random;
    public final static Color[] color_hex = {
            Color.RED,        // Red
            Color.BLUE,       // Blue
            Color.GREEN,      // Green
            Color.YELLOW,     // Yellow
            Color.MAGENTA,    // Purple
            Color.ORANGE,     // Orange
            Color.PINK,       // Pink
            new Color(165, 42, 42),  // Brown (Custom RGB)
            Color.LIGHT_GRAY,      // Black
            Color.WHITE       // White
    };
    private int chosenColor;
    public SimulationModel(int height, int width, SimulationController controller){
        this.controller = controller;
        this.gridHeight = height;
        this.gridWidth = width;
        this.radius = 10;
        this.gameOfLifeMultiplier = 0;
        this.chosenColor = 2;
        this.random = new Random();

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
    public void setRadius(int radius){
        this.radius = radius;
    }
    public int getRadius(){
        return this.radius;
    }
    public int getGameOfLifeMultiplier(){
        return this.gameOfLifeMultiplier;
    }
    public void setGameOfLifeMultiplier(int gameOfLifeMultiplier){
        this.gameOfLifeMultiplier = gameOfLifeMultiplier;
    }
    public void setChosenColor(int chosenColor){
        this.chosenColor = chosenColor;
    }
    public void drawCircle(int x, int y){
        if(x < 0 || x >= this.gridWidth || y < 0 || y >= this.gridHeight) return;
        for (int i = -this.radius; i <= this.radius; i++) {
            for (int j = -this.radius; j <= this.radius; j++) {
               if(this.random.nextBoolean()) continue;
                // Check if the point is within the circle's radius using Pythagorean theorem
                if (i * i + j * j <= this.radius * this.radius) {
                    int circleX = x + i;
                    int circleY = y + j;
                    // Ensure the coordinates are within bounds
                    if (circleX >= 0 && circleX < this.getWidth() && circleY >= 0 && circleY < this.getHeight()) {
                        this.getSimulationGrid()[circleX][circleY] = this.chosenColor + 1; // Mark the grid as occupied by the circle
                    }
                }
            }
        }
    }
    public void gameOfLife(){
        int neighbours;
        int colorAverage;
        for(int multiplier = 1; multiplier <= this.gameOfLifeMultiplier; multiplier++){
            for(int i = 1; i < this.gridWidth - 1; i++){
                for(int j = this.gridHeight - 2; j >= 1; j--){
                    neighbours = 0;
                    colorAverage = 0;
                    if(this.simulationGrid[i-1][j-1] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i-1][j-1];
                    }
                    if(this.simulationGrid[i][j-1] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i][j-1];
                    }
                    if(this.simulationGrid[i+1][j-1] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i+1][j-1];
                    }
                    if(this.simulationGrid[i-1][j] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i-1][j];
                    }
                    if(this.simulationGrid[i+1][j] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i+1][j];
                    }
                    if(this.simulationGrid[i-1][j+1] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i-1][j+1];
                    }
                    if(this.simulationGrid[i][j+1] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i][j+1];
                    }
                    if(this.simulationGrid[i+1][j+1] > 0) {
                        neighbours++;
                        colorAverage += this.simulationGrid[i+1][j+1];
                    } 

                    boolean currentPositionPopulated = this.simulationGrid[i][j] > 0;
                    if(neighbours == 0){
                        colorAverage = 10;
                    }else{
                        float average = (float) colorAverage / neighbours;
                        colorAverage = Math.round(average);
                    }
                    if(currentPositionPopulated && neighbours < 2) this.simulationGrid[i][j] = 0;
                    if(currentPositionPopulated && (neighbours == 2 || neighbours == 3)) this.simulationGrid[i][j] = colorAverage;
                    if(currentPositionPopulated && neighbours > 3) this.simulationGrid[i][j] = 0;
                    if(!currentPositionPopulated && neighbours == 3) this.simulationGrid[i][j] = colorAverage;
                }
            }
        }
    }

    public void generate(int i, int j){
        if (this.simulationGrid[i][j] > 0) {
            if (j + 1 < this.gridHeight && this.simulationGrid[i][j + 1] == 0) {
                this.simulationGrid[i][j + 1] = this.simulationGrid[i][j];
                this.simulationGrid[i][j] = 0;
            } else {
                if (this.random.nextBoolean()) {
                    if (j + 1 < this.gridHeight && i - 1 >= 0 && this.simulationGrid[i - 1][j + 1] == 0) {
                        this.simulationGrid[i - 1][j + 1] = this.simulationGrid[i][j];
                        this.simulationGrid[i][j] = 0;
                    } else if (j + 1 < this.gridHeight && i + 1 < this.gridWidth && this.simulationGrid[i + 1][j + 1] == 0) {
                        this.simulationGrid[i+1][j+1] = this.simulationGrid[i][j];
                        this.simulationGrid[i][j] = 0;
                    }
                } else {
                    if (j + 1 < this.gridHeight && i + 1 < this.gridWidth && this.simulationGrid[i + 1][j + 1] == 0) {
                        this.simulationGrid[i+1][j+1] = this.simulationGrid[i][j];
                        this.simulationGrid[i][j] = 0;
                    } else if (j + 1 < this.gridHeight && i - 1 >= 0 && this.simulationGrid[i - 1][j + 1] == 0) {
                        this.simulationGrid[i-1][j+1] = this.simulationGrid[i][j];
                        this.simulationGrid[i][j] = 0;
                    }
                }
            }
        }
    }
    public void calculateNextGeneration(){
        for(int j = this.gridHeight - 1; j >= 0; j--) {
            if(j % 2 == 0){
                for (int i = this.gridWidth - 1; i >= 0; i--) this.generate(i, j);

            } else {
                for (int i = 0; i < this.gridWidth; i++) this.generate(i, j);

            }
        }
        System.gc();
    }
    public void resetGrid(){
        for(int i = 0; i < this.gridWidth; i++){
            for(int j = 0; j < this.gridHeight; j++){
                this.simulationGrid[i][j] = 0;
            }
        }
    }
}