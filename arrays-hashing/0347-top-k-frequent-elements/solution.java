import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int[] solve(int[] nums, int k) {
      // pq is min-heap by default
      PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
      HashMap<Integer, Integer> counter = new HashMap<>();
      for (var num : nums) {
        counter.put(num, counter.getOrDefault(num, 0)+1);
      }
      for (var entry : counter.entrySet()) {
        int num = entry.getKey();
        int count = entry.getValue();
        pq.offer(new int[]{-count, num});
      }
      int[] output = new int[k];
      for (int i=0; i<k; i++) {
        output[i] = pq.poll()[1];
      }
      return output; // TODO
    }

    record TestCase(int[] nums, int k, int[] expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(new int[]{1,1,1,2,2,3}, 2, new int[]{1,2}),
            new TestCase(new int[]{1}, 1, new int[]{1}),
            new TestCase(new int[]{1,2,1,2,1,2,3,1,3,2}, 2, new int[]{1,2})
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            int[] actual = s.solve(t.nums(), t.k());
            // boolean passed = actual == t.expected();
            boolean passed = Arrays.equals(actual, t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            // System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tExpected: %s%n", Arrays.toString(t.expected()));
            // System.out.printf("\tActual:   %s%n", actual);
            System.out.printf("\tActual:   %s%n", Arrays.toString(actual));
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
