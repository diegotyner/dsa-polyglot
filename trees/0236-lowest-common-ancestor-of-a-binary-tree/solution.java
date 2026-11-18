import java.util.*;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      return null; // TODO
    }

    // p, q, expected are node values; node values are unique in this problem.
    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> root, int p, int q, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 5, 1, 3),
            new TestCase(Arrays.asList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 5, 4, 5),
            new TestCase(Arrays.asList(1, 2), 1, 2, 1)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode root = TreeNode.build(t.root());
            TreeNode result = s.lowestCommonAncestor(root, TreeNode.find(root, t.p()), TreeNode.find(root, t.q()));
            boolean passed = result != null && result.val == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", result == null ? "null" : result.val);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
