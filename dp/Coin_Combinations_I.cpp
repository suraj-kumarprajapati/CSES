



#include<bits/stdc++.h>
using namespace std;

const int MOD = 1e9+7;


int main() {

    int n, x; cin >> n >> x;
    vector<int> dp(x+1, 0);
    vector<int> coins(n);
    for(int i=0; i<n; i++) {
        cin >> coins[i];
    }

    dp[0] = 1;

    for(int i=1; i<=x; i++) {
        int curr = 0;
        for(int c : coins) {
            if(i - c >= 0)
            curr = (curr + dp[i-c]) % MOD ;
        }

        dp[i] = curr;
    }

    cout << dp[x] << endl;


    return 0;
}