

#include <bits/stdc++.h>
using namespace std;

class SparseTable {
public:
    int n, maxLog;
    vector<vector<int>> table;
    vector<int> logVal;

    SparseTable(const vector<int>& arr) {
        n = arr.size();
        maxLog = log2(n);

        table.assign(maxLog + 1, vector<int>(n));
        logVal.assign(n + 1, 0);

        // Precompute logs
        for (int i = 2; i <= n; i++)
            logVal[i] = logVal[i / 2] + 1;

        build(arr);
    }

    void build(const vector<int>& arr) {
        // First row
        for (int i = 0; i < n; i++)
            table[0][i] = arr[i];

        // Build rest
        for (int row = 1; row <= maxLog; row++) {
            for (int i = 0; i + (1 << row) <= n; i++) {
                table[row][i] = min(
                    table[row - 1][i],
                    table[row - 1][i + (1 << (row - 1))]
                );
            }
        }
    }

    // Range Minimum Query (RMQ)
    int query(int L, int R) {
        int len = R - L + 1;
        int k = logVal[len];
        return min(table[k][L], table[k][R - (1 << k) + 1]);
    }
};


int main() {
    vector<int> arr = {4, 2, -1, 4, 0, 2};
    SparseTable st(arr);

    vector<pair<int, int>> queries = {
        {0, 1},
        {4, 5},
        {2, 4},
        {0, 5},
        {1, 4}
    };

    cout << "Array: ";
    for (int x : arr) cout << x << " ";
    cout << "\n\nQueries:\n";

    for (auto &q : queries) {
        cout << q.first << ", " << q.second 
             << " -> " << st.query(q.first, q.second) << "\n";
    }

    return 0;
}
