package com.company;

import javax.swing.*;
import java.awt.*;

public class AccordionBusForm {

    private JButton create_button;
    private JButton up_button;
    private JButton left_button;
    private JButton right_button;
    private JButton down_button;
    private JComboBox<String> choise_button;
    private AccordionBus accordionBus;
    private JFrame frame;
    private DrawBus draw;

    public void direction(JButton button) {
        String temp = button.getName();
        switch (temp) {
            case "Up" -> {
                accordionBus.MoveTrans(Direction.Up);
            }
            case "Down" -> {
                accordionBus.MoveTrans(Direction.Down);
            }
            case "Left" -> {
                accordionBus.MoveTrans(Direction.Left);
            }
            case "Right" -> {
                accordionBus.MoveTrans(Direction.Right);
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

        up_button.setEnabled(false);
        down_button.setEnabled(false);
        right_button.setEnabled(false);
        left_button.setEnabled(false);

        create_button = new JButton("Создать");
        create_button.setBounds(0, 0, 90, 30);
        create_button.addActionListener(e -> {
            accordionBus = new AccordionBus(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.GREEN, Color.GRAY, true, true, true, choise_button.getSelectedIndex() + 3);
            accordionBus.SetPosition(30 + ((int) (Math.random() * 100)), 30 + ((int) (Math.random() * 100)), 900, 500);
            up_button.setEnabled(true);
            down_button.setEnabled(true);
            right_button.setEnabled(true);
            left_button.setEnabled(true);
            draw.setAccordionBus(accordionBus);
            frame.repaint();
        });
        choise_button = new JComboBox<>(new String[]{"3", "4", "5"});
        choise_button.setBounds(0, 40, 90, 30);
    }

    public AccordionBusForm() {
        draw = new DrawBus();
        frame = new JFrame("Bus");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(create_button);
        frame.getContentPane().add(up_button);
        frame.getContentPane().add(down_button);
        frame.getContentPane().add(left_button);
        frame.getContentPane().add(right_button);
        frame.getContentPane().add(choise_button);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }
}