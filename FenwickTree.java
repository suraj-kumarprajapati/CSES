

// all the indices are 1 based
class FenwickTree {
    long[] arr;
    long[] bit;
    int n;

    public FenwickTree(int[] arr) {
        int m = arr.length;
        n = m + 1;
        
        this.arr = new long[n];
        this.bit = new long[n];
        for(int i=0; i<m; i++) {
            this.arr[i+1] = (long) arr[i];
            update(i+1, this.arr[i+1]);
        }

    }


    public void update(int ind, long val) {
        while(ind < n) {
            bit[ind] += val;
            ind += (ind & -ind);
        }
    }

    public long query(int ind) {
        long ans = 0;
        while(ind > 0) {
            ans += bit[ind];
            ind -= (ind & -ind);
        }

        return ans;
    }

    public void set(int ind, long val) {
        long newVal = val - arr[ind];
        this.arr[ind] = val;
        update(ind, newVal);
    }
}
