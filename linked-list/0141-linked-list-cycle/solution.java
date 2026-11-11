import java.util.*;

class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    record TestCase(List<Integer> vals, int pos, boolean expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
                new TestCase(List.of(3, 2, 0, -4), 1, true),
                new TestCase(List.of(1, 2), 0, true),
                new TestCase(List.of(1), -1, false));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            ListNode head = ListNode.buildCycle(t.vals(), t.pos());
            boolean actual = s.hasCycle(head);
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
