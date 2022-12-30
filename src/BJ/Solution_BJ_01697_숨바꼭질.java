package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_BJ_01697_숨바꼭질 {

	static class Pos{
		int x, cnt; // x : 현재 위치, cnt : 이동 시간(횟수)

		public Pos(int x, int cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	static boolean[] visited = new boolean[100001]; // 방문 여부
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 내 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		bfs();
	}

	private static void bfs() {
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(N, 0)); // 초기값 설정
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll(); // 현재 내 위치

			if (cur.x == K) { // 동생을 만나면 종료
				System.out.println(cur.cnt);
				return;
			}
			
			visited[cur.x] = true; // 방문처리
			
			// -1, +1, *2 저장
			int[] move = new int[3];
			move[0] = cur.x - 1;
			move[1] = cur.x + 1;
			move[2] = cur.x * 2;

			for(int nx: move) {
				
				if (nx < 0 || nx > 100000 || visited[nx]) // 범위 초과 또는 이미 방문했을 시
					continue;
				
				queue.offer(new Pos(nx, cur.cnt + 1));
			}
			
		}
		
	}
}
