import java.util.*;

class Solution {
    public int solve(TreeNode root) {
      return -1; // TODO
    }

    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> root, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(3, 9, 20, null, null, 15, 7), 3),
            new TestCase(Arrays.asList(1, null, 2), 2),
            new TestCase(Arrays.asList(), 0)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode root = TreeNode.build(t.root());
            int actual = s.solve(root);
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
