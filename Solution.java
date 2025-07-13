import java.io.*;
import java.util.*;
import java.lang.Math;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {
    
    public static int Chef_Dishes(int N, int C, List<List<Integer>> A) {
        // Create a list of chefs with their expertise and messiness
        List<int[]> chefs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int expertise = A.get(i).get(0);
            int messiness = A.get(i).get(1);
            chefs.add(new int[]{expertise, messiness});
        }
        
        // Sort chefs by messiness in ascending order for optimal strategy
        // We want to use chefs with lower messiness first to keep complexity low
        chefs.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        int currentComplexity = C;
        int chefsUsed = 0;
        
        // Try to use each chef in the sorted order
        for (int[] chef : chefs) {
            int expertise = chef[0];
            int messiness = chef[1];
            
            // Check if this chef can cook (expertise >= current complexity)
            if (expertise >= currentComplexity) {
                chefsUsed++;
                // Update complexity after this chef cooks
                currentComplexity = Math.max(currentComplexity, messiness);
            }
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
        
        // Call the Chef_Dishes method and print result
        int result = Chef_Dishes(N, C, A);
        System.out.println(result);
        
        scan.close();
    }
}