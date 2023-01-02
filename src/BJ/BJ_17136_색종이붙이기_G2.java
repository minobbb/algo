package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 20404kb, 220ms
public class BJ_17136_색종이붙이기_G2 {

	static int min = Integer.MAX_VALUE;
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
		if (checkMap(map)) { // 맵이 비었는지 확인
			System.out.println(0);
			System.exit(0);
		}
		
		initPaper();
		dfs(0, 0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void dfs(int x, int y, int cnt) {
		if (x > 9 && y >= 9) { // 마지막까지 탐색을 완료하면
			min = Math.min(min, cnt);
			return;
		}
		
		if (cnt >= min) { // cnt가 min보다 커지면 더 진행하는게 의미가 없음
			return;
		}
		
		if (x > 9) { // 줄바꿈
			dfs(0, y + 1, cnt);
			return;
		}
		
		if (map[y][x] == 1) {
			for (int size = 5; size >= 1; size--) {
				if (paper[size] > 0 && isAttach(x, y, size)) { // 색종이가 남아있고 붙힐 수 있다면
					attach(x, y, size, 0); // 색종이 붙이기
					paper[size]--;
					dfs(x + 1, y, cnt + 1);
					attach(x, y, size, 1); // 색종이 떼기
					paper[size]++;
				}
			}
		}
		else {
			dfs(x + 1, y, cnt);
		}
		
	}
	

	private static boolean isAttach(int x, int y, int size) {
		if (x + size > 10 || y + size > 10) // 범위를 초과하면
			return false;
		
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (map[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	private static void attach(int x, int y, int size, int n) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				map[i][j] = n;
			}
		}
	}
	
	private static boolean checkMap(int[][] map) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	// 1x1 ~ 5x5 색종이 5장씩 가지고 있음. (인덱스로 관리)
	private static void initPaper() {
		for (int size = 1; size <= 5; size++) {
			paper[size] = 5;
		}
	}
}

