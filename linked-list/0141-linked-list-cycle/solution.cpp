#include <iostream>
#include <vector>
#include "linked_list.hpp"

class Solution {
public:
    bool hasCycle(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head;

        while (fast != nullptr && fast->next != nullptr) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast) {
                return true;
            }
        }
        return false; // TODO
    }
};

struct TestCase {
    std::vector<int> vals;
    int pos;
    bool expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{3,2,0,-4}, 1, true},
        {{1, 2}, 0, true},
        {{1}, -1, false}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        ListNode* head = buildCycleList(t.vals, t.pos);
        bool actual = sol.hasCycle(head);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << std::boolalpha << t.expected << "\n";
        std::cout << "\tActual:   " << std::boolalpha << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
