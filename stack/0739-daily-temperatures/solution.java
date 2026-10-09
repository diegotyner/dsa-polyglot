import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int[] solve(int[] nums) {
      int n = nums.length;
      int[] ret = new int[n];
      Deque<int[]> stack = new ArrayDeque<>();

      for (int i=0; i<n; i ++) {
        int num = nums[i];
        while (!stack.isEmpty()) {
          int[] last_tmp = stack.peek();
          if (num <= last_tmp[0]) {
            break;
          } // else
          stack.pop();
          int prev_idx = last_tmp[1];
          ret[prev_idx] = i-prev_idx;
        }
        stack.push(new int[]{num, i});
      }
      return ret;
    }

    record TestCase(int[] nums, int[] expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(new int[]{73,74,75,71,69,72,76,73}, new int[]{1,1,4,2,1,1,0,0}),
            new TestCase(new int[]{30,40,50,60}, new int[]{1,1,1,0}),
            new TestCase(new int[]{30,60,90}, new int[]{1,1,0})
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            int[] actual = s.solve(t.nums());
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
