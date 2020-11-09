package com.company;

public enum DoorsCount {

    three,
    four,
    five;

    public static DoorsCount getCount(int count) {
        switch (count) {
            case 3 -> {
                return three;
            }
            case 4 -> {
                return four;
            }
            case 5 -> {
                return five;
            }
        }
        return null;
    }
}