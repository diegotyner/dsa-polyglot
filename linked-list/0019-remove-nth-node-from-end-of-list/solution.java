import java.util.*;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // just going to do the easy way with 2 iterations
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len += 1;
        }

        if (len == n) {
            return head.next;
        }
        cur = head;
        for (int i = 0; i < len - n - 1; i++) {
            cur = cur.next;
        }
        cur.next = n != 1 ? cur.next.next : null;
        return head;
    }

    record TestCase(List<Integer> input, int n, List<Integer> expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
                new TestCase(List.of(1, 2, 3, 4, 5), 2, List.of(1, 2, 3, 5)),
                new TestCase(List.of(1), 1, List.of()),
                new TestCase(List.of(1, 2), 1, List.of(1)));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            ListNode head = ListNode.build(t.input());
            List<Integer> actual = ListNode.toList(s.removeNthFromEnd(head, t.n()));
            boolean passed = actual.equals(t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
