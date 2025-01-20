package view;

import model.SimulationModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GridView extends JPanel implements MouseListener, MouseMotionListener {
    private final SimulationModel myModel;
    public GridView(SimulationModel model) {
        this.setSize(new Dimension(model.getWidth(), model.getHeight()));
        this.setBackground(Color.GRAY);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.myModel = model;
    }
    public void drawCircle(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        this.myModel.drawCircle(x, y, 20);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        int [][] grid = this.myModel.getSimulationGrid();
        for(int row = 0; row < this.myModel.getHeight(); row++) {
            for(int col = 0; col < this.myModel.getWidth(); col++) {
                if(grid[col][row] == 1) {
                    g.setColor(Color.GREEN);
                    g.fillRect(col, row, 1, 1);
                }
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // Called when the mouse is clicked (pressed and released).
        this.drawCircle(e);
        this.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Called when the mouse button is pressed.
        this.drawCircle(e);
        this.repaint();

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
        this.drawCircle(e);
        this.repaint();

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // Called when the mouse is moved (without clicking).
    }
}
