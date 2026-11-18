#include <climits>
#include <iostream>
#include <vector>
#include "tree.hpp"

class Solution {
public:
    bool helper(TreeNode* cur, long long lower_bound, long long upper_bound) {
      if (cur == nullptr) { return true; }
      if (cur->val <= lower_bound) { return false; }
      if (cur->val >= upper_bound) { return false; }
      return helper(cur->left, lower_bound, cur->val) && helper(cur->right, cur->val, upper_bound);
    }

    bool isValidBST(TreeNode* root) {
      return helper(root, LONG_LONG_MIN, LONG_LONG_MAX);
    }
};

struct TestCase {
    std::vector<std::optional<int>> root;
    bool expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{2, 1, 3}, true},
        {{5, 1, 4, null, null, 3, 6}, false}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* root = buildTree(t.root);
        bool actual = sol.isValidBST(root);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << std::boolalpha << t.expected << "\n";
        std::cout << "\tActual:   " << std::boolalpha << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
