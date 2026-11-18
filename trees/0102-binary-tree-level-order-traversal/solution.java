import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
      // offer / poll
      Queue<TreeNode> q = new ArrayDeque<>();
      List<List<Integer>> traversal = new ArrayList<>();

      if (root == null) {
        return (new ArrayList<>());
      }

      q.offer(root);


      while (!q.isEmpty()) {
        int level_length = q.size();
        List<Integer> level = new ArrayList<>();

        for (int i=0; i<level_length; i++) {
          TreeNode cur = q.poll();
          if (cur.left != null) {
            q.offer(cur.left);
          }
          if (cur.right != null) {
            q.offer(cur.right);
          }
          level.add(cur.val);
        }

        traversal.add(level);
      }
      return traversal;
    }

    // Arrays.asList, not List.of: List.of throws on null elements
    record TestCase(List<Integer> root, List<List<Integer>> expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
            new TestCase(Arrays.asList(3, 9, 20, null, null, 15, 7),
                List.of(List.of(3), List.of(9, 20), List.of(15, 7))),
            new TestCase(Arrays.asList(1), List.of(List.of(1))),
            new TestCase(Arrays.asList(), List.of())
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            TreeNode root = TreeNode.build(t.root());
            List<List<Integer>> actual = s.levelOrder(root);
            boolean passed = actual.equals(t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
