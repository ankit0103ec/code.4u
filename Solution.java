import java.io.*;
import java.util.*;
import java.lang.Math;

public class Solution {
    
    public static int get_ans(int N, List<Integer> A) {
        final int MOD = 1000000007;
        final int MAX_VAL = 1000000;
        
        // Precompute divisor counts for each number
        int[] divisorCount = new int[MAX_VAL + 1];
        for (int i = 1; i <= MAX_VAL; i++) {
            for (int j = i; j <= MAX_VAL; j += i) {
                divisorCount[j]++;
            }
        }
        
        // Count frequency of each number in array
        int[] freq = new int[MAX_VAL + 1];
        for (int x : A) {
            freq[x]++;
        }
        
        // Calculate sum of (divisors(A[i]) + divisors(A[j])) for all pairs i < j
        // Each element A[i] appears in exactly (N-1) pairs
        // So total sum = (N-1) * sum of all divisor counts
        long totalDivisorSum = 0;
        for (int x : A) {
            totalDivisorSum = (totalDivisorSum + divisorCount[x]) % MOD;
        }
        
        long pairDivisorSum = ((long)(N - 1) * totalDivisorSum) % MOD;
        
        // Calculate common divisors sum efficiently
        long commonDivisorSum = 0;
        
        // For each possible divisor d, count how many numbers in A are divisible by d
        for (int d = 1; d <= MAX_VAL; d++) {
            long count = 0;
            // Sum frequencies of all multiples of d
            for (int multiple = d; multiple <= MAX_VAL; multiple += d) {
                count += freq[multiple];
            }
            
            // If d divides 'count' numbers, it contributes count*(count-1)/2 to common divisor sum
            if (count >= 2) {
                long contribution = (count * (count - 1) / 2) % MOD;
                commonDivisorSum = (commonDivisorSum + contribution) % MOD;
            }
        }
        
        long result = (pairDivisorSum - 2 * commonDivisorSum + MOD) % MOD;
        return (int) result;
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