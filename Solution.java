import java.io.*;
import java.util.*;
import java.lang.Math;

public class Solution {
    
    public static int get_ans(int N, List<Integer> A) {
        // Write your code here
        long result = 0;
        int MOD = 1000000007;
        
        if (N <= 1) return 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                result = (result + beauty(A.get(i), A.get(j))) % MOD;
            }
        }
        
        return (int) result;
    }
    
    private static int beauty(int x, int y) {
        if (x == y) return 0;
        if (x <= 0 || y <= 0) return 0;
        
        Set<Integer> divisorsX = getDivisorsOptimized(x);
        Set<Integer> divisorsY = getDivisorsOptimized(y);
        
        int count = 0;
        
        // Count divisors that divide exactly one of x or y
        for (int d : divisorsX) {
            if (!divisorsY.contains(d)) {
                count++;
            }
        }
        
        for (int d : divisorsY) {
            if (!divisorsX.contains(d)) {
                count++;
            }
        }
        
        return count;
    }
    
    private static Set<Integer> getDivisorsOptimized(int n) {
        Set<Integer> divisors = new HashSet<>();
        if (n <= 0) return divisors;
        
        // Handle 1 specially
        if (n == 1) {
            divisors.add(1);
            return divisors;
        }
        
        // Find all divisors efficiently
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }
        return divisors;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine().trim());
        List<Integer> A = new ArrayList<>(N);

        for(int j=0; j<N; j++) {
            A.add(Integer.parseInt(scan.nextLine().trim()));
        }
        int result = get_ans(N, A);
        System.out.println(result);
    }
}