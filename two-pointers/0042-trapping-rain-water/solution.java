import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int solve(int[] height) {
      int tot = 0;
      int maxLeft=-1, maxRight=-1;
      int lp=0,rp=height.length-1;

      while (lp < rp) {
        int left=height[lp], right=height[rp];
        int divot = Math.min(left, right);
        if (left < right) {
          maxLeft = Math.max(maxLeft, left);
          tot += Math.max(0, maxLeft - divot);
          lp += 1;
        } else { // left geq
          maxRight = Math.max(maxRight, right);
          tot += Math.max(0, maxRight - divot);
          rp -= 1;
        }
      }

      return tot;
    }

    record TestCase(int[] height, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6),
            new TestCase(new int[]{4,2,0,3,2,5}, 9)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            int actual = s.solve(t.height());
            // boolean passed = actual == t.expected();
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            // System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tExpected: %s%n", t.expected());
            // System.out.printf("\tActual:   %s%n", actual);
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
