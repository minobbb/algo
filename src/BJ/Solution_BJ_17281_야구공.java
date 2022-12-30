package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 15496kb, 416ms
public class Solution_BJ_17281_야구공 {
	static final int P = 9; // 선수 9명
	static int N, max = 0;
	static int[][] scores;
	static int[] order;
	static boolean[] selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine()); // 이닝 수
		scores = new int[N][P + 1];
		selected = new boolean[P + 1];
		order = new int[P + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= P; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order[4] = 1;
		perm(1);
		System.out.println(max);
	}

	public static void perm(int cnt) {
		if (cnt == 4) {
			perm(cnt + 1);
			return;
		}

		if (cnt > 9) {
			play();
			return;
		}

		for (int i = 2; i <= 9; i++) {
			if (!selected[i]) {
				selected[i] = true;
				order[cnt] = i;
				perm(cnt + 1);
				selected[i] = false;
			}
		}
	}

	private static void play() {
	int score = 0; // 총 점수
	int cur = 1; // 타자 순서
	boolean[] base = new boolean[4]; // 0 ~ 3루
	
	for (int i = 0; i < N; i++) { // N이닝 반복
		int outCnt = 0;
		base[0] = base[1] = base[2] = base[3] = false;
		
		while (true) {
			if (outCnt == 3)
				break;
			int hit = scores[i][order[cur]]; // cur번 타자가 공을 친 결과
			base[0] = true; // 타석에 있는 타자
			
			if (hit == 0) { // 아웃
				outCnt++;
			}
			else { // 공을 쳤다면
				score += getPoint(hit, base);
			}
			if (++cur == 10) // 다음 타자로 변경
				cur = 1;
		}
	}
	max = Math.max(max, score);
}

	private static int getPoint(int hit, boolean[] base) {
		int score = 0;
		
		for (int i = 3; i >= 0; i--) {
			if (base[i]) {
				if (i + hit >= 4) { // 홈을 밟았다면
					score++;
				} else {
					base[i + hit] = true;
				}
				base[i] = false;
			}
		}
		return score;
	}
}
