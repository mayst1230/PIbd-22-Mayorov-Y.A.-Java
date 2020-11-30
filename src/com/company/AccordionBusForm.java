package com.company;

import javax.swing.*;
import java.awt.*;

public class AccordionBusForm {
    private JButton up_button;
    private JButton left_button;
    private JButton right_button;
    private JButton down_button;
    private Transport transport;
    private JFrame frame;
    private DrawBus draw;

    public void direction(JButton button) {
        String temp = button.getName();
        switch (temp) {
            case "Up" -> {
                transport.moveTransport(Direction.Up);
            }
            case "Down" -> {
                transport.moveTransport(Direction.Down);
            }
            case "Left" -> {
                transport.moveTransport(Direction.Left);
            }
            case "Right" -> {
                transport.moveTransport(Direction.Right);
            }
        }
        frame.repaint();
    }

    public void initialization() {
        Icon up = new ImageIcon("Images/strUp.jpg");
        Icon down = new ImageIcon("Images/strDown.jpg");
        Icon left = new ImageIcon("Images/strLeft.jpg");
        Icon right = new ImageIcon("Images/strRight.jpg");
        up_button = new JButton(up);
        up_button.setName("Up");
        up_button.setBounds(800, 300, 30, 30);
        up_button.addActionListener(e -> {
            direction(up_button);
        });

        down_button = new JButton(down);
        down_button.setName("Down");
        down_button.setBounds(800, 400, 30, 30);
        down_button.addActionListener(e -> {
            direction(down_button);
        });

        right_button = new JButton(right);
        right_button.setName("Right");
        right_button.setBounds(850, 350, 30, 30);
        right_button.addActionListener(e -> {
            direction(right_button);
        });

        left_button = new JButton(left);
        left_button.setName("Left");
        left_button.setBounds(750, 350, 30, 30);
        left_button.addActionListener(e -> {
            direction(left_button);
        });
    }

    public AccordionBusForm() {
        draw = new DrawBus();
        frame = new JFrame("Автобус");
        frame.setSize(900, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(up_button);
        frame.getContentPane().add(down_button);
        frame.getContentPane().add(left_button);
        frame.getContentPane().add(right_button);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    public void setBus(Transport transport) {
        this.transport = transport;
        draw.setTransport(transport);
        frame.repaint();
    }
}