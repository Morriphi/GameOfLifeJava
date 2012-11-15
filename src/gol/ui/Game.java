package gol.ui;

import gol.domain.Cell;
import gol.domain.DeadCell;
import gol.domain.LivingCell;
import gol.domain.Generation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JFrame {
    public static final String TITLE = "Game of Life";
    public static final Point INITIAL_POSITION = new Point(150, 30);
    public static final Dimension INITIAL_SIZE = new Dimension(1000, 750);
    public static final int CELL_SIZE = 10;
    public final Board board;

    public Game() {
        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setLocation(INITIAL_POSITION);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        board = new Board(CELL_SIZE, new GenerationMaker(){
            public Generation make() {
                return new Generation(generateCells());
            }
        });
        this.getContentPane().add(BorderLayout.CENTER, board);
        this.getContentPane().add(BorderLayout.SOUTH, setupControls());

        setVisible(true);
   }

    private Cell[][] generateCells() {
        Cell[][] cells = new Cell
                [Game.INITIAL_SIZE.height / CELL_SIZE]
                [Game.INITIAL_SIZE.width / CELL_SIZE];

        for(int y = 0; y < cells.length; y++)
            for(int x = 0; x < cells[0].length; x++)
                if(new Random().nextInt(5)/ 2 == 0)
                    cells[y][x] = new LivingCell(y,x);
                else
                    cells[y][x] = new DeadCell(y,x);

        return cells;
    }

    private Controls setupControls() {
        Controls controlPanel = new Controls();
        controlPanel.onStart(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board.start();
            }
        });
        controlPanel.onStop(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board.stop();
            }
        });
        controlPanel.onRestart(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                board.restart();
            }
        });
        return controlPanel;
    }
}
