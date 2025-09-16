import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Apartments {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);



        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] desiredApartments = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            desiredApartments[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(desiredApartments);  // sort the desired aparment sizes

        st =new StringTokenizer(br.readLine());
        int[] availableSizes = new int[m];
        for(int i=0; i<m; i++) {
            availableSizes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(availableSizes);  // sort the available sizes



        int i = 0;
        int j = 0;
        int count = 0;
        while(i < n && j < m) {
            int min = Math.max(desiredApartments[i] - k, 0);
            int max = desiredApartments[i] + k;

            if(availableSizes[j] >= min && availableSizes[j] <= max) {
                count += 1;
                i += 1;
                j += 1;
            }
            else if(availableSizes[j] < min) {
                j += 1;
            }
            else {
                i += 1;
            }
        }

        out.println(count);
        out.flush();       
        out.close();


    }
}
