import java.util.*;

class Solution {
    public TreeNode invertTree(TreeNode root) {
      if (root == null) {
        return null;
      }
      var tmp = root.left;
      root.left = root.right;
      root.right = tmp;
      invertTree(root.left);
      invertTree(root.right);
      return root;
    }

    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> root, List<Integer> expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(4, 2, 7, 1, 3, 6, 9), Arrays.asList(4, 7, 2, 9, 6, 3, 1)),
            new TestCase(Arrays.asList(2, 1, 3), Arrays.asList(2, 3, 1)),
            new TestCase(Arrays.asList(), Arrays.asList())
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode root = TreeNode.build(t.root());
            List<Integer> actual = TreeNode.toList(s.invertTree(root));
            boolean passed = actual.equals(t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
