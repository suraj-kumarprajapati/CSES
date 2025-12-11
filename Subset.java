

import java.util.*;
class Subset {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = arr.length;

        List<List<Integer>> result = new ArrayList<>();
        int totalSubsets = 1 << n;
        
        for(int mask=0; mask<totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            for(int bit=0; bit<n; bit++) {
                if((mask & (1 << bit)) != 0) {
                    subset.add(arr[bit]);
                }
            }
            result.add(subset);
        }
        
        for(List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}