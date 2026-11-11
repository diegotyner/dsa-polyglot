import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    static ListNode build(List<Integer> vals) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    static List<Integer> toList(ListNode head) {
        List<Integer> out = new ArrayList<>();
        for (; head != null; head = head.next) out.add(head.val);
        return out;
    }

    // pos is the 0-indexed node the tail connects back to; -1 means no cycle.
    static ListNode buildCycle(List<Integer> vals, int pos) {
        ListNode head = build(vals);
        ListNode cycleNode = null;
        ListNode tail = null;
        int idx = 0;
        for (ListNode node = head; node != null; node = node.next, idx++) {
            if (idx == pos) cycleNode = node;
            tail = node;
        }
        if (tail != null) tail.next = cycleNode;
        return head;
    }
}
