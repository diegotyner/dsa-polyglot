#include <iostream>
#include <vector>
#include "tree.hpp"

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
    int maxDepth(TreeNode* root) {
      if (root == nullptr) {
        return 0;
      }
      return 1+std::max(maxDepth(root->left), maxDepth(root->right));
    }
};

struct TestCase {
    std::vector<std::optional<int>> root;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{3, 9, 20, null, null, 15, 7}, 3},
        {{1, null, 2}, 2},
        {{}, 0}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* root = buildTree(t.root);
        auto actual = sol.maxDepth(root);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
