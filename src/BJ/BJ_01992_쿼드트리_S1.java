package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_01992_쿼드트리_S1 {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		solve(N, map);
		System.out.println(sb);
	}

	private static void solve(int len, char[][] map) {

		char value = map[0][0];

		if (!checkMap(map)) { // 맵의 값이 다르면
			sb.append('(');
			solve(len / 2, splitMap(map, 0, 0)); // 1사분면
			solve(len / 2, splitMap(map, 0, len / 2)); // 2사분면
			solve(len / 2, splitMap(map, len / 2, 0)); // 3사분면
			solve(len / 2, splitMap(map, len / 2, len / 2)); // 4사분면
			sb.append(')');
		} else { // 맵의 값이 모두 같다면
			sb.append(value);
		}
		return;
	}

	private static boolean checkMap(char[][] map) { // 맵의 모든 값이 같은지 체크
		char value = map[0][0];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != value)
					return false;
			}
		}
		return true;
	}

	private static char[][] splitMap(char[][] map, int x, int y) { // map 4등분으로 쪼개기
		int size = map.length / 2;
		char[][] splitmap = new char[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				splitmap[i][j] = map[x + i][y + j];
			}
		}
		return splitmap;
	}

}
