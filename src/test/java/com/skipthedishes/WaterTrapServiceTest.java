package com.skipthedishes;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class WaterTrapServiceTest {

    @Inject
    WaterTrapService waterTrapService;



    /*
     * I normally only use parameterized tests when there are many input/output cases (less code)
     * I acknowledge that using separate isolated tests are more ideal for isolating tests / easier for debugging
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("Parameterized test for elevation maps")
    void testCalculateTrappedWaterParameterized(int[] input, int expected) {
        assertEquals(expected, waterTrapService.calculateTrappedWater(input));
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 4, 1, 2}, 2), //example 1 from pdf
                Arguments.of(new int[]{4, 1, 1, 0, 2, 3}, 8), //Required TC #1 from pdf
                Arguments.of(new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7, 6}, 23), //Required TC #2 from pdf
                Arguments.of(new int[]{4, 4, 2, 8, 2, 3, 4, 1, 1, 9}, 31),
                Arguments.of(new int[]{1, 0, 1, 2, 0, 2, 1, 3, 1, 1}, 4),
                Arguments.of(new int[]{1, 3, 8, 3, 1, 2}, 1),
                Arguments.of(new int[]{10, 0, 0, 0, 0, 10}, 40),
                Arguments.of(new int[]{4, 4, 4, 4}, 0),
                Arguments.of(new int[]{1, 2, 3, 4}, 0),
                Arguments.of(new int[]{3, 0, 2}, 2)
        );
    }

    /*
     * Edge Case Test Cases
     */
    @Test
    @DisplayName("All zeros")
    void testAllZeros() {
        assertEquals(0, waterTrapService.calculateTrappedWater(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    @DisplayName("Null")
    void testNullBarHeights() {
        assertEquals(0, waterTrapService.calculateTrappedWater(null));
    }

    @Test
    @DisplayName("Empty Array")
    void testEmptyArray() {
        assertEquals(0, waterTrapService.calculateTrappedWater(new int[]{}));
    }

    @Test
    @DisplayName("One Bar Array")
    void testOneBarArray() {
        assertEquals(0, waterTrapService.calculateTrappedWater(new int[]{5}));
    }

    @Test
    @DisplayName("Two Bar Array")
    void testTwoBarArray() {
        assertEquals(0, waterTrapService.calculateTrappedWater(new int[]{2,7}));
    }

    @Test
    @DisplayName("Descending order")
    void testDescending() {
        assertEquals(0, waterTrapService.calculateTrappedWater(new int[]{7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    @DisplayName("Ascending order")
    void testAscending() {
        assertEquals(0, waterTrapService.calculateTrappedWater(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }
}