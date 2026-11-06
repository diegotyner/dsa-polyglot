import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public boolean bst(int target, int[] arr2, int d) {
        int lo = 0, hi = arr2.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int el = arr2[mid];
            if (Math.abs(target - el) <= d) {
                return false;
            }
            if (el > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return true;
    }

    public int solve(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int valid = 0;
        for (int num : arr1) {
            if (bst(num, arr2, d)) {
                valid++;
            }
        }
        return valid; // TODO
    }

    record TestCase(int[] nums, int[] arr2, int d, int expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
                new TestCase(new int[] { 4, 5, 8 }, new int[] { 10, 9, 1, 8 }, 2, 2),
                new TestCase(new int[] { 1, 4, 2, 3 }, new int[] { -4, -3, 6, 10, 20, 30 }, 3, 2),
                new TestCase(new int[] { 2, 1, 100, 3 }, new int[] { -5, -2, 10, -3, 7 }, 6, 1));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            int actual = s.solve(t.nums(), t.arr2, t.d);
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
