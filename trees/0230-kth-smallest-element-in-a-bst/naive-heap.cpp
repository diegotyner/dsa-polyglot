#include <deque>
#include <iostream>
#include <vector>
#include <queue>
#include "tree.hpp"

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
      std::priority_queue<int> pq;
      std::deque<TreeNode*> frontier;

      frontier.push_back(root);

      while (!frontier.empty()) {
        TreeNode* cur = frontier.front();
        frontier.pop_front();

        pq.push(cur->val);
        if (pq.size() > k) {
          pq.pop();
        }

        if (cur->left != nullptr) { frontier.push_back(cur->left); }
        if (cur->right != nullptr) { frontier.push_back(cur->right); }
      }

      return pq.top();
    }
};

struct TestCase {
    std::vector<std::optional<int>> root;
    int k;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{3, 1, 4, null, 2}, 1, 1},
        {{5, 3, 6, 2, 4, null, null, 1}, 3, 3}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* root = buildTree(t.root);
        int actual = sol.kthSmallest(root, t.k);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
