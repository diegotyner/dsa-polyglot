import java.util.*;

class Solution {
    private String getHash(String str) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      return new String(chars);
    }

    // public int solve(List<Integer> nums) {
    public List<List<String>> solve(String[] strs) {
      HashMap<String, Integer> hash_to_idx = new HashMap<>();
      List<List<String>> output = new ArrayList<>();
      for (var str : strs) {
        String hash = getHash(str);
        if (hash_to_idx.containsKey(hash)) {
          int idx = hash_to_idx.get(hash);
          output.get(idx).add(str);
        } else {
          hash_to_idx.put(hash, output.size());
          output.add(new ArrayList<String>(List.of(str)));
        }
      }

      return output; // TODO
    }

    record TestCase(String[] strs, List<List<String>> expected) {}

    public static void main(String[] args) {
        Solution s = new Solution();

        // List<TestCase> tests = List.of(
        List<TestCase> tests = List.of(
            new TestCase(new String[]{"eat","tea","tan","ate","nat","bat"}, List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea"))),
            new TestCase(new String[]{""}, List.of(List.of(""))),
            new TestCase(new String[]{"a"}, List.of(List.of("a")))
        );

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            // int actual = s.solve(t.nums());
            List<List<String>> actual = s.solve(t.strs());
            // boolean passed = actual == t.expected();
            boolean passed = actual == t.expected();
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            // System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tExpected: %s%n", t.expected());
            // System.out.printf("\tActual:   %s%n", actual);
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
