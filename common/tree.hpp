#pragma once

#include <optional>
#include <ostream>
#include <queue>
#include <vector>

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

// Lets tests mirror LeetCode's level-order notation: {3, 9, 20, null, null, 15, 7}
inline constexpr std::nullopt_t null = std::nullopt;

inline std::ostream& operator<<(std::ostream& os, const std::optional<int>& v) {
    if (v) return os << *v;
    return os << "null";
}

inline TreeNode* buildTree(const std::vector<std::optional<int>>& vals) {
    if (vals.empty() || !vals[0]) return nullptr;
    TreeNode* root = new TreeNode(*vals[0]);
    std::queue<TreeNode*> q;
    q.push(root);
    size_t i = 1;
    while (!q.empty() && i < vals.size()) {
        TreeNode* node = q.front();
        q.pop();
        if (i < vals.size() && vals[i]) {
            node->left = new TreeNode(*vals[i]);
            q.push(node->left);
        }
        i++;
        if (i < vals.size() && vals[i]) {
            node->right = new TreeNode(*vals[i]);
            q.push(node->right);
        }
        i++;
    }
    return root;
}

inline TreeNode* findNode(TreeNode* root, int val) {
    if (!root || root->val == val) return root;
    TreeNode* left = findNode(root->left, val);
    return left ? left : findNode(root->right, val);
}

// Level order with trailing nulls trimmed, matching LeetCode's output format.
inline std::vector<std::optional<int>> toVector(TreeNode* root) {
    std::vector<std::optional<int>> out;
    std::queue<TreeNode*> q;
    q.push(root);
    while (!q.empty()) {
        TreeNode* node = q.front();
        q.pop();
        if (node) {
            out.push_back(node->val);
            q.push(node->left);
            q.push(node->right);
        } else {
            out.push_back(std::nullopt);
        }
    }
    while (!out.empty() && !out.back()) out.pop_back();
    return out;
}
