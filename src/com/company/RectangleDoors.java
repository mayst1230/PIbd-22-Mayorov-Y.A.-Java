package com.company;

import java.awt.*;

public class RectangleDoors implements Adding{
    private DoorsCount count;

    public RectangleDoors(int number) {
        setNumber(number);
    }

    @Override
    public void setNumber(int number) {
        this.count = DoorsCount.getCount(number);
    }

    @Override
    public void draw(Graphics g, int startPosX, int startPosY) {
        g.setColor(Color.CYAN);
        g.fillRect(startPosX + 195, startPosY + 10, 20, 30);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 195, startPosY + 10, 20, 30);
        g.drawRect(startPosX + 203, startPosY + 10, 2, 30);
        if (count == DoorsCount.four || count == DoorsCount.five) {
            g.setColor(Color.CYAN);
            g.fillRect(startPosX + 245, startPosY + 10, 20, 30);
            g.setColor(Color.BLACK);
            g.drawRect(startPosX + 245, startPosY + 10, 20, 30);
            g.drawRect(startPosX + 253, startPosY + 10, 2, 30);
        }
        if (count == DoorsCount.five) {
            g.setColor(Color.CYAN);
            g.fillRect(startPosX + 300, startPosY + 10, 20, 30);
            g.setColor(Color.BLACK);
            g.drawRect(startPosX + 300, startPosY + 10, 20, 30);
            g.drawRect(startPosX + 308, startPosY + 10, 2, 30);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + count.ordinal();
    }
}
