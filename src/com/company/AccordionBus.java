package com.company;

import java.awt.*;

public class AccordionBus {

    private int startPosX;
    private int startPosY;
    private int pictureWidth;
    private int pictureHeight;
    private final int accordionBusWidth = 300;
    private final int accordionBusHeight = 100;
    private final double changeWidth = 1.2;
    private final double changeHeight = 1;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color OtherColor;
    public boolean Windows;
    public boolean Accordion;
    public boolean Doors;
    public boolean Wheels;
    private DopClass bus;

    public float getStartPosX() {
        return startPosX;
    }

    private void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
    }

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        MaxSpeed = maxSpeed;
    }

    public float getWeight() {
        return Weight;
    }

    private void setWeight(float weight) {
        Weight = weight;
    }

    public Color getMainColor() {
        return MainColor;
    }

    private void setMainColor(Color mainColor) {
        MainColor = mainColor;
    }

    public Color getOtherColor() {
        return OtherColor;
    }

    public void setOtherColor(Color otherColor) {
        OtherColor = otherColor;
    }

    public boolean isWindows() {
        return Windows;
    }

    public void setWindows(boolean windows) {
        Windows = windows;
    }

    public boolean isDoors() {
        return Doors;
    }

    public void setDoors(boolean doors) {
        Doors = doors;
    }

    public boolean isWheels() {
        return Wheels;
    }

    public void setWheels(boolean wheels) {
        Wheels = wheels;
    }

    public boolean isAccordion() {
        return Accordion;
    }

    public void setAccordion(boolean accordion) {
        Accordion = accordion;
    }

    public AccordionBus(int maxSpeed, float weight, Color mainColor, Color otherColor, boolean windows, boolean wheels, boolean accordion, int countDoors) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        OtherColor = otherColor;
        Windows = windows;
        Wheels = wheels;
        Accordion = accordion;
        bus = new DopClass();
        bus.setNumber(countDoors);
    }

    public void SetPosition(int x, int y, int width, int height) {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
    }

    public void MoveTrans(Direction dir) {
        float step = MaxSpeed * 300 / Weight;
        switch (dir) {
            case Right:
                if (startPosX + step < pictureWidth - accordionBusWidth * changeWidth) {
                    startPosX += step;
                }
                break;
            case Left:
                if (startPosX - step > 0) {
                    startPosX -= step;
                }
                break;
            case Up:
                if (startPosY - step > 0) {
                    startPosY -= step;
                }
                break;
            case Down:
                if (startPosY + step < pictureHeight - accordionBusHeight * changeHeight) {
                    startPosY += step;
                }
                break;
        }
    }

    public void DrawBus(Graphics g) {
        g.setColor(MainColor);
        g.fillRect(startPosX + 190, startPosY - 1, 130, 42);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 190, startPosY - 1, 130, 42);

        if (Accordion) {
            g.setColor(OtherColor);
            g.fillRect(startPosX + 160, startPosY, 5, 40);
            g.fillRect(startPosX + 165, startPosY, 5, 40);
            g.fillRect(startPosX + 170, startPosY, 5, 40);
            g.fillRect(startPosX + 175, startPosY, 5, 40);
            g.fillRect(startPosX + 180, startPosY, 5, 40);
            g.fillRect(startPosX + 185, startPosY, 5, 40);

            g.setColor(Color.BLACK);
            g.drawRect(startPosX + 160, startPosY, 5, 40);
            g.drawRect(startPosX + 165, startPosY, 5, 40);
            g.drawRect(startPosX + 170, startPosY, 5, 40);
            g.drawRect(startPosX + 175, startPosY, 5, 40);
            g.drawRect(startPosX + 180, startPosY, 5, 40);
            g.drawRect(startPosX + 185, startPosY, 5, 40);
        }

        if (Windows) {
            //задние окна
            g.setColor(Color.CYAN);
            g.fillRect(startPosX + 220, startPosY + 5, 20, 20);
            g.fillRect(startPosX + 270, startPosY + 5, 20, 20);
            g.setColor(Color.BLACK);
            g.drawRect(startPosX + 220, startPosY + 5, 20, 20);
            g.drawRect(startPosX + 270, startPosY + 5, 20, 20);
        }

        if (Wheels) {
            //заднее колесо
            g.setColor(Color.BLACK);
            g.fillOval(startPosX + 270, startPosY + 30, 20, 20);

        }
        //передний корпус
        g.setColor(MainColor);
        g.fillRect(startPosX, startPosY - 1, 160, 42);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX, startPosY - 1, 160, 42);

        //водительское окно
        g.setColor(Color.blue);
        g.fillRect(startPosX, startPosY - 1, 10, 25);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX, startPosY - 1, 10, 25);

        //передние окна
        g.setColor(Color.CYAN);
        g.fillRect(startPosX + 35, startPosY + 5, 40, 20);
        g.fillRect(startPosX + 105, startPosY + 5, 40, 20);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 35, startPosY + 5, 40, 20);
        g.drawRect(startPosX + 105, startPosY + 5, 40, 20);

        //передние колеса
        g.setColor(Color.BLACK);
        g.fillOval(startPosX + 35, startPosY + 30, 20, 20);
        g.fillOval(startPosX + 125, startPosY + 30, 20, 20);

        g.drawOval(startPosX + 35, startPosY + 30, 20, 20);
        g.drawOval(startPosX + 125, startPosY + 30, 20, 20);

        bus.DrawBus(g, startPosX, startPosY);
    }
}