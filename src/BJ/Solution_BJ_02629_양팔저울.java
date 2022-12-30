package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 12072kb, 76ms
public class Solution_BJ_02629_양팔저울 {

	static int N, M;
	static int[] chu, marble;
	static boolean[][] check;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		chu = new int[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		check = new boolean[N + 1][15001];
		
		M = Integer.parseInt(in.readLine());
		marble = new int[M];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < M; i++) {
			marble[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		for (int i = 0; i < M; i++) {
			if (marble[i] > 15000)
				sb.append("N ");
			else if (check[N][marble[i]]) // N번째에 모든 경우의 수 체크됨
				sb.append("Y ");
			else
				sb.append("N ");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int weight, int idx) {

		if(check[idx][weight])
			return;

		check[idx][weight] = true;
		
		if (idx == N) // 모든 추를 확인했다면
			return;
		
		dfs(weight + chu[idx], idx + 1); // 추를 오른쪽에 올리기
		dfs(weight, idx + 1); // 추 사용 x
		dfs(Math.abs(weight - chu[idx]), idx + 1); // 추를 왼쪽에 올리기
	}

}
