package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 11584kb, 80ms
public class Solution_BJ_11559_PuyoPuyo {

	static final int R = 12;
	static final int C = 6;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 0, -1, 1}; // 상하좌우
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		map = new char[R][C];
		int result = 0;
		
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		while(true) {
			if (!start())
				break;
			result++;
		}
		System.out.println(result);
	}

	
	private static boolean start() {
		boolean flag = false;
		visited = new boolean[R][C];
		
		// 맵 전체 탐색
		for (int i = R - 1; i >= 0; i--) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '.' || visited[i][j])
					continue;
				
				// 연결되어있는 뿌요 리스트에 담기 (bfs)
				Queue<int[]> queue = new LinkedList<>();
				queue.add(new int[] {j, i});
				List<int[]> list = new ArrayList<>();
				list.add(new int[] {j, i});
				visited[i][j] = true;
				char color = map[i][j];
				
				while (!queue.isEmpty()) {
					int[] cur = queue.poll();
					int x = cur[0];
					int y = cur[1];
					
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						while (nx >= 0 && nx < C && ny >= 0 && ny < R && !visited[ny][nx] && map[ny][nx] == color) {
							visited[ny][nx] = true;
							list.add(new int[] {nx, ny});
							queue.add(new int[] {nx, ny});
						}
					}
				}
				
				// 뿌요가 터질 수 있다면 (4개 이상 연결)
				if (list.size() >= 4) {
					flag = true;
					for(int[] p: list) {
						map[p[1]][p[0]] = '.';
					}
				}	
			}
		}
		
		// 터지고 남은 뿌요 아래로 내리기
		for (int j = 0; j < C; j++) {
			Queue<Character> queue = new LinkedList<>();
			for (int i = R - 1; i >= 0; i--) {
				if (map[i][j] != '.') {
					queue.add(map[i][j]);
					map[i][j] = '.';
				}
			}
			int idx = R - 1;
			while(!queue.isEmpty()) {
				map[idx--][j] = queue.poll();
			}
		}
		return flag;
	}
}
