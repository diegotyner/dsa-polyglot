import java.util.*;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
      boolean l_exist = (p != null);
      boolean r_exist = (q != null);

      if (!l_exist && !r_exist) {
        return true;
      } else if (!l_exist || !r_exist) {
        return false;
      }
      if (p.val != q.val) {
        return false;
      }
      return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> p, List<Integer> q, boolean expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3), true),
            new TestCase(Arrays.asList(1, 2), Arrays.asList(1, null, 2), false),
            new TestCase(Arrays.asList(1, 2, 1), Arrays.asList(1, 1, 2), false)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode p = TreeNode.build(t.p());
            TreeNode q = TreeNode.build(t.q());
            boolean actual = s.isSameTree(p, q);
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
