package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 13708kb 112ms
public class Solution_BJ_01012_유기농배추 {

	static int M, N;
	static int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {			
			StringTokenizer st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로길이
			N = Integer.parseInt(st.nextToken()); // 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추의 개수
			
			map = new int[N][M];
			boolean[][] visited = new boolean[N][M]; // 방문했는지 체크
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken()); // 배추의 가로 좌표
				int y = Integer.parseInt(st.nextToken()); // 배추의 세로 좌표
				map[y][x] = 1; // 배추 위치 저장
			}
			
			int result = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visited[i][j]) { // 배추가 있고 방문하지 않은 곳만 체크
						dfs(j, i, visited);
						result++;
					}
				}
			}
			
			System.out.println(result);
			
		}
	}

	private static void dfs(int x, int y, boolean[][] visited) {
		
		visited[y][x] = true; // 방문처리
		
		for (int i = 0; i < 4; i++) { // 4방탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= M || ny < 0 || ny >= N || map[ny][nx] == 0 || visited[ny][nx]) // 범위를 넘어서거나 배추가 없거나 이미 방문했을 시
				continue;
			
			dfs(nx, ny, visited);
		}
	}
}
