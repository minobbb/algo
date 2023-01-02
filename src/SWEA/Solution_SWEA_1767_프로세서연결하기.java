package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_SWEA_1767_프로세서연결하기 {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Pos> list;
	static int N, maxCore, minLength;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) { // 테두리에 위치하지 않은 코어 리스트에 추가
						if (i > 0 && i < N - 1 && j > 0 && j < N - 1)
							list.add(new Pos(j, i));
					}
				}
			}
			maxCore = 0;
			minLength = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(minLength).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int idx, int coreCnt, int len) {
		if (idx == list.size()) {
			if (maxCore < coreCnt) {
				maxCore = coreCnt;
				minLength = len;
			} else if (maxCore == coreCnt) {
				minLength = Math.min(minLength, len);
			}
			return;
		}

		int x = list.get(idx).x;
		int y = list.get(idx).y;
		for (int i = 0; i < 4; i++) {
			int count = 0;
			int nx = x;
			int ny = y;

			while (true) {
				nx += dx[i];
				ny += dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					break;
				if (map[ny][nx] == 1) {
					count = 0;
					break;
				}
				count++;
			}
			
			int fillX = x;
			int fillY = y;
			
			for (int j = 0; j < count; j++) {
				fillX += dx[i];
				fillY += dy[i];
				map[fillY][fillX] = 1;
			}
			
			if (count == 0)
				dfs(idx + 1, coreCnt, len);
			else
				dfs(idx + 1, coreCnt + 1, len + count);
			fillX = x;
			fillY = y;
			for (int j = 0; j < count; j++) {
				fillX += dx[i];
				fillY += dy[i];
				map[fillY][fillX] = 0;
			}
		}

	}
}
