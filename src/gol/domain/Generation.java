package gol.domain;

import java.util.ArrayList;
import java.util.List;

public class Generation implements INeighbourhood {
    private Cell[][] cells;

    public Generation(Cell[][] cells) {
        for(int y = 0; y < cells.length; y++)
            if(cells[y].length != cells[0].length)
                throw new JaggeredArraysNotAllowed();

        this.cells = cells;
    }

    public Generation spawn() {
        Cell[][] nextGeneration = new Cell[cells.length][cells[0].length];

        for(int y = 0; y < cells.length; y++)
            for(int x = 0; x < cells[0].length; x++)
                nextGeneration[y][x] = cells[y][x].spawn(this);

        return new Generation(nextGeneration);
    }

    public int numberOfLiveNeighboursFor(Cell cell) {
        List<Cell> neighbours = new ArrayList<Cell>();

        for(int offsetY = -1; offsetY <= 1; offsetY++)
            for(int offsetX = -1; offsetX <= 1; offsetX++)
                if(!(offsetX == 0 && offsetY == 0))
                    if(isValidPosition((cell.y + offsetY), (cell.x + offsetX)))
                        if(cells[(cell.y + offsetY)][(cell.x + offsetX)].isAlive())
                            neighbours.add(cell);
        return neighbours.size();
    }

    public Cell[][] population() {
        return cells;
    }

    private boolean isValidPosition(int y, int x) {
        return (y >= 0 && y < cells.length) &&
                (x >= 0 && x < cells[0].length);
    }

    public class JaggeredArraysNotAllowed extends RuntimeException {}
}
