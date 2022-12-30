package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_BJ_02578_빙고 {

	static int N = 5; // 빙고 5 * 5
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				int number = Integer.parseInt(st.nextToken());
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (number == map[r][c]) {
							map[r][c] = 0;
							cnt++;
						}
						if (cnt >= 12) // 3빙고 최소 카운트
							check(map);
					}
				}
			}
		}
	}

	private static void check(int[][] map) {
		// 가로열 체크
		int line = 0;
		int temp;
		for (int i = 0; i < N; i++) {
			temp = 0;
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					temp++;
			}
			line += checkLine(temp);
			bingo(line);
		}

		// 세로열 체크
		for (int i = 0; i < N; i++) {
			temp = 0;
			for (int j = 0; j < N; j++) {
				if (map[j][i] == 0)
					temp++;
			}
			line += checkLine(temp);
			bingo(line);
		}

		// 오른쪽 대각선 체크
		temp = 0;
		for (int i = 0; i < N; i++) {
			if (map[i][i] == 0)
				temp++;
		}
		line += checkLine(temp);
		bingo(line);
		
		// 외쪽 대각선 체크
		temp = 0;
		for (int i = 0; i < N; i++) {
			if (map[i][N - i - 1] == 0)
				temp++;
		}
		line += checkLine(temp);
		bingo(line);
	}

	// 3빙고 체크
	private static void bingo(int b) {
		if (b >= 3) {
			System.out.println(cnt);
			System.exit(0);
		}
	}
	// 빙고 한줄 체크
	private static int checkLine(int cnt) {
		if (cnt == 5)
			return 1;
		else
			return 0;
	}
}