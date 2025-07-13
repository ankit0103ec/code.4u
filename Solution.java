import java.io.*;
import java.util.*;
import java.lang.Math;

public class Solution {
    
    public static int get_ans(int N, List<Integer> A) {
        // Write your code here
        long result = 0;
        int MOD = 1000000007;
        
        // Precompute divisors for all numbers in the array
        Map<Integer, Set<Integer>> divisorsMap = new HashMap<>();
        for (int num : A) {
            if (!divisorsMap.containsKey(num)) {
                divisorsMap.put(num, getDivisors(num));
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                result = (result + beauty(A.get(i), A.get(j), divisorsMap)) % MOD;
            }
        }
        
        return (int) result;
    }
    
    private static int beauty(int x, int y, Map<Integer, Set<Integer>> divisorsMap) {
        Set<Integer> divisorsX = divisorsMap.get(x);
        Set<Integer> divisorsY = divisorsMap.get(y);
        
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
    
    private static Set<Integer> getDivisors(int n) {
        Set<Integer> divisors = new HashSet<>();
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