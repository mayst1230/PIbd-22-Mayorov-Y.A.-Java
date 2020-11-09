package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawParking extends JPanel {

    private final Parking<BusVehicle, Adding> busVehicleParking;

    public DrawParking(Parking<BusVehicle, Adding> busVehicleParking) {
        this.busVehicleParking = busVehicleParking;
    }

    protected void paintComponent(Graphics g) {
        if (busVehicleParking != null) {
            busVehicleParking.draw(g);
        }
    }

    public Parking<BusVehicle, Adding> getBusVehicleParking() {
        return busVehicleParking;
    }
}