
#include<bits/stdc++.h>
using namespace std;


int main() {
    int n, x; cin >> n >> x;

    vector<int> h(n);
    vector<int> s(n);
    for(int i=0; i<n; i++) {
        cin >> h[i];
    }

    for(int i=0; i<n; i++) {
        cin >> s[i];
    }


    vector<vector<int>> dp (n+1, vector<int> (x + 1, 0 ));

    for(int ind=n-1; ind >= 0; ind--) {
        for(int price=0; price <= x; price++) {
            int p1 = dp[ind+1][price];
            int p2 = 0;
            if(price - h[ind] >= 0)
                p2 = dp[ind+1][price - h[ind]] + s[ind];

            dp[ind][price] = max(p1, p2);
        }
    }

    cout << dp[0][x] << endl;

    return 0;
}