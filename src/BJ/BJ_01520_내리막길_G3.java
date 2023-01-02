package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 33844kb, 312ms
public class BJ_01520_내리막길_G3 {

	static int M, N;
	static int[][] map, cnt;
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		cnt = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cnt[i][j] = -1;
			}
		}
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int x, int y) {
		if (x == N - 1 && y == M - 1) // 목적지에 도달하면 경우의 수 1 추가
			return 1;

		if (cnt[y][x] != -1) // 방문체크
			return cnt[y][x];
		
		else {
			cnt[y][x] = 0;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (map[y][x] > map[ny][nx])
					cnt[y][x] += dfs(nx, ny);
			}
		}
		return cnt[y][x];
	}

}
