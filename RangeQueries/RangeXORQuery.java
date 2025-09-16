import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;




class SegmentTreeXOR {
    int n;
    int[] arr;
    int t[];

    public SegmentTreeXOR(int n, int[] arr) {
        this.n = n;
        this.arr = arr;
        this.t = new int[4*n];
    }

    private void buildST(int v, int tl, int tr) {
        if(tl == tr) {
            t[v] = arr[tl];
            return;
        }

        int tm = tl + (tr - tl) / 2;
        buildST(2*v,tl, tm );
        buildST(2*v+1, tm+1, tr);
        t[v] = t[2*v] ^ t[2*v+1];
    }

    private int queryST(int v, int tl, int tr, int ql, int qr) {
        if(tl > qr || tr < ql)
            return 0;

        if(ql <= tl && tr <= qr) {
            return t[v];
        }

        int tm = tl + (tr - tl) / 2;
        int left = queryST(2*v, tl, tm, ql, qr);
        int right = queryST(2*v+1, tm+1, tr, ql, qr);
        return left ^ right;
    }

    public void build() {
        buildST(1, 0, n-1);
    }

    public int query(int a, int b) {
        return queryST(1, 0, n-1, a, b);
    }
}



public class RangeXORQuery {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTreeXOR seg = new SegmentTreeXOR(n, arr);
        seg.build();


        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1; 
            
            int res = seg.query(a, b);
            out.println(res);
        }

        out.flush();
        out.close();
    }
}
