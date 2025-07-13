# Skip Coding Challenge


## Problem Statement (From Code Challenge.pdf)

Imagine an elevation map where non-negative integers in an array represent the height of a
series of bars, each with a width of 1. When it rains, water gets trapped between these bars.
You must write a function that takes such an array and calculates the total volume of water that
can be trapped.

### Example 1
- Input: `[3, 2, 4, 1, 2]`
- Output: `2`
- 
### Example 2
- Input: `[4, 1, 1, 0, 2, 3]`
- Output: `8`

## Solution
- The solution is written in a public method in `WaterTrapService`
- Provided Unit Test cases in `WaterTrapServiceTest` covering required scenarios plus additional ones, also including alot of edge cases

## How to Run the Application

### Prerequisites
- Java 21
- Maven

### Running Test Cases

To run all test cases
```shell
./mvnw test
```

## Complexity Analysis
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

------------

## Alternative Solution

This is an alternative solution that uses extra arrays and multiple loops for readability/maintainability. Overall, This approach is easier to debug and understand, though it uses O(n) space

```java
public Integer calculateTrappedWater(int[] barHeights) {
    if (barHeights == null || barHeights.length < 3) return 0;
    // Simple case: less than 3 walls means no water can be totalTrappedWater
    int[] maxLeft = new int[numWalls];
    int[] maxRight = new int[numWalls];
    int totalTrappedWater = 0;
    int numWalls = barHeights.length;


    maxLeft[0] = barHeights[0];
    for (int i = 1; i < numWalls; i++) {
        maxLeft[i] = Math.max(maxLeft[i-1], barHeights[i]);
    }
    
    maxRight[numWalls - 1] = barHeights[numWalls - 1];
    for (int i = numWalls - 2; i >= 0; i--) {
        maxRight[i] = Math.max(maxRight[i+1], barHeights[i]);
    }
    
    for (int i = 1; i < numWalls-1; i++) {
        int trappedWater = Math.min(maxLeft[i], maxRight[i]) - barHeights[i];
        if (trappedWater > 0){
            totalTrappedWater += trappedWater;
        }
    }
    return totalTrappedWater;
}
```

- This approach is more readable and maintainable than the main one I provided, with logic broken into clear, simpler steps.
- No need to deal with pointer manipulations.
- Time Complexity: O(n)
- Space Complexity: O(n)
