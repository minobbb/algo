package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2차원 배열로 풀다가 방법을 모르겠어서 구선생님 도움 받아 품.
// 방문처리의 여러가지 경우의 수가 붙는다면 2차원이 아닌 3차원으로 선언하여 풀자.
// 147952kb 724ms

public class BJ_02206_벽부수고이동하기_G3 {
	
	static class Point {
		int x, y, dist, breakWall; // x, y : 좌표, dist : 이동거리, breakWall : 벽을 부쉇는지 여부( 1 : 부숨, 0 : 안부숨)

		public Point(int x, int y, int dist, int breakWall) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.breakWall = breakWall;
		}
	}

	static int N, M;
	static int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][][] visited; // 벽을 부수고 이동하는 경우와 벽을 부수지 않고 이동하는 경우. 2가지를 사용하기 위해 3차원 배열 선언

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][2]; // 인덱스 0 : 벽을 부수지 않고 이동, 인덱스 1 : 벽을 부수고 이동
		
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		bfs();
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 1, 0));
		visited[0][0][0] = true; // 출발지 방문처리
		
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if (cur.x == M - 1 && cur.y == N - 1) { // 목표지점 도착
				System.out.println(cur.dist);
				return;
			}
			
			for (int i = 0; i < 4; i++) { // 4방 탐색
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx < 0 || nx >= M || ny < 0 || ny >= N) // 범위를 벗어났다면
					continue;
				
				if (map[ny][nx] == 0) { // 길일때
					if (!visited[ny][nx][cur.breakWall]) { // 방문하려는 곳을 방문하지 않았다면.
						queue.offer(new Point(nx, ny, cur.dist + 1, cur.breakWall));
						visited[ny][nx][cur.breakWall] = true; // 방문처리
					}
				}
				
				else { // 벽일때
					if (cur.breakWall == 0 && !visited[ny][nx][1]) { // 벽을 부순적 없고, 벽을 부숴서 방문한 적이 없다면
						queue.offer(new Point(nx, ny, cur.dist + 1, cur.breakWall + 1)); // breakWall + 1로 벽을 부순 것을 처리
						visited[ny][nx][1] = true; // 방문처리
					}
				}
 			}
		}
		System.out.println(-1);
	}
}
