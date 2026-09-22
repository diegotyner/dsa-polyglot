import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public int solve(int[] nums) {
      HashSet<Integer> seen = new HashSet<>();
      int best = 0;
      for (var num : nums) {
        seen.add(num);
      }
      for (var num : seen) {
        if (seen.contains(num-1)){
          continue;
        }
        // else...
        int i=1;
        while(seen.contains(num+i)) {
          i++;
        }
        best = Integer.max(best, i);
      }
      return best;
    }

    record TestCase(int[] nums, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(new int[]{100,4,200,1,3,2}, 4),
            new TestCase(new int[]{0,3,7,2,5,8,4,6,0,1}, 9),
            new TestCase(new int[]{1,0,1,2}, 3)
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
