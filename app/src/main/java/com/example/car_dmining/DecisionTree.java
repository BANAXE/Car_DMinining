package com.example.car_dmining;

public class DecisionTree {
    public static String classify(int weight, double horsePower, double displacement) {
        if (weight > 1812.5) {
            if (horsePower > 63.5) {
                return "Japanese";
            } else {
                return "European";
            }
        } else {
            if (displacement > 94.5) {
                return "American";
            } else {
                return "Japanese";
            }
        }
    }
}
