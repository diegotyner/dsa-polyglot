import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int solve(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean rotated = nums[mid] > nums[hi];
            if (rotated) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    record TestCase(int[] nums, int expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
                new TestCase(new int[] { 3, 4, 5, 1, 2 }, 1),
                new TestCase(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0),
                new TestCase(new int[] { 11, 13, 15, 17 }, 11));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            int actual = s.solve(t.nums());
            // boolean passed = actual == t.expected();
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            // System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tExpected: %s%n", t.expected());
            // System.out.printf("\tActual: %s%n", actual);
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
