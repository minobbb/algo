package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1953_탈주범검거 {

	static class Pos {
		int x, y, type, time;

		public Pos(int x, int y, int type, int time) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
			this.time = time;
		}

	}

	static int N, M, R, C, L;
	static int[][] map;
	static int[][] moveX;
	static int[][] moveY;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		initMove();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로 길이
			M = Integer.parseInt(st.nextToken()); // 가로 길이
			R = Integer.parseInt(st.nextToken()); // 맨홀 세로 위치
			C = Integer.parseInt(st.nextToken()); // 맨홀 가로 위치
			L = Integer.parseInt(st.nextToken()); // 소요 시간
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			sb.append("#").append(tc).append(" ").append(caseCnt()).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(C, R, map[R][C], 1));
		visited[R][C] = true;
		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			int x = pos.x;
			int y = pos.y;
			int type = pos.type;
			int time = pos.time;
			if (time == L)
				break;

			for (int d = 0; d < 4; d++) {
				if (moveX[type][d] == 0 && moveY[type][d] == 0)
					continue;
				int nx = x + moveX[type][d];
				int ny = y + moveY[type][d];
				// 어디로 갈수있는지 type 따라서..
				if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] != 0
						&& checkNextTunnel(d, nx, ny)) {
					visited[ny][nx] = true;
					queue.add(new Pos(nx, ny, map[ny][nx], time + 1));
				}
			}
		}
	}

	private static boolean checkNextTunnel(int d, int nx, int ny) {
		int tType = map[ny][nx];
		switch (d) { // 이동할 방향
		case 0: // 상
			if (tType == 3 || tType == 4 || tType == 7)
				return false;
			break;
		case 1: // 하
			if (tType == 3 || tType == 5 || tType == 6)
				return false;
			break;
		case 2: // 좌
			if (tType == 2 || tType == 6 || tType == 7)
				return false;
			break;
		case 3: // 우
			if (tType == 2 || tType == 4 || tType == 5)
				return false;
			break;
		}

		return true;
	}

	private static Object caseCnt() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j])
					cnt++;
			}
		}
		return cnt;
	}

	private static void initMove() {
		moveX = new int[8][4];
		moveY = new int[8][4];
//		상하좌우
//		x : 0, 0, -1, 1
//		y : -1, 1, 0, 0
		moveX[1][0] = 0;
		moveX[1][1] = 0;
		moveX[1][2] = -1;
		moveX[1][3] = 1;
		moveY[1][0] = -1;
		moveY[1][1] = 1;
		moveY[1][2] = 0;
		moveY[1][3] = 0;

//		상하
		moveX[2][0] = 0;
		moveX[2][1] = 0;
		moveY[2][0] = -1;
		moveY[2][1] = 1;

//		좌우
		moveX[3][2] = -1;
		moveX[3][3] = 1;
		moveY[3][2] = 0;
		moveY[3][3] = 0;

//		상우
		moveX[4][0] = 0;
		moveX[4][3] = 1;
		moveY[4][0] = -1;
		moveY[4][3] = 0;

//		하우
		moveX[5][1] = 0;
		moveX[5][3] = 1;
		moveY[5][1] = 1;
		moveY[5][3] = 0;
//		하좌

		moveX[6][1] = 0;
		moveX[6][2] = -1;
		moveY[6][1] = 1;
		moveY[6][2] = 0;

//		상좌
		moveX[7][0] = 0;
		moveX[7][2] = -1;
		moveY[7][0] = -1;
		moveY[7][2] = 0;
	}
}
