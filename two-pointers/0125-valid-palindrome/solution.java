import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public boolean solve(String nums) {
      List<Character> string_builder = new ArrayList<>();
      char[] char_arr = nums.toCharArray();
      for (var ch : char_arr) {
        if (Character.isLetterOrDigit(ch)) {
          string_builder.add(Character.toLowerCase(ch));
        }
      }
      int lp=0, rp=string_builder.size()-1;
      while (lp < rp) {
        if (string_builder.get(lp) != string_builder.get(rp)) {
          return false;
        }
        lp++;rp--;
      }
      return true;
    }

    record TestCase(String nums, boolean expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase("A man, a plan, a canal: Panama", true),
            new TestCase("race a car", false),
            new TestCase(" ", true)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            Boolean actual = s.solve(t.nums());
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
