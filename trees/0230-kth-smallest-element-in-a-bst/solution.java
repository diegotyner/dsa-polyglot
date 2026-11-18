import java.util.*;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
      List<TreeNode> stack = new ArrayList<>();
      TreeNode cur = root;

      while (true) {
        while (cur != null) {
          stack.add(cur);
          cur = cur.left;
        }
        cur = stack.removeLast();
        k--;
        if (k==0) return cur.val;
        cur = cur.right;
      }
    }

    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> root, int k, int expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(3, 1, 4, null, 2), 1, 1),
            new TestCase(Arrays.asList(5, 3, 6, 2, 4, null, null, 1), 3, 3)
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode root = TreeNode.build(t.root());
            int actual = s.kthSmallest(root, t.k());
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
