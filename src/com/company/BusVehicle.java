package com.company;

import java.awt.*;

public class BusVehicle extends Vehicle {

    protected int busVehicleWidth = 100;
    protected int busVehicleHeight = 100;
    protected double changeWidth = 3.1;
    protected double changeHeight = 2.1;

    public BusVehicle(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected BusVehicle(int maxSpeed, float weight, Color mainColor, int busWidth, int busHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.busVehicleWidth = busWidth;
        this.busVehicleHeight = busHeight;
    }

    @Override
    public void draw(Graphics g) {
        //передний корпус
        g.setColor(mainColor);
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

        //отрисовка дверей в передней части автобуса
        g.setColor(Color.CYAN);
        g.fillRect(startPosX + 10, startPosY + 10, 20, 30);
        g.fillRect(startPosX + 80, startPosY + 10, 20, 30);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 10, startPosY + 10, 20, 30);
        g.drawRect(startPosX + 18, startPosY + 10, 2, 30);
        g.drawRect(startPosX + 80, startPosY + 10, 20, 30);
        g.drawRect(startPosX + 88, startPosY + 10, 2, 30);

    }

    @Override
    public void moveTransport(Direction direction) {
        float step = maxSpeed * 100 / weight;
        switch (direction) {
            case Right:
                if (startPosX + step < pictureWidth - busVehicleWidth * changeWidth) {
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
                if (startPosY + step < pictureHeight - busVehicleHeight * changeHeight) {
                    startPosY += step;
                }
                break;
        }
    }
}