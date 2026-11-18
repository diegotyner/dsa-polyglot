#include <iostream>
#include <vector>
#include "tree.hpp"

class Solution {
public:
    int kthSmallest(TreeNode* root, int k) {
      std::vector<TreeNode*> stack;
      TreeNode* cur = root;
      while (true) {
        while (cur != nullptr) {
            stack.push_back(cur);
            cur = cur->left;
        }
        cur = stack.back();
        stack.pop_back();
        k--;
        if (k==0) return cur->val;
        cur = cur->right;
      }
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
