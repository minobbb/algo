package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 방문 체크 O : 11664kb, 76ms
// 방문 체크 X : 91752kb, 352ms
public class BJ_13460_구슬탈출2_G1 {

	static class Pos {
		int rx, ry, bx, by, cnt;

		public Pos(int rx, int ry, int bx, int by, int cnt) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static char map[][];
	static boolean[][][][] visited;
	static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int rx = 0, ry = 0, bx = 0, by = 0;
		visited = new boolean[N][M][N][M];
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'R') {
					rx = j;
					ry = i;
				}
				else if (map[i][j] == 'B') {
					bx = j;
					by = i;
				}
			}
		}
		
		System.out.println(bfs(new Pos(rx, ry, bx, by, 1)));

	}

	private static int bfs(Pos pos) {
		Queue<Pos> queue = new LinkedList<>();
		visited[pos.ry][pos.rx][pos.by][pos.bx] = true;
		queue.add(pos);
		
		boolean goalRed = false;
		boolean goalBlue = false;
		
		while(!queue.isEmpty()) {
			Pos cur = queue.poll();
			if (cur.cnt > 10) // 이동횟수 10회 초과시
				return -1;
			
			for (int d = 0; d < 4; d++) {				
				goalRed = false;
				goalBlue = false;

				int nrx = cur.rx;
				int nry = cur.ry ;
				while (map[nry + dy[d]][nrx + dx[d]] != '#') { // 빨간 구슬이 벽을 만날때까지 이동
					nrx += dx[d];
					nry += dy[d];
					if (map[nry][nrx] == 'O') { // 빨간 구슬이 구멍을 만났다면
						goalRed = true;
						break;
					}
				}
				
				int nbx = cur.bx;
				int nby = cur.by;
				while (map[nby + dy[d]][nbx + dx[d]] != '#') { // 파란 구슬이 벽을 만날때까지 이동
					nbx += dx[d];
					nby += dy[d];
					if (map[nby][nbx] == 'O') { // 파란 구슬이 구멍을 만났다면
						goalBlue = true;
						break;
					}
				}
				
				if (goalBlue) { // 파란구슬이 구멍에 들어가면 실패.
					continue;
				}
				
				if (goalRed) { // 빨간 구슬이 구멍에 들어가면 성공.
					return cur.cnt;
				}
				
				if (nrx == nbx && nry == nby) { // 구슬이 같은 위치에 위치할 경우 위치 조정
					if (d == 0) { // 상
						if (cur.ry > cur.by) nry -= dy[d];
						else nby -= dy[d];
					}
					else if (d == 1) { // 하
						if (cur.ry > cur.by) nby -= dy[d];
						else nry -= dy[d];
					}
					else if (d == 2) { // 좌
						if (cur.rx > cur.bx) nrx -= dx[d];
						else nbx -= dx[d];
					}
					else if (d == 3) { // 우
						if (cur.rx > cur.bx) nbx -= dx[d];
						else nrx -= dx[d];
					}
				}
				if (!visited[nry][nrx][nby][nbx]) {
					visited[nry][nrx][nby][nbx] = true;
					queue.add(new Pos(nrx, nry, nbx, nby, cur.cnt + 1));
				}
			}
		}
		return -1;
	}

}
