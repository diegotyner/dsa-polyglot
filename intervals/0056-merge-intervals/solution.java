import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();

        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            var cur = intervals[i];
            if (prev[1] < cur[0]) {
                merged.add(prev);
                prev = cur;
            } else {
                prev[1] = Math.max(prev[1], cur[1]);
            }
        }
        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);
    }

    record TestCase(int[][] intervals, int[][] expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
                new TestCase(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } },
                        new int[][] { { 1, 6 }, { 8, 10 }, { 15, 18 } }),
                new TestCase(new int[][] { { 1, 4 }, { 4, 5 } }, new int[][] { { 1, 5 } }),
                new TestCase(new int[][] { { 4, 7 }, { 1, 4 } }, new int[][] { { 1, 7 } }),
                new TestCase(new int[][] { { 1, 4 }, { 2, 3 } }, new int[][] { { 1, 4 } }),
                new TestCase(new int[][] { { 1, 4 } }, new int[][] { { 1, 4 } }));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int[][] actual = s.merge(t.intervals());
            boolean passed = Arrays.deepEquals(actual, t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", Arrays.deepToString(t.expected()));
            System.out.printf("\tActual:   %s%n", Arrays.deepToString(actual));
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
