
#include<bits/stdc++.h>
using namespace std;

#define ll long long

class FenwickTree {

    private:
        vector<ll> arr;
        vector<ll> bit;
        int n;
    
    public:
        FenwickTree(const vector<int>& input) {
            int m = input.size();
            n = m + 1;

            arr.assign(n, 0LL);
            bit.assign(n, 0LL);

            for(int i=0; i<m; i++) {
                arr[i+1] = input[i];
                update(i+1, arr[i+1]);
            }
        }

        void update(int ind, ll val ) {
            while(ind < n) {
                bit[ind] += val;
                ind += (ind & -ind);
            }
        }

        ll query(int ind) {
            ll ans = 0;
            while(ind > 0) {
                ans += bit[ind];
                ind -= (ind & -ind);
            }

            return ans;
        }

        void setVal(int ind, ll val) {
            ll newVal = val - arr[ind];
            arr[ind] = val;
            update(ind, newVal);
        }

};