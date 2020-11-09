package com.company;

import javax.swing.*;
import javax.swing.border.Border;

public class ParkingForm {

    private JFrame frame;
    private JButton parkBus;
    private JButton parkAccordionBus;
    private JButton takeTransport;
    private JButton compareEquality;
    private JButton compareInequality;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private Parking<BusVehicle, Adding> parking;
    private DrawParking drawParking;
    private JPanel groupBox;
    private JPanel equateGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private Border borderTake;
    private Border borderCompare;

    public ParkingForm() {
        initialization();
        frame = new JFrame("Парковка");
        frame.setSize(1200, 575);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkBus);
        frame.getContentPane().add(parkAccordionBus);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(equateGroupBox);
        frame.getContentPane().add(drawParking);
        frame.repaint();
    }

    public void initialization() {
        parking = new Parking<>(900, 450);
        drawParking = new DrawParking(parking);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCompare = BorderFactory.createTitledBorder("Сравнение");
        parkBus = new JButton("AddBus");
        parkAccordionBus = new JButton("AddAccBus");
        compareEquality = new JButton("==");
        compareInequality = new JButton("!=");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.add(placeText);
        groupBox.add(placeTransport);
        groupBox.add(takeTransport);
        parkBus.setBounds(850, 12, 300, 40);
        parkBus.addActionListener(e -> createBus());
        parkAccordionBus.setBounds(850, 60, 300, 40);
        parkAccordionBus.addActionListener(e -> createAccordionBus());
        groupBox.setBounds(930, 150, 150, 100);
        placeText.setBounds(40, 20, 60, 30);
        placeTransport.setBounds(85, 20, 30, 30);
        takeTransport.setBounds(40, 60, 90, 30);
        takeTransport.addActionListener(e -> takeBus());
        groupBox.setBorder(borderTake);
        drawParking.setBounds(0, 0, 900, 570);
        equateGroupBox = new JPanel();
        equateGroupBox.setLayout(null);
        equateGroupBox.setBorder(borderCompare);
        equateGroupBox.add(compareEquality);
        equateGroupBox.add(compareInequality);
        equateGroupBox.add(countPlaceTransport);
        equateGroupBox.add(placeCountText);
        equateGroupBox.setBounds(930, 300, 150, 150);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
        compareInequality.setBounds(40, 60, 90, 30);
        compareInequality.addActionListener(e -> compare(compareInequality.getText()));
        compareEquality.setBounds(40, 100, 90, 30);
        compareEquality.addActionListener(e -> compare(compareEquality.getText()));
    }

    private void createBus() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            BusVehicle transport = new BusVehicle(100, 1000, colorDialog.getColor());
            if (parking.add(transport)) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Парковка переполнена");
            }
        }
    }

    private void createAccordionBus() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            JColorChooser otherColorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, otherColorDialog);
            if (otherColorDialog.getColor() != null) {
                BusVehicle transport = new AccordionBus(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                        true, true, true, 0, 0);
                if (parking.add(transport)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Парковка переполнена");
                }
            }
        }
    }

    private void takeBus() {
        if (!placeTransport.getText().equals("")) {
            try {
                BusVehicle transport = parking.delete(Integer.parseInt(placeTransport.getText()));
                if (transport != null) {
                    AccordionBusForm accordionBusFormForm = new AccordionBusForm();
                    accordionBusFormForm.setBus(transport);
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Транспорта не существует");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Транспорта не существует");
            }
        }
    }

    private void compare(String comparison) {
        int number = Integer.parseInt(countPlaceTransport.getText());
        if (!countPlaceTransport.getText().equals("")) {
            if (comparison.equals("==")) {
                if (parking.equal(number)) {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " = количеству занятых мест на стоянке");
                } else {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " != количеству занятых мест на стоянке");
                }
            }
            if (comparison.equals("!=")) {
                if (parking.inequal(number)) {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " != количеству занятых мест на стоянке");
                } else {
                    JOptionPane.showMessageDialog(frame, "Введенное число " + number + " = количеству занятых мест на стоянке");
                }
            }
        }
    }
}