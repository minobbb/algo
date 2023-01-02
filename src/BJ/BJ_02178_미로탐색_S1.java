package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 12308kb, 88ms
public class BJ_02178_미로탐색_S1 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs();
		
		System.out.println(map[N - 1][M - 1]);
	}
	
	private static void bfs() {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(0, 0)); // 시작점 설정
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll(); // 큐에서 좌표 꺼내기
			visited[cur.y][cur.x] = true; // 방문처리

			if (cur.x == M - 1 && cur.y == N - 1) // 종료 조건. (N, M) 에 도달
				return;

			for (int i = 0; i < 4; i++) { // 4방 탐색
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 1 && visited[ny][nx] == false) { // 갈 수 있는 길
					map[ny][nx] = map[cur.y][cur.x] + 1; // 이동거리 + 1 
					queue.offer(new Point(nx, ny)); // 큐에 좌표 넣기
				}
			}
		}
	}
}
