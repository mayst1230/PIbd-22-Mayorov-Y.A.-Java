package com.company;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.LinkedList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

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
    private Logger logger;

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
        logger = LogManager.getLogger(ParkingForm.class);
        PropertyConfigurator.configure("src/com/company/log4j2.properties");
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
        if (listBoxParkings.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            TransportConfigPanel configPanel = new TransportConfigPanel(frame);
            Transport bus = configPanel.getTransport();
            if (bus == null) {
                return;
            }
            if (parkingCollection.get(listBoxParkings.getSelectedValue()).add(bus)) {
                logger.info("На парковку " + listBoxParkings.getSelectedValue() + " был добавлен автобус " + bus);
                frame.repaint();
            }
        } catch (ParkingOverflowException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
        }
    }

    private void placeIntoListTransport() {
        if (listBoxParkings.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Transport bus = parkingCollection.get(listBoxParkings.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
            if (bus != null) {
                listTransport.add((BusVehicle) bus);
                frame.repaint();
                logger.info("С парковки  " + listBoxParkings.getSelectedValue() + " был изъят автобус " + bus + " и помещен в коллекцию");
            }
        } catch (ParkingNotFoundException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
            logger.warn(e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
            logger.fatal(e.getMessage());
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
            logger.info("Добавлена парковка " + parkingField.getText());
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
                logger.info("Парковка " + listBoxParkings.getSelectedValue() + " удалена");
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Парковка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeItemList() {
        drawParkings.setSelectedItem(listBoxParkings.getSelectedValue());
        if (listBoxParkings.getSelectedValue() != null) {
            logger.info("Парковка " + listBoxParkings.getSelectedValue() + " выбрана пользователем");
        }
        frame.repaint();
    }

    private void takeTransportFrame() {
        if (!listTransport.isEmpty()) {
            AccordionBusForm accordionBusFormForm = new AccordionBusForm();
            BusVehicle bus = listTransport.get(0);
            accordionBusFormForm.setBus(Objects.requireNonNull(bus));
            listTransport.remove(0);
            logger.info("Автобус " + bus + " был изъят из коллекции");
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Коллекция пуста", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveFile() {
        JFileChooser fileSaveDialog = new JFileChooser();
        fileSaveDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileSaveDialog.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                parkingCollection.saveData(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка при сохранении файла", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadFile() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                parkingCollection.loadData(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные были загружены из файла " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (ParkingOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
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
            try {
                parkingCollection.saveData(fileSaveDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Ошибка при сохранении файла", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }

    private void loadParking() {
        JFileChooser fileOpenDialog = new JFileChooser();
        fileOpenDialog.setFileFilter(new FileNameExtensionFilter("Текстовый файл", "txt"));
        int result = fileOpenDialog.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                parkingCollection.loadData(fileOpenDialog.getSelectedFile().getPath());
                JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                logger.info("Данные были загружены из файла " + fileOpenDialog.getSelectedFile().getPath());
                reloadLevels();
                frame.repaint();
            } catch (ParkingOverflowException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                logger.warn(e.getMessage());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                logger.error(e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                logger.fatal(e.getMessage());
            }
        }
    }
}