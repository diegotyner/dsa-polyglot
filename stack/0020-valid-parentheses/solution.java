import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public boolean solve(String s) {
      Deque<Character> stack = new ArrayDeque<>();
      char[] char_ls = s.toCharArray();
      String openers = "([{";

      for (Character ch : char_ls) {
        if (openers.indexOf(ch) != -1) {
          stack.push(ch);
        } else { // its a closer
          if (stack.isEmpty()) {
            return false;
          }
          Character popped = stack.pop();
          switch (popped) {
            case '{':
              if (ch != '}') return false;
              break;
            case '[':
              if (ch != ']') return false;
              break;
            case '(':
              if (ch != ')') return false;
              break;
          }
        }
      }

      return stack.isEmpty();
    }

    record TestCase(String s, boolean expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase("()", true),
            new TestCase("()[]{}", true),
            new TestCase("(]", false),
            new TestCase("([])", true)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            boolean actual = s.solve(t.s());
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
