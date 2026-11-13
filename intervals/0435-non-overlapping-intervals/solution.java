import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
      return -1; // TODO
    }

    record TestCase(int[][] intervals, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}, 1),
            new TestCase(new int[][]{{1, 2}, {1, 2}, {1, 2}}, 2),
            new TestCase(new int[][]{{1, 2}, {2, 3}}, 0)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int actual = s.eraseOverlapIntervals(t.intervals());
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
