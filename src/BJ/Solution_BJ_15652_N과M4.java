package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 + 같은 수 여러번 가능 + 비내림차순
// 14720kb, 92ms
public class Solution_BJ_15652_N과M4 {

	static int N, M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		solve(1, 0);
		System.out.println(sb);
	}

	private static void solve(int start, int depth) {

		if (depth == M) {
			for(int num: arr) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {

			arr[depth] = i;
			solve(i, depth + 1);
		}
		
	}
}
