


#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> arr = {1, 2, 3};
    int n = arr.size();

    int totalSubsets = 1 << n; // 2^n

    for (int mask = 0; mask < totalSubsets; mask++) {
        vector<int> subset;

        for (int bit = 0; bit < n; bit++) {
            if (mask & (1 << bit)) {  // check if bit is set
                subset.push_back(arr[bit]);
            }
        }

        // print the subset
        cout << "{ ";
        for (int x : subset) cout << x << " ";
        cout << "}\n";
    }

    return 0;
}
