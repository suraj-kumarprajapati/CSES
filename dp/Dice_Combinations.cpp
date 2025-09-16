

#include<bits/stdc++.h>
using namespace std;

const int MOD = 1e9+7;


int main() {

    int n; cin >> n;

    vector<int> dp(n+1, 0);
    dp[0] = 1;

    for(int i=1; i<=n; i++) {
        int curr = 0;
        for(int j=1; j<=6 && i-j >= 0; j++) {
            curr = (curr + dp[i-j]) % MOD ;
        }

        dp[i] = curr;
    }

    cout << dp[n] << endl;


    return 0;
}