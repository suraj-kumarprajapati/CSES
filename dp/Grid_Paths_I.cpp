
#include<bits/stdc++.h>
using namespace std;

const int MOD = 1e9 + 7;

int main() {
    int n; cin >> n;
    vector<vector<char>> grid(n, vector<char>(n));

    // taking input
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            char ch; cin >> ch;
            grid[i][j] = ch;
        }
    }

    vector<vector<int>> dp(n, vector<int> (n, 0));
    dp[n-1][n-1] = (grid[n-1][n-1] != '*' ? 1 : 0);

    for(int i=n-1; i>=0; i--) {
        for(int j=n-1; j>=0; j--) {
            if(i == n-1 && j == n-1) continue;
            if(grid[i][j] == '*') continue;

            int w1 = 0, w2 = 0;
            if(i + 1 < n)
                w1 = dp[i+1][j];
            if(j + 1 < n)
                w2 = dp[i][j+1];

            dp[i][j] = (w1 + w2) % MOD;
        }
    }

    cout << dp[0][0] << endl;
    return 0;
}