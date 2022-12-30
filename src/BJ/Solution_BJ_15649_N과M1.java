package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1부터 N까지 자연수 중에서 중복 없이 M개 고른 수열

public class Solution_BJ_15649_N과M1 {

	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		visited = new boolean[N + 1]; // 1 ~ N
		solve(0);
		System.out.println(sb);
	}

	private static void solve(int depth) {
		if (depth == M) {
			for (int val : arr) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			arr[depth] = i;
			solve(depth + 1);
			visited[i] = false;
		}
	}
}
