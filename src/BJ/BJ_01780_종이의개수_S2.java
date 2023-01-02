package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 317236kb, 868ms
public class BJ_01780_종이의개수_S2 {
	
	static int N;
	static int[] result = new int[3]; // -1, 0, 1
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0, 0, N);
		sb.append(result[0]).append("\n").append(result[1]).append("\n").append(result[2]).append("\n");
		System.out.println(sb);
		
	}

	private static void solve(int x, int y, int len) {
		
		if (checkNum(x, y, len)) {
			result[map[y][x] + 1]++;
			return;
		}
		
		int cutLen = len / 3;
		
		// 9등분
		// 1~3사분면
		solve(x, y, cutLen);
		solve(x + cutLen, y, cutLen);
		solve(x + cutLen + cutLen, y, cutLen);
		
		// 4~6사분면
		solve(x, y + cutLen, cutLen);
		solve(x + cutLen, y + cutLen, cutLen);
		solve(x + cutLen + cutLen, y + cutLen, cutLen);
		
		// 7~9사분면
		solve(x, y + cutLen + cutLen, cutLen);
		solve(x + cutLen, y + cutLen + cutLen, cutLen);
		solve(x + cutLen + cutLen, y + cutLen + cutLen, cutLen);
		
	}

	private static boolean checkNum(int x, int y, int len) {

		int temp = map[y][x];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (map[y + i][x + j] != temp)
					return false;
			}
		}
		
		return true;
	}
}
