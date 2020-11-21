package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.LinkedList;
import java.util.List;

public class ParkingForm {
    private JFrame frame;
    private JButton parkBus;
    private JButton parkAccordionBus;
    private JButton takeTransport;
    private JButton compareEquality;
    private JButton compareInequality;
    private JButton addParking;
    private JButton deleteParking;
    private JButton putTransportIntoList;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private Parking<BusVehicle, Adding> parking;
    private DrawParking drawParkings;
    private JPanel groupBoxTake;
    private JPanel equateGroupBox;
    private JPanel parkingsGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private JTextField parkingField;
    private JList<String> listBoxParkings;
    private Border borderTake;
    private Border borderCompare;
    private Border borderParkings;
    private DefaultListModel<String> parkingList;
    private List<BusVehicle> listTransport;
    private ParkingCollection parkingCollection;

    public ParkingForm() {
        initialization();
        frame = new JFrame("Парковки");
        frame.setSize(1200, 575);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkBus);
        frame.getContentPane().add(parkAccordionBus);
        frame.getContentPane().add(groupBoxTake);
        frame.getContentPane().add(drawParkings);
        frame.getContentPane().add(parkingsGroupBox);
        frame.repaint();
    }

    public void initialization() {
        listTransport = new LinkedList<>();
        parkingCollection = new ParkingCollection(900, 450);
        drawParkings = new DrawParking(parkingCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCompare = BorderFactory.createTitledBorder("Сравнение");
        borderParkings = BorderFactory.createTitledBorder("Парковки");
        parkBus = new JButton("AddBus");
        parkAccordionBus = new JButton("AddAccBus");
        compareEquality = new JButton("==");
        compareInequality = new JButton("!=");
        putTransportIntoList = new JButton("Добавить в список");
        addParking = new JButton("Добавить парковку");
        deleteParking = new JButton("Удалить парковку");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать из списка");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        parkingField = new JTextField();
        parkingList = new DefaultListModel<>();
        listBoxParkings = new JList<>(parkingList);
        groupBoxTake = new JPanel();
        groupBoxTake.setLayout(null);
        groupBoxTake.add(placeText);
        groupBoxTake.add(placeTransport);
        groupBoxTake.add(takeTransport);
        groupBoxTake.add(putTransportIntoList);
        parkBus.setBounds(850, 12, 300, 40);
        parkBus.addActionListener(e -> createBus());
        parkAccordionBus.setBounds(850, 60, 300, 40);
        parkAccordionBus.addActionListener(e -> createAccordionBus());
        groupBoxTake.setBounds(880, 110, 250, 160);
        placeText.setBounds(90, 20, 60, 30);
        placeTransport.setBounds(135, 20, 30, 30);
        putTransportIntoList.setBounds(40, 70, 170, 30);
        putTransportIntoList.addActionListener(e -> {
            placeIntoListTransport();
        });
        takeTransport.setBounds(40, 110, 170, 30);
        takeTransport.addActionListener(e -> takeTransportFrame());
        groupBoxTake.setBorder(borderTake);
        drawParkings.setBounds(0, 0, 900, 570);
        equateGroupBox = new JPanel();
        equateGroupBox.setLayout(null);
        equateGroupBox.setBorder(borderCompare);
        equateGroupBox.add(compareEquality);
        equateGroupBox.add(compareInequality);
        equateGroupBox.add(countPlaceTransport);
        equateGroupBox.add(placeCountText);
        equateGroupBox.setBounds(930, 300, 150, 150);
        parkingsGroupBox = new JPanel();
        parkingsGroupBox.setLayout(null);
        parkingsGroupBox.setBounds(880, 270, 250, 240);
        parkingsGroupBox.setBorder(borderParkings);
        parkingField.setBounds(50, 30, 150, 20);
        listBoxParkings.setBounds(50, 90, 150, 100);
        listBoxParkings.addListSelectionListener(e -> {
            changeItemList();
        });
        addParking.setBounds(50, 55, 150, 30);
        addParking.addActionListener(e -> {
            addParking();
        });
        deleteParking.setBounds(50, 200, 150, 30);
        deleteParking.addActionListener(e -> {
            deleteParking();
        });
        parkingsGroupBox.add(parkingField);
        parkingsGroupBox.add(listBoxParkings);
        parkingsGroupBox.add(addParking);
        parkingsGroupBox.add(deleteParking);

        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
        compareInequality.setBounds(40, 60, 90, 30);
        compareInequality.addActionListener(e -> compare(compareInequality.getText()));
        compareEquality.setBounds(40, 100, 90, 30);
        compareEquality.addActionListener(e -> compare(compareEquality.getText()));
    }

    private void createBus() {
        if (listBoxParkings.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                BusVehicle bus = new BusVehicle(100, 1000, colorDialog.getColor());
                if (parkingCollection.get(listBoxParkings.getSelectedValue()).add(bus)) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Парковка переполнена");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createAccordionBus() {
        if (listBoxParkings.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                JColorChooser otherColorDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, otherColorDialog);
                if (otherColorDialog.getColor() != null) {
                    BusVehicle bus = new AccordionBus(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                            true, true, true, 0, 0);
                    if (parkingCollection.get(listBoxParkings.getSelectedValue()).add(bus)) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Парковка переполнена");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void placeIntoListTransport() {
        if (listBoxParkings.getSelectedIndex() >= 0) {
            if (!placeTransport.getText().equals("")) {
                try {
                    BusVehicle bus = (BusVehicle) parkingCollection.get(listBoxParkings.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
                    if (bus != null) {
                        listTransport.add(bus);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloadLevels() {
        int index = listBoxParkings.getSelectedIndex();
        parkingList.removeAllElements();
        int i = 0;
        for (String name : parkingCollection.keys()) {
            parkingList.add(i, name);
            i++;
        }
        int itemsCount = parkingList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxParkings.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxParkings.setSelectedIndex(index);
        }
    }

    private void addParking() {
        if (!parkingField.getText().equals("")) {
            parkingCollection.addParking(parkingField.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название парковки", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteParking() {
        if (listBoxParkings.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить парковку " + listBoxParkings.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                parkingCollection.deleteParking(listBoxParkings.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
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

    private void changeItemList() {
        drawParkings.setSelectedItem(listBoxParkings.getSelectedValue());
        frame.repaint();
    }

    private void takeTransportFrame() {
        if (!listTransport.isEmpty()) {
            AccordionBusForm accordionBusFormForm = new AccordionBusForm();
            accordionBusFormForm.setBus(listTransport.get(0));
            listTransport.remove(0);
            frame.repaint();
        }
    }
}