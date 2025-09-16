



#include<bits/stdc++.h>
using namespace std;


const int INF = 1e9 + 7;

int main() {

    int n, x; cin >> n >> x;

    vector<int> dp(x+1, INF);
    vector<int> coins(n);
    for(int i=0; i<n; i++) {
        cin >> coins[i];
    }

    dp[0] = 0;

    for(int i=1; i<=x; i++) {
        int curr = INF;
        for(int c : coins) {
            if(i - c >= 0)
            curr = min(curr, dp[i-c] + 1);
        }

        dp[i] = curr;
    }

    cout << (dp[x] == INF ? -1 : dp[x]) << endl;


    return 0;
}