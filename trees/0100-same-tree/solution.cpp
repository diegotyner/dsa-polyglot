#include <iostream>
#include <vector>
#include "tree.hpp"

class Solution {
public:
    bool isSameTree(TreeNode* p, TreeNode* q) {
      bool l_exist = (p != nullptr);
      bool r_exist = (q != nullptr);

      if (!l_exist && !r_exist) {
        return true;
      } if (!l_exist || !r_exist) {
        return false;
      }
      if (p->val != q->val) {
        return false;
      }
      return (isSameTree(p->left, q->left) && isSameTree(p->right, q->right));
    }
};

struct TestCase {
    std::vector<std::optional<int>> p;
    std::vector<std::optional<int>> q;
    bool expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{1, 2, 3}, {1, 2, 3}, true},
        {{1, 2}, {1, null, 2}, false},
        {{1, 2, 1}, {1, 1, 2}, false}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        TreeNode* p = buildTree(t.p);
        TreeNode* q = buildTree(t.q);
        bool actual = sol.isSameTree(p, q);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << std::boolalpha << t.expected << "\n";
        std::cout << "\tActual:   " << std::boolalpha << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
