package com.skipthedishes;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Service for calculating the total volume of water that can be trapped between bars in an elevation map.
 * <p>
 * Example (As provided to me in CodeChallenge.pdf):
 * Input: [3, 2, 4, 1, 2]
 * Output: 2
 */
@ApplicationScoped
public class WaterTrapService {


    /**
     * Returns the amount of trapped water for a given elevation map.
     *
     * @param barHeights heights array of non-negative bar heights
     * @return total units of trapped water
     */
    public Integer calculateTrappedWater(int[] barHeights) {
        if (barHeights == null || barHeights.length < 3) {
            return 0; // No water can be trapped with fewer than 3 bars
        }

        // Initialize variables/pointers
        int leftPointer = 0;
        int rightPointer = barHeights.length - 1;

        int maxLeft = 0;
        int maxRight = 0;
        int totalWater = 0;

        // Calculate from both ends until you reach the middle
        while (leftPointer < rightPointer) {
            // Compare the heights at both ends (leftPointer and rightPointer)
            if (barHeights[leftPointer] < barHeights[rightPointer]) {
                // Update maxLeft or add trapped water at leftPointer
                if (barHeights[leftPointer] >= maxLeft) {
                    maxLeft = barHeights[leftPointer];
                } else {
                    totalWater += maxLeft - barHeights[leftPointer];
                }
                leftPointer++;
            } else {
                // Update maxRight or add trapped water at rightPointer
                if (barHeights[rightPointer] >= maxRight) {
                    maxRight = barHeights[rightPointer];
                } else {
                    totalWater += maxRight - barHeights[rightPointer];
                }
                rightPointer--;
            }
        }
        return totalWater;
    }
}
