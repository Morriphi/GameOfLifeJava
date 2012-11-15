package gol.test;

import gol.ui.Game;
import gol.ui.Board;
import gol.ui.Controls;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {
    public Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void title() {
        assertEquals("game title", Game.TITLE, game.getTitle());
    }

    @Test
    public void size() {
        assertEquals("game size", Game.INITIAL_SIZE, game.getSize());
    }

    @Test
    public void location() {
         assertEquals("game location", Game.INITIAL_POSITION, game.getLocation());
    }

    @Test
    public void defaultCloseOperation() {
         assertEquals("default close operation", WindowConstants.EXIT_ON_CLOSE, game.getDefaultCloseOperation());
    }

    @Test
    public void isVisible() {
        assertTrue("is visible", game.isVisible());
    }

    @Test
    public void layout() {
         assertEquals("layout", game.getContentPane().getLayout().getClass(), BorderLayout.class);
    }

    @Test
    public void board() {
         assertEquals("has a board", game.getContentPane().getComponents()[0].getClass(), Board.class);
    }

    @Test
    public void controlPanel() {
         assertEquals("has a control panel", game.getContentPane().getComponents()[1].getClass(), Controls.class);
    }

    @Test
    public void resize() {
         assertFalse("can resize", game.isResizable());
    }

}
