import java.io.*;
import java.util.*;
import java.lang.Math;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {
    
    public static int Chef_Dishes(int N, int C, List<List<Integer>> A) {
        // Write your code here
        // This method needs to be implemented based on the problem requirements
        return 0; // placeholder return
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