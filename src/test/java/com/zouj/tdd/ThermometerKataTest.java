package com.zouj.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ThermometerKataTest {

    @Test
    void shouldReturnZeroWhenEmpty() throws TemperatureCapacityReachedExeception {
        // Given
        // int[] num = {2, 9, 1};
        // int[] temperatures = new int[5];
        // List<Integer> temperatures = Arrays.asList(2, 9, 1);
        List<Integer> temperatures = new ArrayList<>();
        KataNearZeroTemperature nearZero = new KataNearZeroTemperature();

        // When
        int result = nearZero.compute(temperatures);

        // Then
        assertEquals(0, result, () -> "should return 0 when empty");

    }

    @Test
    void shouldThrownExceptionWhenGreaterThan10000() {
        // Given
        // int[] num = {2, 9, 1};
        // int[] temperatures = new int[5];
        // List<Integer> temperatures = Arrays.asList(2, 9, 1);
        // List<Integer> temperatures = new ArrayList<>(10005);
        List<Integer> temperatures = new ArrayList<Integer>(Collections.nCopies(10005, 0));

        KataNearZeroTemperature nearZero = new KataNearZeroTemperature();

        try {
            // When
            int result = nearZero.compute(temperatures);
            fail("Should throw TemperatureCapacityReachedExeception on capacity.");
        } catch (TemperatureCapacityReachedExeception expected) {
            // Then
            assertEquals("La capacité max du tableau a été dépassé", expected.getMessage());
        }

    }

    @Test
    void shouldReturn1With291Array() throws TemperatureCapacityReachedExeception {
        // Given
        List<Integer> temperatures = Arrays.asList(2, 9, 1);
        KataNearZeroTemperature nearZero = new KataNearZeroTemperature();
         // When
         int result = nearZero.compute(temperatures);
         // Then
         assertEquals(1, result, () -> "Return minimum 1");
    }

    @Test
    void shouldReturn1With29minu1Array() throws TemperatureCapacityReachedExeception {
        // Given
        List<Integer> temperatures = Arrays.asList(2, 9, -1);
        KataNearZeroTemperature nearZero = new KataNearZeroTemperature();
         // When
         int result = nearZero.compute(temperatures);
         // Then
         assertEquals(-1, result, () -> "Return minimum -1");
    }

    @Test
    void shouldReturn1With29minus2minus3Array() throws TemperatureCapacityReachedExeception {
        // Given
        List<Integer> temperatures = Arrays.asList(2, 9, -2, -3);
        KataNearZeroTemperature nearZero = new KataNearZeroTemperature();
         // When
         int result = nearZero.compute(temperatures);
         // Then
         assertEquals(2, result, () -> "Return minimum 2");
    }

}
