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
    TreeNode* invertTree(TreeNode* root) {
      if (root == nullptr) {
          return nullptr;
      }
      auto tmp = root->left;
      root->left = root->right;
      root->right = tmp;
      invertTree(root->left);
      invertTree(root->right);
      return root;
    }
};

struct TestCase {
    std::vector<std::optional<int>> root;
    std::vector<std::optional<int>> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{4, 2, 7, 1, 3, 6, 9}, {4, 7, 2, 9, 6, 3, 1}},
        {{2, 1, 3}, {2, 3, 1}},
        {{}, {}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* root = buildTree(t.root);
        auto actual = toVector(sol.invertTree(root));
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
