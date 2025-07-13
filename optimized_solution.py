import sys

def get_ans(n, portals, q, queries):
    MOD = 10**9 + 7
    result_xor = 0
    
    for query in queries:
        if query[0] == 0:
            u, v, c = query[1] - 1, query[2] - 1, query[3]
            
            # dp[color] = ways to reach current room with this color
            dp = [0, 0]
            dp[c] = 1
            
            # Process each room from u to v-1
            for room in range(u, v):
                r, b, w = portals[room]
                new_dp = [0, 0]
                
                # Red card can use red and white portals
                if dp[0]:
                    new_dp[0] = (new_dp[0] + dp[0] * r) % MOD
                    new_dp[1] = (new_dp[1] + dp[0] * w) % MOD
                
                # Blue card can use blue and white portals
                if dp[1]:
                    new_dp[1] = (new_dp[1] + dp[1] * b) % MOD
                    new_dp[0] = (new_dp[0] + dp[1] * w) % MOD
                
                dp = new_dp
            
            result_xor ^= dp[c]
        else:
            i, r, b, w = query[0] - 1, query[1], query[2], query[3]
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