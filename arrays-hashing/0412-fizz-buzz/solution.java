import java.util.*;

class Solution {
    // public int solve(List<Integer> nums) {
    public List<String> solve(int nums) {
      List<String> ret = new ArrayList<>();
      for (int i=1; i<nums+1; i++) {
        boolean div3 = (i%3==0);
        boolean div5 = (i%5==0);
        if (div3 && div5) {
          ret.add("FizzBuzz");
        } else if (div3) {
          ret.add("Fizz");
        } else if (div5) {
          ret.add("Buzz");
        } else {
          ret.add(Integer.toString(i));
        }

      }
      return ret;
    }

    record TestCase(int nums, List<String> expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(3, List.of("1", "2", "Fizz")),
            new TestCase(5, List.of("1","2","Fizz","4","Buzz")),
            new TestCase(15, List.of("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"))
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            List<String> actual = s.solve(t.nums());
            // boolean passed = actual == t.expected();
            boolean passed = actual.equals(t.expected());
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
