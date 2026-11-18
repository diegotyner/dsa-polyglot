import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // vals must allow nulls: use Arrays.asList(...), not List.of(...)
    static TreeNode build(List<Integer> vals) {
        if (vals.isEmpty() || vals.get(0) == null) return null;
        TreeNode root = new TreeNode(vals.get(0));
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.size()) {
            TreeNode node = q.poll();
            if (i < vals.size() && vals.get(i) != null) {
                node.left = new TreeNode(vals.get(i));
                q.offer(node.left);
            }
            i++;
            if (i < vals.size() && vals.get(i) != null) {
                node.right = new TreeNode(vals.get(i));
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    static TreeNode find(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        TreeNode left = find(root.left, val);
        return left != null ? left : find(root.right, val);
    }

    // Level order with trailing nulls trimmed, matching LeetCode's output format.
    static List<Integer> toList(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>(); // ArrayDeque rejects null elements
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                out.add(node.val);
                q.offer(node.left);
                q.offer(node.right);
            } else {
                out.add(null);
            }
        }
        while (!out.isEmpty() && out.get(out.size() - 1) == null) out.remove(out.size() - 1);
        return out;
    }
}
