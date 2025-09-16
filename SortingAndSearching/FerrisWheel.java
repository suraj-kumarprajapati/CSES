import java.io.*;
import java.util.*;

public class FerrisWheel {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = fr.nextInt();
        int x = fr.nextInt();

        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = fr.nextInt();
        }

        Arrays.sort(weights);

        int i = 0, j = n - 1, gondolas = 0;

        while (i <= j) {
            if (weights[i] + weights[j] <= x) {
                i++;
            }
            j--;
            gondolas++;
        }

        out.println(gondolas);
        out.flush();
        out.close();
    }

    // FAST INPUT CLASS
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in), 32768);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
