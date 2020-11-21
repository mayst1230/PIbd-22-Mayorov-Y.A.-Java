package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawParking extends JPanel {

    private final ParkingCollection parkingCollection;
    private String selectedItem = null;

    public DrawParking(ParkingCollection parkingCollection) {
        this.parkingCollection = parkingCollection;
    }

    protected void paintComponent(Graphics g) {
        if (selectedItem != null) {
            if (parkingCollection != null) {
                parkingCollection.get(selectedItem).draw(g);
            }
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}