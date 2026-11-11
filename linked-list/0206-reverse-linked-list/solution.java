import java.util.*;

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    record TestCase(List<Integer> input, List<Integer> expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
                new TestCase(List.of(1, 2, 3, 4, 5), List.of(5, 4, 3, 2, 1)),
                new TestCase(List.of(1, 2), List.of(2, 1)),
                new TestCase(List.of(), List.of()));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            ListNode head = ListNode.build(t.input());
            List<Integer> actual = ListNode.toList(s.reverseList(head));
            boolean passed = actual.equals(t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
