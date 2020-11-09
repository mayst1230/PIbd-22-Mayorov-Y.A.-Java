package com.company;

import java.awt.*;

public class DopClass {

    private DoorsCount count;

    public void setNumber(int number) {
        count = DoorsCount.getCount(number);
    }

    public void DrawBus(Graphics g, int startPosX, int startPosY) {
        switch (count) {
            case five: {
                g.setColor(Color.CYAN);
                g.fillRect(startPosX + 300, startPosY + 10, 20, 30);
                g.setColor(Color.BLACK);
                g.drawRect(startPosX + 300, startPosY + 10, 20, 30);
                g.drawRect(startPosX + 308, startPosY + 10, 2, 30);
            }
            case four: {
                g.setColor(Color.CYAN);
                g.fillRect(startPosX + 245, startPosY + 10, 20, 30);
                g.setColor(Color.BLACK);
                g.drawRect(startPosX + 245, startPosY + 10, 20, 30);
                g.drawRect(startPosX + 253, startPosY + 10, 2, 30);
            }
            case three: {
                g.setColor(Color.CYAN);
                g.fillRect(startPosX + 10, startPosY + 10, 20, 30);
                g.fillRect(startPosX + 80, startPosY + 10, 20, 30);
                g.setColor(Color.BLACK);
                g.drawRect(startPosX + 10, startPosY + 10, 20, 30);
                g.drawRect(startPosX + 18, startPosY + 10, 2, 30);
                g.drawRect(startPosX + 80, startPosY + 10, 20, 30);
                g.drawRect(startPosX + 88, startPosY + 10, 2, 30);
                g.setColor(Color.CYAN);
                g.fillRect(startPosX + 195, startPosY + 10, 20, 30);
                g.setColor(Color.BLACK);
                g.drawRect(startPosX + 195, startPosY + 10, 20, 30);
                g.drawRect(startPosX + 203, startPosY + 10, 2, 30);
                break;
            }
        }
    }
}