import java.util.*;


// can be used for min and max range queries
class SparseTableImpl {

    private int n;
    private int m;
    private int[][] table;   // (m+1) rows, and n columns

    
    public SparseTableImpl(int[] arr) {
        this.n = arr.length;
        this.m = (int) (Math.log(n) / Math.log(2));  // log2(n)
        this.table = new int[m+1][n];

        buildTable(arr);
    }

    private void buildTable(int[] arr) {
        // for first row
        for(int ind=0; ind<n; ind++) {
            table[0][ind] = arr[ind];
        }

        for(int row=1; row <= m; row++) {
            // (ind + 2 ^ (row)) <= n
            for(int ind=0; (ind + (1 << row)) <= n; ind ++) {
                // take the minimum of (table[row-1][ind], table[row-1][ind + 2 ^ (row-1)])
                table[row][ind] = Math.min(table[row-1][ind], table[row-1][ind + (1 << (row - 1))]);
            }
        }
    }

    public int query(int l, int r) {
        int length = r - l + 1;
        int k = (int) (Math.log(length) / Math.log(2));
        return Math.min(table[k][l], table[k][r - (1 << k) + 1]);
    }


    
}



public class SparseTable {
    public static void main(String[] args) {
        int[] arr = new int[] {4, 2, -1, 4, 0, 2};
        int n = arr.length;

        SparseTableImpl st = new SparseTableImpl(arr);

        List<int[]> queries = Arrays.asList(
            new int[] {0, 1},
            new int[] {4, 5},
            new int[] {2, 4},
            new int[] {0, 5},
            new int[] {1, 4}
        );

        for(int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        for(int[] q : queries ) {
            int l = q[0], r = q[1];
            System.out.println(l + ", " + r + " -> " + st.query(l, r));
        }
    }
}
 