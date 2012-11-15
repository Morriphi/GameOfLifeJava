package gol.test;

import gol.ui.Controls;
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class ControlsTest {
    public Controls controls;

    @Before
    public void setUp() throws Exception {
        controls = new Controls();
    }

    @Test
    public void shouldHaveControls() {
        assertButton("Start", atIndex(0));
        assertButton("Stop", atIndex(1));
        assertButton("Restart", atIndex(2));
    }

    private void assertButton(String name, int index) {
        assertEquals(name + " button", controls.getComponents()[index].getClass(), JButton.class);
        assertEquals(name + " button", ((JButton) controls.getComponents()[index]).getText(), name);
    }

    private int atIndex(int index) {
        return index;
    }
}
