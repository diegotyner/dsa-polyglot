import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> ret = new ArrayList<>();
        int i = 0;
        // strictly before
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            ret.add(intervals[i]);
            i++;
        }

        // in the middle
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;
        }

        ret.add(new int[] { start, end });

        // after
        while (i < intervals.length) {
            ret.add(intervals[i]);
            i++;
        }

        return ret.toArray(new int[ret.size()][]);
    }

    record TestCase(int[][] intervals, int[] newInterval, int[][] expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
                new TestCase(new int[][] { { 1, 3 }, { 6, 9 } }, new int[] { 2, 5 },
                        new int[][] { { 1, 5 }, { 6, 9 } }),
                new TestCase(new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } }, new int[] { 4, 8 },
                        new int[][] { { 1, 2 }, { 3, 10 }, { 12, 16 } }));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int[][] actual = s.insert(t.intervals(), t.newInterval());
            boolean passed = Arrays.deepEquals(actual, t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", Arrays.deepToString(t.expected()));
            System.out.printf("\tActual:   %s%n", Arrays.deepToString(actual));
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
