package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_BJ_03040_백설공주와일곱난쟁이 {

	static int N = 9; // 난쟁이의 수
	static int[] height;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		height = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(in.readLine());
			sum += height[i];
		}
		solve(0, 0, sum, new boolean[N]);
	}
	
	private static void solve(int cnt, int start, int sum, boolean[] check) {
		if (cnt == 2) {
			if (sum == 100) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < check.length; i++) {
					if (!check[i])
						sb.append(height[i]).append('\n');
				}
				System.out.println(sb);
			}
			return;
		}

		for (int i = start; i < N; i++) {
			check[i] = true;
			solve(cnt + 1, i + 1,sum - height[i], check);
			check[i] = false;
			
		}
	}
}
