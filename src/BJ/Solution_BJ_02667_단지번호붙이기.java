package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// cnt로 단지 내 집의 수를 샌 후 ArrayList에 저장.
// sort를 통해 오름차순 정렬한 후 출력
// 11624kb, 76ms
public class Solution_BJ_02667_단지번호붙이기 {

	static int N, cnt;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++)
			map[i] = in.readLine().toCharArray();
		
		ArrayList<Integer> result = new ArrayList<>();
		int res = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt = 0;
				if (map[i][j] == '1' && !visited[i][j]) {
					cnt++;
					res++;
					dfs(j, i);
					result.add(cnt);
				}
			}
		}
		
		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		sb.append(res).append("\n");
		for (int i = 0, size = result.size(); i < size; i++) {
			sb.append(result.get(i)).append("\n");
		}
		System.out.println(sb);
		
		
	}


	private static void dfs(int x, int y) {
		
		visited[y][x] = true; // 방문처리
		
		for (int i = 0; i < 4; i++) { // 4방탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] == '0' || visited[ny][nx])
				continue;
			cnt++;
			dfs(nx, ny);
		}
		
	}

}


