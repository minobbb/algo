package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_JW_1681_해밀턴순환회로 {

	static int N, result = Integer.MAX_VALUE;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0, 0, 0, new boolean[N]);
		System.out.println(result);
	}
	
	// 순열
	private static void solve(int cnt, int cur, int distance, boolean[] visited) {
		if (cnt == N - 1) { // 실제로 모두 방문하는 횟수는 N - 1
			if (map[cur][0] == 0) return; // 회사로 돌아갈 수 없는 경우
			distance += map[cur][0]; // 회사로 돌아가는 거리
			result = Math.min(result, distance);
			return;
		}
		
		if (distance > result) // 거리가 더 멀어지면 return
			return;
		
		for (int i = 1; i < N; i++) {
			if (visited[i]) continue;
			if (map[cur][i] == 0) continue; // 길이 없는 경우
			visited[i] = true;
			solve(cnt + 1, i, distance + map[cur][i], visited);
			visited[i] = false;
		}
	}
}
