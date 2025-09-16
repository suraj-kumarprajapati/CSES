import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class STSum {
    private int n;
    long[] a;
    long[] unPropUpd; // unpropagated update
    boolean[] isLazy; // to track lazy nodes
    long[] t; // 1 based indexing
    String rangeUpdateType;

    public STSum(int n, long[] a, String rangeUpdateType) {
        this.n = n;
        this.a = a;
        this.t = new long[4*n];
        this.unPropUpd = new long[4*n];
        this.isLazy = new boolean[4*n];
        this.rangeUpdateType = rangeUpdateType;
        this.buildSum(1, 0, n-1);
    }

    // build the segment tree -> build(1, 0, n-1)
    // v -> current node, tl & tr -> current node range, a -> original array 
    public void buildSum(int v, int tl, int tr) {
        // if leaf node is reached
        if(tl == tr) {
            t[v] = a[tl];
            return;
        }

        int tm = (tl + tr) / 2;
        buildSum(2*v, tl, tm);
        buildSum(2*v+1, tm+1, tr);
        t[v] = t[2*v] + t[2*v + 1];
    }

    // sum query input question -> [ql, qr] : querySum(1, 0, n-1, ql, qr)
    private long querySum(int v, int tl, int tr, int ql, int qr) {
        // if not overlapping
        if(tl > qr || tr < ql) 
            return 0;
        
        // if completely overlapping
        if( tl >= ql && tr <= qr) 
            return t[v];

        // partially overlapping

        // first pushDown the unpropagated values to it's childrens
        pushDown(v, tl, tr);

        int tm = (tl + tr) / 2;
        long leftVal = querySum(2*v, tl, tm, ql, qr);
        long rightVal = querySum(2*v+1, tm+1, tr, ql, qr);
        return leftVal + rightVal;
    }

    // point update -> pointUpdate(1, 0, n-1, id, value)
    private void pointUpdateSum(int v, int tl, int tr, int id, int value) {
        // if reached leaf node at index 'id'
        if(tl == id && tr == id) {
            t[v] = value;
            return;
        }

        // if outside the current node's range or not overlapping
        if(id > tr || id < tl)
            return;
        
        pushDown(v, tl, tr);
        int tm = (tl + tr) / 2;
        pointUpdateSum(2*v, tl, tm, id, value);
        pointUpdateSum(2*v+1, tm+1, tr, id, value);
        t[v] = t[2*v] + t[2*v+1];
    }

    // range update
    private void rangeUpdateSum(int v, int tl, int tr, int l, int r, int newValue) {
        // if not overlapping
        if(tr < l || tl > r) {
            return;
        }

        // if completely overlapping
        if(l <= tl && tr <= r) {
            apply(v, tl, tr, newValue);
            return;
        }

        // partially overlapping

        // first pushDown the unpropagated values to it's childrens
        pushDown(v, tl, tr);

        int tm = (tl + tr) / 2;
        rangeUpdateSum(2*v, tl, tm, l, r, newValue);
        rangeUpdateSum(2*v+1, tm+1, tr, l, r, newValue);
        t[v] = t[2*v] + t[2*v+1];
    }

    // push down the unpropagated values to it's childrens
    // pushDown(v, tl, tr)
    private void pushDown(int v, int tl, int tr) {
        // if the current node is not lazy or does not contain any unpropagated value
        if(!isLazy[v]) {
            return;
        }

        // if lazy, invert it to not lazy and propagate the changes to it's childrens
        isLazy[v] = false;
        int tm = (tl + tr) / 2;
        apply(2*v, tl, tm, unPropUpd[v]);
        apply(2*v+1, tm+1, tr, unPropUpd[v]);
        // make the current unpropagated value to 0 after propagating to it's childrens
        unPropUpd[v] = 0;
    }

    // apply the changes to the node (update or upgrade)
    private void apply(int v, int tl, int tr, long newValue) {
        if(rangeUpdateType.equals("update")) {
            update(v, tl, tr, newValue);
        }
        else if(rangeUpdateType.equals("upgrade")) {
            upgrade(v, tl, tr, newValue);
        }
    }

    // in case it's an update operation
    private void update(int v, int tl, int tr, long newValue) {
        // check if it's leaf node or not
        if(tl != tr) {
            isLazy[v] = true;
            unPropUpd[v] += newValue;
        }

        t[v] += (tr - tl + 1) * newValue;
    }

    // in case it's an upgrade/replace operation
    private void upgrade(int v, int tl, int tr, long newValue) {
        // check if it's leaf node or not
        if(tl != tr) {
            isLazy[v] = true;
            unPropUpd[v] = newValue;
        }

        t[v] = (tr - tl + 1) * newValue;
    }



    // end points function
    public long query(int ql, int qr) {
        return querySum(1, 0, n-1, ql, qr);
    }

    public void pointUpdate(int id, int value) {
        pointUpdateSum(1, 0, n-1, id, value);
    }

    public void rangeUpdate(int l, int r, int value) {
        rangeUpdateSum(1, 0, n-1, l, r, value);
    }
}



public class RangeUpdateQuery {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        STSum seg = new STSum(n, arr, "update");


        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
           
            if(type == 1) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int u = Integer.parseInt(st.nextToken());

                seg.rangeUpdate(a, b, u);
            }
            else {
                int k = Integer.parseInt(st.nextToken()) - 1;
                long res = seg.query(k, k);
                out.println(res);
            }
        }

        out.flush();
        out.close();
    }
}
