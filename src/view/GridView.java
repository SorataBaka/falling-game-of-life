package view;

import model.SimulationModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GridView extends JPanel implements MouseListener, MouseMotionListener {
    private final SimulationModel myModel;
    private int currentX;
    private int currentY;
    public GridView(SimulationModel model) {
        this.setSize(new Dimension(model.getWidth(), model.getHeight()));
        this.setBackground(Color.GRAY);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.myModel = model;
        this.currentX = this.currentY = -1;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int [][] grid = this.myModel.getSimulationGrid();
        for(int row = 0; row < this.myModel.getHeight(); row++) {
            for(int col = 0; col < this.myModel.getWidth(); col++) {
                if(grid[col][row] > 0) {
                    g.setColor(SimulationModel.color_hex[grid[col][row] - 1]);
                    g.fillRect(col, row, 1, 1);
                }
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // Called when the mouse is clicked (pressed and released).
        this.currentX = e.getX();
        this.currentY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Called when the mouse button is pressed.
        this.currentX = e.getX();
        this.currentY = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Called when the mouse button is released.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Called when the mouse enters the component's area.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Called when the mouse exits the component's area.
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // Called when the mouse is dragged (clicked and moved).
        if(this.currentX == -1 || this.currentY == -1 ){
            this.currentX = e.getX();
            this.currentY = e.getY();
            return;
        }
        //Get new coordinates
        int newX = e.getX();
        int newY = e.getY();

        //Get distance
        int distanceX = newX - this.currentX;
        int distanceY = newY - this.currentY;

        //Get steps
        int steps = Math.max(Math.abs(distanceX), Math.abs(distanceY));

        double xStep = (double)distanceX / steps;
        double yStep = (double)distanceY / steps;
        
        //Starting point
        float x = currentX;
        float y = currentY;

        for(int i = 0; i <= steps; i++){
            this.myModel.drawCircle(Math.round(x), Math.round(y));
            x += xStep;
            y += yStep;
        }
        this.currentX = newX;
        this.currentY = newY;
        this.repaint();

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // Called when the mouse is moved (without clicking).
    }
}
