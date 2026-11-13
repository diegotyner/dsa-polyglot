import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int solve(int[] nums) {
        int longest = 1, cur = 1;
        boolean increasing = true;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                cur = 1;
                continue;
            }

            if (nums[i] > nums[i - 1] == increasing) {
                cur++;
            } else {
                cur = 2;
                increasing = !increasing;
            }

            longest = Math.max(longest, cur);
        }

        return longest;
    }

    record TestCase(int[] nums, int expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
                new TestCase(new int[] { 1, 4, 3, 3, 2 }, 2),
                new TestCase(new int[] { 3, 3, 3, 3 }, 1),
                new TestCase(new int[] { 3, 2, 1 }, 3));

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
