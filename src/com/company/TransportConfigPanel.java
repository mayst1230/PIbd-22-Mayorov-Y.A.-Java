package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class TransportConfigPanel extends JDialog {
    private Vehicle transport;
    private Color busColor;
    private Adding transportAdding;
    private DrawBus drawBus;

    public TransportConfigPanel(Frame frame) {
        super(frame, true);
        setSize(835, 390);
        setLayout(null);

        //TODO: Тип автобуса
        JPanel panelTypeOfTransport = new JPanel();
        panelTypeOfTransport.setLayout(null);
        Border typeBorder = BorderFactory.createTitledBorder("Тип автобуса");
        panelTypeOfTransport.setBorder(typeBorder);

        JLabel busVehicleLable = new JLabel("Bus");
        busVehicleLable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        busVehicleLable.setBounds(10, 30, 190, 55);
        busVehicleLable.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel accBusLable = new JLabel("AccBus");
        accBusLable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        accBusLable.setBounds(10, 100, 190, 55);
        accBusLable.setHorizontalAlignment(SwingConstants.CENTER);

        JCheckBox checkBoxWindows = new JCheckBox("Окна");
        checkBoxWindows.setSelected(true);
        JCheckBox checkBoxWheels = new JCheckBox("Колеса");
        checkBoxWheels.setSelected(true);
        JCheckBox checkBoxAccordion = new JCheckBox("Гармошка");
        checkBoxAccordion.setSelected(true);

        JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        JSpinner spinnerDoorsCount = new JSpinner(new SpinnerNumberModel(3, 3, 5, 1));

        MouseAdapter listenerTransportType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel label = (JLabel) e.getSource();
                switch (label.getText()) {
                    case "Bus" -> transport = new BusVehicle((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE);
                    case "AccBus" -> transport = new AccordionBus((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE, Color.WHITE,
                            checkBoxWindows.isSelected(), checkBoxWheels.isSelected(), checkBoxAccordion.isSelected(), -1, -1);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() + panelTypeOfTransport.getX() >= drawBus.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() + panelTypeOfTransport.getX() <= drawBus.getX() + drawBus.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelTypeOfTransport.getY() >= drawBus.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelTypeOfTransport.getY() <= drawBus.getY() + drawBus.getHeight()) {
                    transport.setPosition(10, 0, drawBus.getWidth(), drawBus.getHeight());
                    drawBus.setTransport(transport);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transport = null;
            }
        };
        busVehicleLable.addMouseListener(listenerTransportType);
        accBusLable.addMouseListener(listenerTransportType);
        panelTypeOfTransport.add(busVehicleLable);
        panelTypeOfTransport.add(accBusLable);
        add(panelTypeOfTransport);
        panelTypeOfTransport.setBounds(5, 10, 210, 170);

        //TODO: Панель предпросмотра
        drawBus = new DrawBus();
        getContentPane().add(drawBus);
        drawBus.setBounds(217, 60, 323, 50);
        drawBus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        MouseListener listenerPanelDrawTransport = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (transport != null || (drawBus.getAccordionBus() instanceof AccordionBus && transportAdding != null)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (transport != null || transportAdding != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };
        drawBus.addMouseListener(listenerPanelDrawTransport);

        //TODO: Панель параметров автобуса
        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Параметры");
        panelOptions.setBorder(typeBorder);
        JLabel labelMaxSpeed = new JLabel("Макс скорость");
        JLabel labelWight = new JLabel("Вес");
        panelOptions.setBounds(5, 185, 210, 110);
        labelMaxSpeed.setBounds(10, 20, 100, 15);
        spinnerMaxSpeed.setBounds(10, 35, 100, 25);
        labelWight.setBounds(10, 60, 100, 15);
        spinnerWeight.setBounds(10, 75, 100, 25);
        checkBoxWindows.setBounds(115, 35, 90, 20);
        checkBoxWheels.setBounds(115, 55, 90, 20);
        checkBoxAccordion.setBounds(115, 75, 90, 20);
        panelOptions.add(spinnerMaxSpeed);
        panelOptions.add(spinnerWeight);
        panelOptions.add(labelMaxSpeed);
        panelOptions.add(labelWight);
        panelOptions.add(checkBoxWindows);
        panelOptions.add(checkBoxWheels);
        panelOptions.add(checkBoxAccordion);
        add(panelOptions);

        //TODO: Выбор цвета
        JPanel panelColors = new JPanel();
        panelColors.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Цвет");
        panelColors.setBorder(typeBorder);

        JLabel labelMainColor = new JLabel("Основной цвет");
        labelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMainColor.setBounds(10, 25, 100, 30);
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelOtherColor = new JLabel("Доп. цвет");
        labelOtherColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelOtherColor.setBounds(120, 25, 140, 30);
        labelOtherColor.setHorizontalAlignment(SwingConstants.CENTER);

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                busColor = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawBus.getAccordionBus() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelMainColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelMainColor.getX() + labelMainColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelMainColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelMainColor.getY() + labelMainColor.getHeight()) {
                        drawBus.getAccordionBus().setMainColor(busColor);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelOtherColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelOtherColor.getX() + labelOtherColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelOtherColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelOtherColor.getY() + labelOtherColor.getHeight() &&
                            drawBus.getAccordionBus() instanceof AccordionBus) {
                        AccordionBus accordionBus = (AccordionBus) drawBus.getAccordionBus();
                        accordionBus.setOtherColor(busColor);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                busColor = null;
            }
        };

        MouseListener listenerColorLabel = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Основной цвет" -> {
                        if (drawBus.getAccordionBus() != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                    case "Дополнительный цвет" -> {
                        if (drawBus.getAccordionBus() instanceof AccordionBus) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (busColor != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        labelMainColor.addMouseListener(listenerColorLabel);
        labelOtherColor.addMouseListener(listenerColorLabel);

        JPanel panelDarkRed = new JPanel();
        panelDarkRed.setBackground(new Color(176, 0, 0));
        panelDarkRed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDarkRed.setBounds(10, 75, 30, 30);
        panelDarkRed.addMouseListener(listenerColor);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelYellow.setBounds(90, 75, 30, 30);
        panelYellow.addMouseListener(listenerColor);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlack.setBounds(160, 75, 30, 30);
        panelBlack.addMouseListener(listenerColor);

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelWhite.setBounds(230, 75, 30, 30);
        panelWhite.addMouseListener(listenerColor);

        JPanel panelDeepPink = new JPanel();
        panelDeepPink.setBackground(new Color(255, 20, 147));
        panelDeepPink.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDeepPink.setBounds(10, 120, 30, 30);
        panelDeepPink.addMouseListener(listenerColor);

        JPanel panelOrange = new JPanel();
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelOrange.setBounds(90, 120, 30, 30);
        panelOrange.addMouseListener(listenerColor);

        JPanel panelDarkGreen = new JPanel();
        panelDarkGreen.setBackground(new Color(18, 73, 2));
        panelDarkGreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDarkGreen.setBounds(160, 120, 30, 30);
        panelDarkGreen.addMouseListener(listenerColor);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlue.setBounds(230, 120, 30, 30);
        panelBlue.addMouseListener(listenerColor);

        panelColors.setBounds(540, 10, 275, 170);
        add(panelColors);
        panelColors.add(labelMainColor);
        panelColors.add(labelOtherColor);
        panelColors.add(panelDarkRed);
        panelColors.add(panelYellow);
        panelColors.add(panelBlack);
        panelColors.add(panelWhite);
        panelColors.add(panelDeepPink);
        panelColors.add(panelOrange);
        panelColors.add(panelDarkGreen);
        panelColors.add(panelBlue);

        //TODO: Панель с типом дверей
        JPanel panelAdditions = new JPanel();
        panelAdditions.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Тип дверей");
        panelAdditions.setBorder(typeBorder);

        add(panelAdditions);
        panelAdditions.setBounds(245, 185, 570, 110);

        JLabel labelRectangle = new JLabel("Прямоугольные");
        labelRectangle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelRectangle.setBounds(10, 30, 120, 55);
        labelRectangle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelCircle = new JLabel("Круглые");
        labelCircle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelCircle.setBounds(140, 30, 120, 55);
        labelCircle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelOval = new JLabel("Овальные");
        labelOval.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelOval.setBounds(270, 30, 120, 55);
        labelOval.setHorizontalAlignment(SwingConstants.CENTER);

        panelAdditions.add(labelRectangle);
        panelAdditions.add(labelCircle);
        panelAdditions.add(labelOval);

        JLabel labelDoorsCount = new JLabel("Количество дверей");
        labelDoorsCount.setBounds(430, 30, 120, 15);
        spinnerDoorsCount.setBounds(430, 50, 80, 25);

        panelAdditions.add(labelDoorsCount);
        panelAdditions.add(spinnerDoorsCount);

        MouseAdapter listenerAdding = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel labelAdditions = (JLabel) e.getSource();
                switch (labelAdditions.getText()) {
                    case "Прямоугольные" -> transportAdding = new RectangleDoors((int) spinnerDoorsCount.getValue() - 3);
                    case "Круглые" -> transportAdding = new CircleDoors((int) spinnerDoorsCount.getValue() - 3);
                    case "Овальные" -> transportAdding = new OvalDoors((int) spinnerDoorsCount.getValue() - 3);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawBus.getAccordionBus() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() + panelAdditions.getX() >= drawBus.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getAlignmentX() + panelAdditions.getX() <= drawBus.getX() + drawBus.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAdditions.getY() >= drawBus.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAdditions.getY() <= drawBus.getY() + drawBus.getHeight() &&
                            drawBus.getAccordionBus() instanceof AccordionBus) {
                        AccordionBus accordionBus = (AccordionBus) drawBus.getAccordionBus();
                        accordionBus.setAdding(transportAdding);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transportAdding = null;
            }
        };

        labelRectangle.addMouseListener(listenerAdding);
        labelCircle.addMouseListener(listenerAdding);
        labelOval.addMouseListener(listenerAdding);

        JButton buttonAdd = new JButton("ОК");
        JButton buttonClear = new JButton("Отмена");
        add(buttonAdd);
        add(buttonClear);
        buttonAdd.setBounds(530, 300, 120, 30);
        buttonClear.setBounds(660, 300, 120, 30);
        buttonAdd.addActionListener(e -> dispose());
        buttonClear.addActionListener(e -> {
            drawBus.setTransport(null);
            dispose();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                drawBus.setTransport(null);
            }
        });
        setVisible(true);
    }

    public Transport getTransport() {
        return drawBus.getAccordionBus();
    }
}