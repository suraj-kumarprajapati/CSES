
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Distinct {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        out.println(set.size());
        out.flush();
    }
}