package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02563_색종이_S5 {

	static final int N = 100; // 도화지 크기 N * N

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		int[][] map = new int[N + 1][N + 1]; // 1~100 사용하기 위해 +1

		for (int p = 0; p < T; p++) {
			st = new StringTokenizer(in.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int maxW = (w + 10 > 100) ? 100 : w + 10;
			int maxH = (h + 10 > 100) ? 100 : h + 10;
			for (int i = h; i < maxH; i++) {
				for (int j = w; j < maxW; j++) {
					if (map[i][j] == 0)
						map[i][j] = 1;
				}
			}
		}

		int cnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1)
					cnt++;
			}
		}
		System.out.println(cnt);
	}
}
