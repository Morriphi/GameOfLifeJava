package gol.ui;

import gol.domain.Cell;
import gol.domain.Generation;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JPanel {
    java.util.Timer timer;
    private Generation generation;
    private GenerationMaker generationMaker;
    private int cellSize;
    private int speedInMilliseconds = 100;

    public Board(int cellSize, GenerationMaker generationMaker){
        this.cellSize = cellSize;
        this.generationMaker = generationMaker;
        this.generation = generationMaker.make();
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                generation = generation.spawn();
                repaint();
            }
        }, 0, speedInMilliseconds);
    }

    public void stop() {
        timer.cancel();
    }

    public void restart() {
        generation = generationMaker.make();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        Cell[][] population = generation.population();

        for(int yPosition = 0, y=0; yPosition < population.length * cellSize; yPosition+=cellSize, y++)
            for(int xPosition = 0, x=0; xPosition < population[0].length * cellSize; xPosition+=cellSize, x++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setSize(cellSize, cellSize);
                if(population[y][x].isAlive())
                    g2d.setColor(Color.BLACK);
                else
                    g2d.setColor(Color.WHITE);

                rectangle.setLocation(xPosition, yPosition);
                g2d.fill(rectangle);
                g2d.setColor(Color.GRAY);
                g2d.draw(rectangle);
            }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
}
