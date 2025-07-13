import sys

def get_ans(n, portals, q, queries):
    MOD = 10**9 + 7
    result_xor = 0
    
    # Process each query
    for query in queries:
        if query[0] == 0:
            # Query type 0: calculate paths from u to v
            u, v, c = query[1], query[2], query[3]
            u -= 1  # Convert to 0-based indexing
            v -= 1
            
            # dp[i][color] = number of ways to reach room i with color 'color'
            # color: 0 = red, 1 = blue
            dp = [[0] * 2 for _ in range(n)]
            dp[u][c] = 1
            
            # Process each room from u to v-1
            for room in range(u, v):
                red_portals, blue_portals, white_portals = portals[room]
                
                # For each current color
                for curr_color in range(2):
                    if dp[room][curr_color] == 0:
                        continue
                    
                    # Use red portals
                    if curr_color == 0:  # Red card
                        dp[room + 1][0] = (dp[room + 1][0] + dp[room][0] * red_portals) % MOD
                    
                    # Use blue portals  
                    if curr_color == 1:  # Blue card
                        dp[room + 1][1] = (dp[room + 1][1] + dp[room][1] * blue_portals) % MOD
                    
                    # Use white portals (changes color)
                    dp[room + 1][1 - curr_color] = (dp[room + 1][1 - curr_color] + dp[room][curr_color] * white_portals) % MOD
            
            # Count ways where start and end colors are the same
            answer = (dp[v][c]) % MOD
            result_xor ^= answer
            
        else:
            # Update portals in room i
            i, r, b, w = query[0], query[1], query[2], query[3]
            i -= 1  # Convert to 0-based indexing
            portals[i] = [r, b, w]
    
    return result_xor

def main():
    n = int(sys.stdin.readline().strip())
    portals = []
    for _ in range(n):
        portals.append(list(map(int, sys.stdin.readline().strip().split())))
    
    q = int(sys.stdin.readline().strip())
    queries = []
    for _ in range(q):
        queries.append(list(map(int, sys.stdin.readline().strip().split())))
    
    result = get_ans(n, portals, q, queries)
    print(result)

if __name__ == "__main__":
    main()