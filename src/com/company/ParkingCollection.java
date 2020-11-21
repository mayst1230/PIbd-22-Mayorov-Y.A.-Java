package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParkingCollection {

    private final Map<String, Parking<Transport, Adding>> parkingStages;

    private final int pictureWidth;

    private final int pictureHeight;

    public ParkingCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        parkingStages = new HashMap<>();
    }

    public Set<String> keys() {
        return parkingStages.keySet();
    }

    public void addParking(String name) {
        if (parkingStages.containsKey(name)) {
            return;
        }
        parkingStages.put(name, new Parking<>(pictureWidth, pictureHeight));
    }

    public void deleteParking(String name) {
        parkingStages.remove(name);
    }

    public Parking<Transport, Adding> get(String name) {
        if (parkingStages.containsKey(name)) {
            return parkingStages.get(name);
        }
        return null;
    }

    public Transport get(String name, int index) {
        if (parkingStages.containsKey(name)) {
            return parkingStages.get(name).get(index);
        }
        return null;
    }
}
