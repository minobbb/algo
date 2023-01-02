package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14704kb, 196ms
public class BJ_17135_캐슬디펜스_G3 {

	static int N, M, D, killCnt, max = 0;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 길이
		M = Integer.parseInt(st.nextToken()); // 가로 길이
		D = Integer.parseInt(st.nextToken()); // 궁수 공격 거리 제한

		map = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(max);
	}

	private static void comb(int start, int cnt) {
		if (cnt == 3) {
			play();
			return;
		}

		for (int i = start; i < M; i++) {
			if (map[N][i] == 2)
				continue;
			map[N][i] = 2;
			comb(i + 1, cnt + 1);
			map[N][i] = 0;
		}
	}

	private static void play() {
		int[][] newMap = copyMap();
		int[][] archers = findArcher();
		killCnt = 0;
		while (true) {
			if (!checkEnemy(newMap))
				break; // 남은 적이 없다면
			attackEnemy(newMap, archers);
			moveEnemy(newMap);
		}
		max = Math.max(max, killCnt);
	}

	private static int[][] findArcher() {
		int[][] archer = new int[3][2];
		int idx = 0;
		for (int i = 0; i < M; i++) {
			if (map[N][i] == 2) {
				archer[idx][0] = i;
				archer[idx][1] = N;
				idx++;
			}
		}
		return archer;
	}

	private static boolean checkEnemy(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					return true;
			}
		}
		return false;
	}

	private static void attackEnemy(int[][] map, int[][] archers) {
		int[][] enemys = new int[3][2];
		for (int k = 0; k < 3; k++) {
			int archerX = archers[k][0];
			int archerY = archers[k][1];
			int enemyX = -1;
			int enemyY = -1;
			int mindist = Integer.MAX_VALUE;
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) { // 적이 있다면
						int dist = Math.abs(archerY - i) + Math.abs(archerX - j);

						if (dist < mindist && dist <= D) { // 거리가 더 가깝고 공격 사정거리 안에 있다면
							mindist = dist;
							enemyX = j;
							enemyY = i;
						}
						else if (dist == mindist && enemyX > j) { // 거리가 같을 때 더 왼쪽에 있다면
							enemyX = j;
							enemyY = i;

						}
					}
				}
			}
			if (enemyX == -1 || enemyY == -1) { // 공격할 적이 없는 경우
				enemys[k][0] = -1;
				enemys[k][1] = -1;
				continue;
			}
			enemys[k][0] = enemyX;
			enemys[k][1] = enemyY;
		}

		for (int i = 0; i < 3; i++) { // 공격한 적을 맵에서 제거
			int x = enemys[i][0];
			int y = enemys[i][1];
			if (x == -1 || y == -1)
				continue;
			if (map[y][x] == 1) {
				map[y][x] = 0;
				killCnt++;
			}
		}
		
	}

	private static void moveEnemy(int[][] map) {
		for (int i = 0; i < M; i++) {
			if (map[N - 1][i] == 1) // 성을 도달한 적 없애기
				map[N - 1][i] = 0;
		}

		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i + 1][j] = 1;
					map[i][j] = 0;
				}
			}
		}
	}

	private static int[][] copyMap() {
		int[][] copyMap = new int[N + 1][M];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}
}
