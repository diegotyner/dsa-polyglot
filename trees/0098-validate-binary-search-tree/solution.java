import java.util.*;

class Solution {
    public boolean valid(TreeNode cur, long lower_bound, long upper_bound) {
      if (cur == null) { return true; }
      if (cur.val <= lower_bound || cur.val >= upper_bound) { return false; }
      return (valid(cur.left, lower_bound, cur.val) && valid(cur.right, cur.val, upper_bound));
    }

    public boolean isValidBST(TreeNode root) {
      return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> root, boolean expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(2, 1, 3), true),
            new TestCase(Arrays.asList(5, 1, 4, null, null, 3, 6), false)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode root = TreeNode.build(t.root());
            boolean actual = s.isValidBST(root);
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
