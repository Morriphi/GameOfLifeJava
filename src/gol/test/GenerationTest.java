package gol.test;

import gol.domain.*;
import org.junit.Assert;
import org.junit.Test;

public class GenerationTest {
    private boolean O = false;
    private boolean X = true;

    @Test
    public void canCreateGameWithInitialState() {
        Cell[][] state = map(new boolean[][]{ {O,O,O},
                                              {O,O,O},
                                              {O,O,O} });
        Generation g = new Generation(state);

        Assert.assertArrayEquals(state, g.population());
    }

    @Test(expected = Generation.JaggeredArraysNotAllowed.class)
    public void shouldNotAllowJaggeredArrays() {
        new Generation(new Cell[][]{{new DeadCell(0,0), new DeadCell(0,0)},{new DeadCell(0,0)} });
    }

    @Test
    public void shouldKillOneCell() {
        Cell[][] state = map(new boolean[][]{ {O,O,O},
                                              {O,X,O},
                                              {O,O,O}});
        Generation g = new Generation(state);

        Assert.assertArrayEquals(map(new boolean[][]{{O, O, O},
                {O, O, O},
                {O, O, O}}), g.spawn().population());
    }

    @Test
    public void block() {
        Generation g = new Generation(map(new boolean[][]{ {X,X},
                                                 {X,O}}));

        Assert.assertArrayEquals(map(new boolean[][]{{X, X},
                {X, X}}), g.spawn().population());
    }

    @Test
    public void blinker() {
         Generation g = new Generation(map(new boolean[][]{{O,X,O},
                                                 {O,X,O},
                                                 {O,X,O}}));

        Generation secondGeneration = g.spawn();

        Assert.assertArrayEquals(map(new boolean[][]{{O, O, O},
                {X, X, X},
                {O, O, O}}), secondGeneration.population());

        Generation thirdGeneration = secondGeneration.spawn();

        Assert.assertArrayEquals(map(new boolean[][]{{O, X, O},
                {O, X, O},
                {O, X, O}}), thirdGeneration.population());
    }

    public Cell[][] map(boolean[][] state) {
        Cell[][] cells = new Cell[state.length][state[0].length];
        for(int y = 0; y < state.length; y++)
            for(int x = 0; x < state[0].length; x++)
                cells[y][x] = buildCell(y, x, state);
        return cells;
    }

    private Cell buildCell(int y, int x, boolean[][] state) {
        if(state[y][x])
            return new LivingCell(y, x);
        return new DeadCell(y, x);
    }
}
