package com.company;

public class ParkingOverflowException extends Exception{
    public ParkingOverflowException() {
        super("На парковке нет свободных мест");
    }
}
