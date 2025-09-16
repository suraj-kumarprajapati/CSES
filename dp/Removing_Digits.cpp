

#include<bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int main() {

    int n;
    cin >> n;

    vector<int> dp(n+1, INF);
    dp[0] = 0;

    for(int num=1; num<=n; num++) {
        int curr = INF;
        int temp = num;

        while(temp > 0) {
            int dig = temp % 10;
            if(dig > 0 && num - dig >=0) {
                curr = min(curr, 1 + dp[num - dig]);
            }
            temp = temp / 10;
        }

        dp[num] = curr;
    }

    cout << dp[n] << endl;


    return 0;
}