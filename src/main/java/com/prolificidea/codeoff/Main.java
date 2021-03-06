package com.prolificidea.codeoff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Main {

    private static final KeyStroke ESCAPE_KEY = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    public static final String CLOSE_WINDOW_ACTION = "com.spodding.tackline.dispatch:WINDOW_CLOSING";

    public static void addCloseEventOnEscape(final JFrame dialog) {
        Action actionClosingEvent = new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
            }
        };

        JRootPane root = dialog.getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ESCAPE_KEY, CLOSE_WINDOW_ACTION);
        root.getActionMap().put(CLOSE_WINDOW_ACTION, actionClosingEvent);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Twitter Rain");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(true);

        addCloseEventOnEscape(frame);

        Rain rain = new Rain(frame.getSize().getHeight(), frame.getSize().getWidth());

        rain.setBackground(Color.black);
        frame.add(rain);
        frame.setBackground(Color.black);
        frame.setVisible(true);

        FetchTwitterFeed.makeItRainTwitter(rain);
    }
}