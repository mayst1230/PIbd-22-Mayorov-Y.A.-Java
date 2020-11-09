package com.company;

import java.awt.*;


public class AccordionBus extends BusVehicle {

    public Color OtherColor;
    public boolean Windows;
    public boolean Accordion;
    public boolean Doors;
    public boolean Wheels;
    private Adding adding;

    public float getStartPosX() {
        return startPosX;
    }

    private void setStartPosX(int startPosX) {
        this.startPosX = startPosX;
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

    public AccordionBus(int maxSpeed, float weight, Color mainColor, Color otherColor, boolean windows, boolean wheels, boolean accordion, int add, int numbers) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.OtherColor = otherColor;
        this.Windows = windows;
        this.Wheels = wheels;
        this.Accordion = accordion;
        switch (add) {
            case 0:
                adding = new DopClass(numbers);
                break;
            case 1:
                adding = new CircleDoors(numbers);
                break;
            case 2:
                adding = new OvalDoors(numbers);
                break;
        }
    }

    public void draw(Graphics g) {

        g.setColor(mainColor);
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
        super.draw(g);
        adding.draw(g, startPosX, startPosY);
    }
}