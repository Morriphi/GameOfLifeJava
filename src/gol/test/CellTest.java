package gol.test;

import gol.domain.*;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CellTest {
    @Test
    public void livingCell_shouldBeAlive() {
        assertTrue(new LivingCell(0, 0).isAlive());
    }

    @Test
    public void deadCell_shouldNotBeAlive() {
        assertFalse(new DeadCell(0, 0).isAlive()) ;
    }

    @Test
     public void live_cell_with_two_live_neighbours_lives_on_to_the_next_generation() {
        LivingCell cell = new LivingCell(0,0);
        assertTrue(cell.shouldLiveNextGeneration(new INeighbourhood() {
            public int numberOfLiveNeighboursFor(Cell cell) {
                return 2;
            }
        }));
    }

    @Test
    public void live_cell_with_three_live_neighbours_lives_on_to_the_next_generation() {
        LivingCell cell = new LivingCell(0,0);
        assertTrue(cell.shouldLiveNextGeneration(new INeighbourhood() {
            public int numberOfLiveNeighboursFor(Cell cell) {
                return 3;
            }
        }));
    }
    
    @Test
    public void live_cell_with_fewer_than_two_live_neighbours_dies_as_if_caused_by_under_population() {
        LivingCell cell = new LivingCell(0,0);
        assertFalse(cell.shouldLiveNextGeneration(new INeighbourhood() {
            public int numberOfLiveNeighboursFor(Cell cell) {
                return 1;
            }
        }));
    }

    @Test
    public void live_cell_with_more_than_three_live_neighbours_dies_as_if_by_overcrowding() {
        LivingCell cell = new LivingCell(0,0);
        assertFalse(cell.shouldLiveNextGeneration(new INeighbourhood() {
            public int numberOfLiveNeighboursFor(Cell cell) {
                return 4;
            }
        }));
    }

    @Test
    public void dead_cell_with_exactly_three_live_neighbours_becomes_a_live_cell_as_if_by_reproduction() {
        DeadCell cell = new DeadCell(0,0);
        assertTrue(cell.shouldLiveNextGeneration(new INeighbourhood() {
            public int numberOfLiveNeighboursFor(Cell cell) {
                return 3;
            }
        }));
    }

    @Test
    public void valueObject() {
        DeadCell cellA1 = new DeadCell(0, 0);
        DeadCell cellA2 = new DeadCell(0, 0);
        DeadCell cellB = new DeadCell(1, 1);
        LivingCell cellC = new LivingCell(0, 0);

        assertTrue(cellA1.equals(cellA2));
        assertFalse(cellA1.equals(cellB));
        assertFalse(cellA1.equals(cellC));
        assertFalse(cellA1.equals(null));
        assertFalse(cellC.equals(null));
        assertTrue(cellA1.hashCode() == cellA2.hashCode());
        assertFalse(cellA1.hashCode() == cellB.hashCode());
    }
}
