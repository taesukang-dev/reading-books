n = int(input())
lst = list(map(int, input().split()))

dp = [1] * n

for i in range(1, n):
	for j in range(i):
		if lst[i] < lst[j]:
			dp[i] = max(dp[j] + 1, dp[i])
print(n - max(dp))