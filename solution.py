import math

def get_ans(N, A):
    # Write your code here
    result = 0
    MOD = 1000000007
    
    if N <= 1:
        return 0
    
    for i in range(N):
        for j in range(i + 1, N):
            result = (result + beauty(A[i], A[j])) % MOD
    
    return result

def beauty(x, y):
    if x == y:
        return 0
    if x <= 0 or y <= 0:
        return 0
    
    divisors_x = get_divisors_optimized(x)
    divisors_y = get_divisors_optimized(y)
    
    count = 0
    
    # Count divisors that divide exactly one of x or y
    for d in divisors_x:
        if d not in divisors_y:
            count += 1
    
    for d in divisors_y:
        if d not in divisors_x:
            count += 1
    
    return count

def get_divisors_optimized(n):
    divisors = set()
    if n <= 0:
        return divisors
    
    # Handle 1 specially
    if n == 1:
        divisors.add(1)
        return divisors
    
    # Find all divisors efficiently
    for i in range(1, int(math.sqrt(n)) + 1):
        if n % i == 0:
            divisors.add(i)
            if i != n // i:
                divisors.add(n // i)
    
    return divisors

def main():
    N = int(input().strip())
    A = []
    
    for j in range(N):
        A.append(int(input().strip()))
    
    result = get_ans(N, A)
    print(result)

if __name__ == "__main__":
    main()