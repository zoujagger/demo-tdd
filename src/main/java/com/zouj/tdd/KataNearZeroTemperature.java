package com.zouj.tdd;

import java.util.List;
import java.util.Optional;

public class KataNearZeroTemperature {

    public int compute(List<Integer> temperatures) throws TemperatureCapacityReachedExeception {

        if (temperatures.size() > 10000) {
            throw new TemperatureCapacityReachedExeception("La capacité max du tableau a été dépassé");
        }

        if (temperatures.isEmpty())
            return 0;

       return nearZeroWithoutStream(temperatures);
    }

    private int nearZeroWithoutStream(List<Integer> temperatures) {
        int temperatureClosestToZero = temperatures.get(0);

        for (int temperature : temperatures) {
            int distance = Math.abs(temperature);

            if (distance < Math.abs(temperatureClosestToZero))
                temperatureClosestToZero = temperature;
            else if (temperature > 0 && distance <= Math.abs(temperatureClosestToZero))
                temperatureClosestToZero = temperature;
        }

        return temperatureClosestToZero;
    }

    private List<Integer> distanceTozero(List<Integer> temperatures) {
        return temperatures.stream().map(b -> Math.abs(b)).toList();
    }

    private int nearZeroMin(List<Integer> temperatures) {
        Optional<Integer> minimum = temperatures.stream().min((a, b) -> a - b);
        return minimum.get();
    }

}
