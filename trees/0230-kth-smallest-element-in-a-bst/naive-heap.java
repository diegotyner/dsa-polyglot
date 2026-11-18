import java.util.*;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
      PriorityQueue<Integer> pq = new PriorityQueue<>();              // MIN-heap by default (opposite of C++!)
      Queue<TreeNode> q = new ArrayDeque<>();
      q.offer(root);

      while (!q.isEmpty()) {
        TreeNode cur = q.poll();

        pq.offer(-cur.val);
        if (pq.size() > k) {
          pq.poll();
        }

        if (cur.left != null) { q.offer(cur.left); }
        if (cur.right != null) { q.offer(cur.right); }
      }

      return -pq.poll();
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
