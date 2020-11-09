package com.company;

import java.awt.*;

public class Parking<T extends Transport, G extends Adding> {

    private final Object[] places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 340;

    private final int placeSizeHeight = 80;

    public Parking(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        this.places = new Object[width * height];
        this.pictureWidth = picWidth;
        this.pictureHeight = picHeight;
    }

    public boolean add(T vehicle) {
        for (int i = 0; i < places.length; i++) {
            if (CheckFreePlace(i)) {
                vehicle.setPosition(5 + i / 5 * placeSizeWidth + 5, i % 5 *
                        placeSizeHeight + 15, pictureWidth, pictureHeight);
                places[i] = vehicle;
                return true;
            }
        }
        return false;
    }

    public T delete(int index) {
        if (index < 0 || index > places.length) {
            return null;
        }
        if (!CheckFreePlace(index)) {
            T vehicle = (T) places[index];
            places[index] = null;
            return vehicle;
        }
        return null;
    }

    private boolean CheckFreePlace(int indexPlace) {
        return places[indexPlace] == null;
    }

    public boolean equal(int count) {
        int countPlaces = 0;
        for (Object object : places) {
            if (object != null) {
                countPlaces++;
            }
        }
        return countPlaces == count;
    }

    public boolean inequal(int count) {
        return !equal(count);
    }

    public void draw(Graphics g) {
        drawMarking(g);
        for (Object place : places) {
            if (place != null) {
                T placeT = (T) place;
                placeT.draw(g);
            }
        }
    }

    private void drawMarking(Graphics g) {
        int change = 0;
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j) {
                g.drawLine(i * placeSizeWidth + change, j * placeSizeHeight, (int) (i *
                        placeSizeWidth + placeSizeWidth / 2) + change, j * placeSizeHeight);
            }
            g.drawLine(i * placeSizeWidth + change, 0, i * placeSizeWidth + change,
                    (pictureHeight / placeSizeHeight) * placeSizeHeight);
            change += 5;
        }
    }
}