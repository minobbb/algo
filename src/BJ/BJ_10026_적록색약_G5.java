package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_10026_적록색약_G5 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, 0, -1, 0 }; // 우 하 좌 상
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		visited = new boolean[N][N];
		
		// 일반 사람
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[j][i]) {
					dfs(j, i);
					cnt++;
				}
			}
		}
		sb.append(cnt).append(" ");
		
		// G -> R 로 변경 (적녹색약)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G') map[i][j] = 'R';
			}
		}

		// 재사용을 위해 다시 초기화
		cnt = 0;
		visited = new boolean[N][N];
		// 적녹색약 사람
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[j][i]) {
					dfs(j, i);
					cnt++;
				}
			}
		}
		
		sb.append(cnt);
		System.out.println(sb);
	}

	private static void dfs(int y, int x) {
		
		char color = map[y][x];
		visited[y][x] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx])
				continue;
			if (map[ny][nx] == color) {
				dfs(ny, nx);
			}
		}
	}

//	private static void printMap(boolean[][] map) {
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map.length; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
}
