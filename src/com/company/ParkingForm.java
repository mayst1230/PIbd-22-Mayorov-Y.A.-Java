package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.LinkedList;
import java.util.List;

public class ParkingForm {
    private JFrame frame;
    private JButton parkTransport;
    private JButton takeTransport;
    private JButton addParking;
    private JButton deleteParking;
    private JButton putTransportIntoList;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private DrawParking drawParkings;
    private JPanel groupBoxTake;
    private JPanel parkingsGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private JTextField parkingField;
    private JList<String> listBoxParkings;
    private Border borderTake;
    private Border borderParkings;
    private DefaultListModel<String> parkingList;
    private List<BusVehicle> listTransport;
    private ParkingCollection parkingCollection;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu parkingFileMenu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JMenuItem saveParking;
    private JMenuItem loadParking;

    public ParkingForm() {
        initialization();
        frame = new JFrame("Парковки");
        frame.setSize(1200, 575);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkTransport);
        frame.getContentPane().add(groupBoxTake);
        frame.getContentPane().add(drawParkings);
        frame.getContentPane().add(parkingsGroupBox);
        frame.setJMenuBar(menuBar);
        frame.repaint();
    }

    public void initialization() {
        listTransport = new LinkedList<>();
        parkingCollection = new ParkingCollection(900, 450);
        drawParkings = new DrawParking(parkingCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderParkings = BorderFactory.createTitledBorder("Парковки");
        parkTransport = new JButton("Припарковать транспорт");
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
        parkTransport.setBounds(850, 10, 300, 90);
        parkTransport.addActionListener(e -> {
            createTransport();
        });
        groupBoxTake = new JPanel();
        groupBoxTake.setLayout(null);
        groupBoxTake.add(placeText);
        groupBoxTake.add(placeTransport);
        groupBoxTake.add(takeTransport);
        groupBoxTake.add(putTransportIntoList);
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

        //TODO: МЕНЮ ФАЙЛ
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        saveFile = new JMenuItem("Сохранить");
        saveFile.addActionListener(e -> {
            saveFile();
        });
        loadFile = new JMenuItem("Загрузить");
        loadFile.addActionListener(e -> {
            loadFile();
        });
        parkingFileMenu = new JMenu("Парковка");
        saveParking = new JMenuItem("Сохранить");
        saveParking.addActionListener(e -> {
            saveParking();
        });
        loadParking = new JMenuItem("Загрузить");
        loadParking.addActionListener(e -> {
            loadParking();
        });
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);
        parkingFileMenu.add(saveParking);
        parkingFileMenu.add(loadParking);
        menuBar.add(fileMenu);
        menuBar.add(parkingFileMenu);
    }

    private void createTransport() {
        if (listBoxParkings.getSelectedIndex() >= 0) {
            TransportConfigPanel configPanel = new TransportConfigPanel(frame);
            Transport bus = configPanel.getTransport();
            if (bus != null) {
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

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (parkingCollection.saveData(fileSaveDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно сохранен", "Результат", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не сохранен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (parkingCollection.loadData(fileOpenDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не загружен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveParking() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        if (listBoxParkings.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Выберите парковку", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (parkingCollection.saveParking(fileSaveDialog.getSelectedFile().getPath(), listBoxParkings.getSelectedValue())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно сохранен", "Результат", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не сохранен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadParking() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (parkingCollection.loadParking(fileOpenDialog.getSelectedFile().getPath())) {
                JOptionPane.showMessageDialog(frame, "Файл успешно загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                reloadLevels();
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Файл не загружен", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}