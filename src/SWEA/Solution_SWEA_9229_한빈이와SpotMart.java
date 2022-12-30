package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_9229_한빈이와SpotMart {

	static int N, M, result;
	static int[] snack;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		int T = Integer.parseInt(in.readLine());
	
		for (int tc = 1; tc <= T; tc++) {
			String[] input = in.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			snack = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++)
				snack[i] = Integer.parseInt(st.nextToken());
			result = -1;
			int sum;
			
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					sum = snack[i] + snack[j];
					if (sum <= M && sum > result)
						result = sum;
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
			
		}
		System.out.println(sb);
	}

//	public static void pickSnack(int cnt, int idx, int sum) {
//		if (sum > M)
//			return;
//		
//		if (cnt == 2) {
//			if (sum > result) {
//				result = sum;
//				return;
//			}
//		}
//		
//		if (idx == N)
//			return;
//		
//		pickSnack(cnt + 1, idx + 1, sum + snack[idx]); // 과자를 집은 경우
//		pickSnack(cnt, idx + 1, sum); // 과자를 집지 않은 경우
//	}
}
