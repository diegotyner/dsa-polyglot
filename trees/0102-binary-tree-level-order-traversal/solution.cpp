#include <iostream>
#include <vector>
#include <queue>
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
    std::vector<std::vector<int>> levelOrder(TreeNode* root) {
      if (root == nullptr) return {};

      std::deque<TreeNode*> dq;
      std::vector<std::vector<int>> traversal;
      dq.push_back(root);

      while (!dq.empty()) {
        int level_size = dq.size();
        std::vector<int> level;

        for (int i=0; i<level_size; i++) {
          TreeNode* cur = dq.front();
          dq.pop_front();
          if (cur->left != nullptr) {
            dq.push_back(cur->left);
          }
          if (cur->right != nullptr) {
            dq.push_back(cur->right);
          }
          level.push_back(cur->val);
        }
        traversal.push_back(level);
      }

      return traversal;
    }
};

struct TestCase {
    std::vector<std::optional<int>> root;
    std::vector<std::vector<int>> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{3, 9, 20, null, null, 15, 7}, {{3}, {9, 20}, {15, 7}}},
        {{1}, {{1}}},
        {{}, {}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* root = buildTree(t.root);
        auto actual = sol.levelOrder(root);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
