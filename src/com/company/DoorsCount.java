package com.company;

public enum DoorsCount {
    three,
    four,
    five;

    public static DoorsCount getCount(int count) {
        switch (count) {
            case 0:
                return three;
            case 1:
                return four;
            case 2:
                return five;
        }
        return null;
    }
}
