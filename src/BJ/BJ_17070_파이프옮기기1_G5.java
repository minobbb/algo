package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 293616kb, 436ms
public class BJ_17070_파이프옮기기1_G5 {

	static class Pipe {
		int x, y, state; // state = 0 :가로, 1 : 대각선, 2 : 세로

		public Pipe(int x, int y, int state) {
			super();
			this.x = x;
			this.y = y;
			this.state = state;
		}
	}

	static int N;
	static int result = 0;
	static int[][] map;
	static int[] dx = { 1, 1, 0 }; // 우, 우하, 하
	static int[] dy = { 0, 1, 1 };

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

		dfs(new Pipe(1, 0, 0));
		System.out.println(result);
	}

	private static void dfs(Pipe pipe) {

		int x = pipe.x;
		int y = pipe.y;

		if (x == N - 1 && y == N - 1) {
			result++;
			return;
		}

		switch (pipe.state) {
		case 0: // 가로
			for (int i = 0; i < 2; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (i == 0 && checkRange(nx, ny) && map[ny][nx] != 1) {
					dfs(new Pipe(nx, ny, i));
				} else if (i == 1 && checkRange(nx, ny) && checkDiag(nx, ny)) {
					dfs(new Pipe(nx, ny, i));
				}
			}
			break;

		case 1: // 대각선
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (i == 0 && checkRange(nx, ny) && map[ny][nx] != 1) {
					dfs(new Pipe(nx, ny, i));
				} else if (i == 1 && checkRange(nx, ny) && checkDiag(nx, ny)) {
					dfs(new Pipe(nx, ny, i));
				} else if (i == 2 && checkRange(nx, ny) && map[ny][nx] != 1)
					dfs(new Pipe(nx, ny, i));
			}
			break;

		case 2: // 세로
			for (int i = 1; i < 3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (i == 1 && checkRange(nx, ny) && checkDiag(nx, ny)) {
					dfs(new Pipe(nx, ny, i));
				} 
				else if (i == 2 && checkRange(nx, ny) && map[ny][nx] != 1)
					dfs(new Pipe(nx, ny, i));
			}
			break;

		}
	}

	private static boolean checkRange(int nx, int ny) {
		if (nx >= 0 && nx < N && ny >= 0 && ny < N)
			return true;
		return false;
	}
	
	private static boolean checkDiag(int nx, int ny) { // 대각선으로 파이프 놓을 시 벽 체크
		if (map[ny][nx] == 0 && map[ny - 1][nx] == 0 && map[ny][nx - 1] == 0)
			return true;
		return false;
	}
}
