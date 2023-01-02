package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12184kb, 104ms
public class BJ_01149_RGB거리_S1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(in.readLine());
		int[][] price = new int[N][3];
		int[][] dp = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 3; j++) {
				price[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기값 설정
		dp[0][0] = price[0][0]; // 시작 red
		dp[0][1] = price[0][1]; // 시작 green
		dp[0][2] = price[0][2]; // 시작 blue
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + price[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + price[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + price[i][2];
		}
		
		System.out.println(Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2])));
	}
}
