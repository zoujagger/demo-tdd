package com.zouj.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;

/**
 * Unit test for simple App.
 */
@DisplayName("<= AppTest Specification =>")
class AppTest {
    /**
     * Parameters in Contructor
     */
    private AppTest(TestInfo testInfo) {
        System.out.println("Working on test " + testInfo.getDisplayName());
    }

    /**
     * Parameters in test case method
     */
    @Test
    void shouldAnswerWithTrue(TestInfo testInfo) {
        System.out.println("Working on test case " + testInfo.getDisplayName());
        assertTrue(true);

    }

    @Test
    void nullAssertionTest() {
        String str = null;
        assertNull(str);
        assertNull(str, "str should be null");
        assertNull(str, () -> "str should be null");
    }

    @Test
    void shouldCheckForEvenNumbers() {
        int number = new Random(10).nextInt();
        assertTrue(() -> number % 2 == 0, number + " is not an even number.");
        BiFunction<Integer, Integer, Boolean> divisible = (x, y) -> x % y == 0;
        Function<Integer, Boolean> multipleOf2 = (x) -> divisible.apply(x, 2);
        assertTrue(() -> multipleOf2.apply(number), () -> " 2 is not factor of " +
                number);
        // List<Integer> numbers = Arrays.asList(1, 1, 1, 1, 2);
        // assertTrue(() -> numbers.stream().distinct().anyMatch(DSLTest::isEven),
        // "Did not find an even number in the list");
    }

    /**
     * Grouping assertions
     */
    // @Test
    // public void shelfToStringShouldPrintBookCountAndTitles() throws Exception {
    //     BookShelf shelf = new BookShelf();
    //     List<String> books = shelf.books();
    //     shelf.add("The Phoenix Project");
    //     shelf.add("Java 8 in Action");
    //     String shelfStr = shelf.toString();
    //     assertAll(() -> assertTrue(shelfStr.contains("The PhoenixProject"), "1st book title missing"),
    //             () -> assertTrue(shelfStr.contains("Java 8 in Action"),
    //                     "2nd book title missing "),
    //             () -> assertTrue(shelfStr.contains("2 books found"),
    //                     "Book count missing"));
    // }

    static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
