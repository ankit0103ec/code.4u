import java.io.*;
import java.util.*;
import java.lang.Math;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Solution {
    
    public static int Chef_Dishes(int N, int C, List<List<Integer>> A) {
        List<int[]> chefs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int expertise = A.get(i).get(0);
            int messiness = A.get(i).get(1);
            chefs.add(new int[]{expertise, messiness});
        }
        
        // Key insight: We want to use chefs in order that maximizes total chefs used
        // Strategy: Among chefs that can cook at current complexity, 
        // choose the one with minimum messiness to keep future complexity low
        
        boolean[] used = new boolean[N];
        int currentComplexity = C;
        int chefsUsed = 0;
        
        while (true) {
            int bestChef = -1;
            int minMessiness = Integer.MAX_VALUE;
            
            // Find the chef with minimum messiness who can cook at current complexity
            for (int i = 0; i < N; i++) {
                if (!used[i] && chefs.get(i)[0] >= currentComplexity) {
                    if (chefs.get(i)[1] < minMessiness) {
                        minMessiness = chefs.get(i)[1];
                        bestChef = i;
                    }
                }
            }
            
            // If no chef can cook, break
            if (bestChef == -1) {
                break;
            }
            
            // Use the best chef
            used[bestChef] = true;
            chefsUsed++;
            currentComplexity = Math.max(currentComplexity, chefs.get(bestChef)[1]);
        }
        
        return chefsUsed;
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int N = Integer.parseInt(scan.nextLine().trim());
        int C = Integer.parseInt(scan.nextLine().trim());
        
        List<List<Integer>> A = new ArrayList<>(N);
        
        for(int i = 0; i < N; i++) {
            A.add(
                Arrays.asList(scan.nextLine().trim().split(" "))
                    .stream()
                    .map(s -> Integer.parseInt(s))
                    .collect(toList())
            );
        }
        
        int result = Chef_Dishes(N, C, A);
        System.out.println(result);
        
        scan.close();
    }
}