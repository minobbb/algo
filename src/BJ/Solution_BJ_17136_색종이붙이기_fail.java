package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
답 : 5

4 4 4 4 3 3 3 0 0 0
4 4 4 4 3 3 3 0 0 0
4 4 4 4 3 3 3 0 0 0
4 4 4 4 1 2 2 0 0 0
5 5 5 5 5 2 2 0 0 0
5 5 5 5 5 0 0 0 0 0
5 5 5 5 5 0 0 0 0 0
5 5 5 5 5 0 0 0 0 0
5 5 5 5 5 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
 */

public class Solution_BJ_17136_색종이붙이기_fail {

	static int cnt, min = Integer.MAX_VALUE;
	static boolean[] visited = new boolean[6];
	static int[] paper = new int[6];
	static int[][] map = new int[10][10];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (checkZero(map)) { // 모든칸이 0이라면
			System.out.println(0);
			System.exit(0);
		}
		
		perm(0, new int[5]);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void perm(int idx, int[] arr) {
		if (idx == 5) {
			cnt = 0;
			int[][] newMap = copyMap();
			initPaper();
			for (int size : arr) {
				checkPaper(size, newMap);
				if (checkZero(newMap)) // map의 모든 칸이 0이면 종료
					break;
			}
			if (!checkZero(newMap)) // 색종이를 다 붙혔는데 1이 남아있다면
				return;
			min = Math.min(min, cnt);
			return;
		}

		for (int i = 1; i <= 5; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			arr[idx] = i;
			perm(idx + 1, arr);
			visited[i] = false;
		}

	}

	// 1x1 ~ 5x5 색종이 5장씩 가지고 있음. (인덱스로 관리)
	private static void initPaper() {
		for (int size = 1; size <= 5; size++) {
			paper[size] = 5;
		}
	}

	private static void checkPaper(int size, int[][] newMap) {
		for (int r = 0; r <= 10 - size; r++) {
			for (int c = 0; c <= 10 - size; c++) {
				if (map[r][c] == 1) {
					if (paper[size] > 0) {
						if (gluePaper(r, c, size, newMap)) { // 색종이를 붙힐 수 있다면
							paper[size]--;
							cnt++;
						}
					}
					if (paper[size] == 0)
						return;
				}
			}
		}
	}

	private static boolean gluePaper(int r, int c, int size, int[][] newMap) {
		// 색종이 붙일 수 있는지 확인
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (newMap[i][j] == 0) {
					return false;
				}
			}
		}

		// 색종이를 붙일 수 있다면
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				newMap[i][j] = 0;
			}
		}
		return true;
	}

	private static boolean checkZero(int[][] map) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	private static int[][] copyMap() {
		int[][] newMap = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
}
