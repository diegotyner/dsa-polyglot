#include <iostream>
#include <vector>
#include "linked_list.hpp"

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::vector<T>& v) {
    os << "[";
    for (size_t i = 0; i < v.size(); i++) {
        os << v[i];
        if (i + 1 < v.size()) os << ", ";
    }
    os << "]";
    return os;
}

class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        int len = 0;
        ListNode* cur = head;
        while (cur != nullptr) {
            cur = cur->next;
            len+=1;
        }
        if (n==len) {
            return head->next;
        }

        cur = head;
        for (int i=0; i<len-n-1; i++) {
            cur = cur->next;
        }
        cur->next = (n!=1) ? cur->next->next : nullptr;
        return head; 
    }
};

struct TestCase {
    std::vector<int> input;
    int n;
    std::vector<int> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{1, 2, 3, 4, 5}, 2, {1, 2, 3, 5}},
        {{1}, 1, {}},
        {{1, 2}, 1, {1}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        ListNode* head = buildList(t.input);
        std::vector<int> actual = toVector(sol.removeNthFromEnd(head, t.n));
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
