#pragma once

#include <vector>

struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x) : val(x), next(nullptr) {}
};

inline ListNode* buildList(const std::vector<int>& vals) {
    ListNode dummy(0);
    ListNode* tail = &dummy;
    for (int v : vals) {
        tail->next = new ListNode(v);
        tail = tail->next;
    }
    return dummy.next;
}

inline std::vector<int> toVector(ListNode* head) {
    std::vector<int> out;
    for (; head; head = head->next) out.push_back(head->val);
    return out;
}

// pos is the 0-indexed node the tail connects back to; -1 means no cycle.
inline ListNode* buildCycleList(const std::vector<int>& vals, int pos) {
    ListNode* head = buildList(vals);
    ListNode* cycleNode = nullptr;
    ListNode* tail = nullptr;
    int idx = 0;
    for (ListNode* node = head; node; node = node->next, idx++) {
        if (idx == pos) cycleNode = node;
        tail = node;
    }
    if (tail) tail->next = cycleNode;
    return head;
}
