package com.company;

public class ParkingNotFoundException extends Exception {
    public ParkingNotFoundException(int index) {
        super("Не найден автобус по указанному месту");
    }
}
