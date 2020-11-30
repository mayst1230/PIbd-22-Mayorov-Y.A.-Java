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

    public Adding getAdding() {
        return adding;
    }

    public void setAdding(Adding adding) {
        this.adding = adding;
    }

    public boolean isWindows() {
        return Windows;
    }

    public void setWindows(boolean windows) {
        this.Windows = windows;
    }

    public boolean isDoors() {
        return Doors;
    }

    public void setDoors(boolean doors) {
        this.Doors = doors;
    }

    public boolean isWheels() {
        return Wheels;
    }

    public void setWheels(boolean wheels) {
        this.Wheels = wheels;
    }

    public boolean isAccordion() {
        return Accordion;
    }

    public void setAccordion(boolean accordion) {
        this.Accordion = accordion;
    }

    public void setOtherColor(Color otherColor) {
        this.OtherColor = otherColor;
    }

    public AccordionBus(int maxSpeed, float weight, Color mainColor, Color otherColor, boolean windows, boolean wheels, boolean accordion) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.OtherColor = otherColor;
        this.Windows = windows;
        this.Wheels = wheels;
        this.Accordion = accordion;
    }

    public AccordionBus(String info) {
        super("");
        String[] string = info.split(separator);
        if (string.length == 8) {
            maxSpeed = Integer.parseInt(string[0]);
            weight = Float.parseFloat(string[1]);
            mainColor = Color.decode(string[2]);
            OtherColor = Color.decode(string[3]);
            Windows = Boolean.parseBoolean(string[4]);
            Wheels = Boolean.parseBoolean(string[5]);
            Accordion = Boolean.parseBoolean(string[6]);
            if (string[7].contains("null")) {
                adding = null;
            } else {
                String[] argsAddition = string[7].split("\\.");
                int number = Integer.parseInt(argsAddition[1]);
                switch (argsAddition[0]) {
                    case "RectangleDoors" -> adding = new RectangleDoors(number);
                    case "CircleDoors" -> adding = new CircleDoors(number);
                    case "OvalDoors" -> adding = new OvalDoors(number);
                }
            }
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
        if (adding != null) {
            adding.draw(g, startPosX, startPosY);
        }
    }

    @Override
    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB() + separator + OtherColor.getRGB() + separator
                + Windows + separator + Wheels + separator + Accordion + separator + adding;
    }
}