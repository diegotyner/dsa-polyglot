#include <iostream>
#include <vector>
#include "tree.hpp"

class Solution {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
      return nullptr; // TODO
    }
};

// p, q, expected are node values; node values are unique in this problem.
struct TestCase {
    std::vector<std::optional<int>> root;
    int p;
    int q;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 1, 3},
        {{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}, 5, 4, 5},
        {{1, 2}, 1, 2, 1}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* root = buildTree(t.root);
        TreeNode* result = sol.lowestCommonAncestor(root, findNode(root, t.p), findNode(root, t.q));
        bool passed = result && result->val == t.expected;
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   ";
        if (result) std::cout << result->val << "\n";
        else std::cout << "null\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
