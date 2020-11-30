package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Parking<T extends Transport, G extends Adding> {

    private final List<T> places;

    private final int pictureWidth;

    private final int maxCount;

    private final int pictureHeight;

    private final int placeSizeWidth = 340;

    private final int placeSizeHeight = 80;

    public Parking(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        maxCount = width * height;
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        places = new ArrayList<>();
    }

    public boolean add(T vehicle) {
        if (places.size() < maxCount) {
            places.add(vehicle);
            return true;
        }
        return false;
    }

    public T delete(int index) {
        if (index >= 0 && index < maxCount && places.get(index) != null) {
            T bus = places.get(index);
            places.remove(index);
            return bus;
        }
        return null;
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
        for (int i = 0; i < places.size(); i++) {
            places.get(i).setPosition(5 + i / 5 * placeSizeWidth + 10, i % 5 *
                    placeSizeHeight + 15, pictureWidth, pictureHeight);
            places.get(i).draw(g);
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
            change += 10;
        }
    }

    public T get(int index) {
        if (index > -1 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }

    public void clear() {
        places.clear();
    }
}