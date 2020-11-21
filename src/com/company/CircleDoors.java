package com.company;

import java.awt.*;

public class CircleDoors implements Adding {

    private DoorsCount count;

    public CircleDoors(int number) {
        setNumber(number);
    }

    @Override
    public void setNumber(int number) {
        this.count = DoorsCount.getCount(number);
    }

    @Override
    public void draw(Graphics g, int startPosX, int startPosY) {
        g.setColor(Color.CYAN);
        g.fillOval(startPosX + 195, startPosY + 20, 20, 20);
        g.setColor(Color.BLACK);
        g.drawOval(startPosX + 195, startPosY + 20, 20, 20);
        g.drawRect(startPosX + 203, startPosY + 20, 2, 20);
        if (count == DoorsCount.four || count == DoorsCount.five) {
            g.setColor(Color.CYAN);
            g.fillOval(startPosX + 245, startPosY + 20, 20, 20);
            g.setColor(Color.BLACK);
            g.drawOval(startPosX + 245, startPosY + 20, 20, 20);
            g.drawRect(startPosX + 253, startPosY + 20, 2, 20);
        }
        if (count == DoorsCount.five) {
            g.setColor(Color.CYAN);
            g.fillOval(startPosX + 300, startPosY + 20, 20, 20);
            g.setColor(Color.BLACK);
            g.drawOval(startPosX + 300, startPosY + 20, 20, 20);
            g.drawRect(startPosX + 308, startPosY + 20, 2, 20);
        }
    }
}
