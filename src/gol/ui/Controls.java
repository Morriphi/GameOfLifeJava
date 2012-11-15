package gol.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Controls extends JPanel {
    public final JButton start;
    public final JButton stop;
    public final JButton restart;

    public Controls(){
        start = new JButton();
        start.setText("Start");

        stop = new JButton();
        stop.setText("Stop");

        restart = new JButton();
        restart.setText("Restart");

        add(start);
        add(stop);
        add(restart);
    }

    public void onStart(ActionListener listener) {
        start.addActionListener(listener);
    }

    public void onStop(ActionListener listener) {
        stop.addActionListener(listener);
    }

    public void onRestart(ActionListener listener) {
        restart.addActionListener(listener);
    }
}
