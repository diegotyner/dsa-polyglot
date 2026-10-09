#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

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
    bool solve(std::string s) {
      std::vector<char> stack;
      std::string openers = "{[(";
      for (char ch : s) {
        if (openers.find(ch) != std::string::npos) {
          stack.push_back(ch);
        } else {
          if (stack.empty()) {
            return false;
          }
          char popped = stack.back();
          stack.pop_back();

          switch (popped) {
            case '[':
              if (ch != ']') return false;
              break;
            case '{':
              if (ch != '}') return false;
              break;
            case '(':
              if (ch != ')') return false;
              break;
          }
        }
      }

      return stack.empty();
    }
};

struct TestCase {
    std::string nums;
    bool expected;
};

int main() {
    std::vector<TestCase> tests = {
      {"()", true},
      {"()[]{}", true},
      {"(]", false},
      {"([])", true}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.solve(t.nums);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
