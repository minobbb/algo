package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 12588kb, 116ms
public class BJ_09205_맥주마시면서걸어가기_G5 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(in.readLine()); // 편의점 개수
			int[] house = new int[2];
			int[][] stores = new int[N][2]; // 편의점 좌표
			int[] dest = new int[2];
			
			st = new StringTokenizer(in.readLine());
			house[0] = Integer.parseInt(st.nextToken());
			house[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				stores[i][0] = Integer.parseInt(st.nextToken());
				stores[i][1] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			dest[0] = Integer.parseInt(st.nextToken());
			dest[1] = Integer.parseInt(st.nextToken());
		
			if (bfs(N, house, stores, dest, new boolean[N])) {
				sb.append("happy\n");
			}
			else {
				sb.append("sad\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean bfs(int N, int[] house, int[][] stores, int[] dest, boolean[] visited) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(dest);
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (calcDist(cur, house)) {// 집과의 거리가 1000 이하면 종료
				return true;
			}
			
			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;
				if (calcDist(cur, stores[i])) { // 현재 위치에서 편의점의 거리가 1000 이하인 곳 큐 삽입
					visited[i] = true;
					queue.add(stores[i]);
				}
			}
		}
		
		return false;
	}

	private static boolean calcDist(int[] a, int[] b) {
		if (Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) <= 1000)
			return true;
		return false;
	}
}
