#include<stdio.h>
#include<bits/stdc++.h>
using namespace std;

#define ll long long


ll dp[20][2][11][2];

// fun(str_num, ind, tight, prev, leading_zero)
ll fun(string& s, int ind, bool tight, int prev, bool leading_zero) {
    int n = s.size();
    if(ind == n) return 1; // successfully built this number

    if(dp[ind][tight][prev][leading_zero] != -1)
        return dp[ind][tight][prev][leading_zero];

    int low_dig = 0;
    int upp_dig = (tight ? (s[ind] - '0') : 9);

    ll ans = 0;
    for(int dig = low_dig; dig <= upp_dig; dig++) {
        // invalid case
        if(dig == prev && !leading_zero) 
            continue;

        // valid case
        ans += fun(s, ind+1, (tight && dig == upp_dig), dig, (leading_zero && dig == 0));
    }

    return dp[ind][tight][prev][leading_zero] = ans;
}


ll solve(ll a, ll b) {
    string str_left = to_string(a-1);
    string str_right = to_string(b);

    memset(dp, -1, sizeof(dp));
    ll ans_right = fun(str_right, 0, 1, 10, 1);

    memset(dp, -1, sizeof(dp));
    ll ans_left = fun(str_left, 0, 1, 10, 1);
    
    return ans_right - ans_left;
}


int main() {

    ll a, b;
    cin >> a >> b;

    ll ans = solve(a, b);
    cout << ans;

    return 0;
}
