import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int solve(int[] nums) {
      int lp=0, rp=nums.length-1;
      int best=0;
      while (lp < rp) {
        int left=nums[lp], right=nums[rp];
        int area = (rp-lp)*(Integer.min(left, right));
        best = Integer.max(best, area);
        if (left < right) {
          lp++;
        } else {
          rp--;
        }
      }
      return best; // TODO
    }

    record TestCase(int[] nums, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(new int[]{1,8,6,2,5,4,8,3,7}, 49),
            new TestCase(new int[]{1,1}, 1)
        );

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
            // System.out.printf("\tActual:   %s%n", actual);
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
