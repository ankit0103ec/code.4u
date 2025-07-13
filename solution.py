import sys

def get_ans(n, portals, q, queries):
    MOD = 10**9 + 7
    
    # Parse portals - handle both single numbers and arrays
    rooms = []
    for portal in portals:
        if len(portal) == 1:
            # Parse as 3-digit number: abc means a red, b blue, c white
            num = portal[0]
            r = num // 100
            b = (num // 10) % 10
            w = num % 10
            rooms.append([r, b, w])
        else:
            rooms.append(list(portal))
    
    xor_result = 0
    
    for query in queries:
        # Parse query - handle both single numbers and arrays
        if len(query) == 1:
            num = query[0]
            if num >= 1000:
                # 4-digit number: abcd means a=type, b=u, c=v, d=c
                query_type = num // 1000
                u = (num // 100) % 10
                v = (num // 10) % 10
                c = num % 10
                parsed_query = [query_type, u, v, c]
            else:
                # 3-digit number: abc means 0, a, b, c
                query_type = 0
                u = num // 100
                v = (num // 10) % 10
                c = num % 10
                parsed_query = [query_type, u, v, c]
        else:
            parsed_query = query
        
        if parsed_query[0] == 0:  # Path finding query
            u, v, c = parsed_query[1], parsed_query[2], parsed_query[3]
            
            # DP: dp[i][color] = number of ways to reach room i with card color
            # color: 0 = red, 1 = blue
            dp = [[0, 0] for _ in range(n)]
            
            # Base case: start at room u with card color c
            dp[u-1][c] = 1  # Convert to 0-indexed
            
            # Fill DP table from room u to room v-1
            for i in range(u-1, v-1):
                red_portals, blue_portals, white_portals = rooms[i]
                
                # From room i to room i+1
                # Red card -> red portal (stays red) or white portal (becomes blue)
                # Blue card -> blue portal (stays blue) or white portal (becomes red)
                next_red = (dp[i][0] * red_portals + dp[i][1] * white_portals) % MOD
                next_blue = (dp[i][1] * blue_portals + dp[i][0] * white_portals) % MOD
                
                dp[i+1][0] = next_red
                dp[i+1][1] = next_blue
            
            # Answer is the number of ways to reach room v with the same color c
            answer = dp[v-1][c]
            xor_result ^= answer
            
        else:  # Update query
            if len(query) == 1:
                # Parse as 4-digit number: abcd means a=room, b=r, c=b, d=w
                num = query[0]
                room = num // 1000
                r = (num // 100) % 10
                b = (num // 10) % 10
                w = num % 10
                rooms[room-1] = [r, b, w]
            else:
                i, r, b, w = parsed_query[0], parsed_query[1], parsed_query[2], parsed_query[3]
                rooms[i-1] = [r, b, w]  # Convert to 0-indexed
    
    return xor_result

def main():
    n = int(sys.stdin.readline().strip())
    portals = []
    for _ in range(n):
        portals.append(list(map(lambda x: int(x), sys.stdin.readline().strip().split())))
    
    q = int(sys.stdin.readline().strip())
    queries = []
    for _ in range(q):
        queries.append(list(map(lambda x: int(x), sys.stdin.readline().strip().split())))
    
    result = get_ans(n, portals, q, queries)
    print(result)

if __name__ == "__main__":
    main()