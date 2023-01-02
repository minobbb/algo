package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 13348kb, 120ms
public class BJ_02630_색종이만들기_S2 {

	static int[][] map;
	static int N, white = 0, blue = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cut(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void cut(int x, int y, int len) {

		if (colorCheck(x, y, len)) { // 잘라진 사각형이 모두 같은 색이라면
			if (map[y][x] == 1)// 무슨색인지 확인
				blue++;
			else
				white++;
			return;
		}

		int cutLen = len / 2; // 4등분 했을 때의 변의 길이

		cut(x, y, cutLen); // 1사분면
		cut(x + cutLen, y, cutLen); // 2사분면
		cut(x, y + cutLen, cutLen); // 3사분면
		cut(x + cutLen, y + cutLen, cutLen); // 4사분면

	}

	private static boolean colorCheck(int x, int y, int len) {

		int color = map[y][x];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (map[y + i][x + j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}
