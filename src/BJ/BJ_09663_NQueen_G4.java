package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 15088kb, 5180ms
public class BJ_09663_NQueen_G4 {

	static int N, result = 0;
	static int[][] map;
	static int[][] checked;
	static int[] dx = {-1, 0, 1}; // 좌하, 하, 우하
	static int[] dy = {1, 1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		checked = new int[N][N];
		solve(0);
		System.out.println(result);
	}

	private static void solve(int depth) {
		
		if (depth == N - 1) { // 마지막 줄에 도달했을 때,
			for (int i = 0; i < N; i++) {
				if (checked[depth][i] == 0) // 마지막 줄에 놓을 수 있는 퀸이 있다면
					result++;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {

			if (checked[depth][i] != 0) // 방문 못하는곳이면 (퀸이 공격할 수 있다면)
				continue;

			map[depth][i] = 1;
			
			// 다음 depth 못가는곳 3방체크 (좌하, 하, 우하)
			for (int j = 0; j < 3; j++) {
				int nx = i + dx[j];
				int ny = depth + dy[j];
				
				while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (checked[ny][nx] == 0) // 아직 놓을 수 있는 곳이면
						checked[ny][nx] = depth + 1;
					nx += dx[j];
					ny += dy[j];
				}
			}
			
			solve(depth + 1);
			
			// checked 해제
			for (int j = 0; j < 3; j++) {
				int nx = i + dx[j];
				int ny = depth + dy[j];
				
				while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (checked[ny][nx] == depth + 1) // 해당 depth에서 체크한 곳이면
						checked[ny][nx] = 0;
					nx += dx[j];
					ny += dy[j];
				}
			}
		}
	}
}
