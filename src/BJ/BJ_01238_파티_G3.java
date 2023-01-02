package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 17304kb, 172ms
public class BJ_01238_파티_G3 {

	static class Road implements Comparable<Road> {
		int index, time;

		public Road(int index, int time) {
			super();
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Road o) {
			return this.time - o.time;
		}

	}
	
	static int N, M, X;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 마을의 수 (정점의 개수)
		M = Integer.parseInt(st.nextToken()); // 도로의 수 (간선의 개수)
		X = Integer.parseInt(st.nextToken()); // 시작점

		ArrayList<Road>[] roadList = new ArrayList[N + 1];
		ArrayList<Road>[] reverseRoadList = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			roadList[i] = new ArrayList<>();
			reverseRoadList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			roadList[start].add(new Road(end, time));
			reverseRoadList[end].add(new Road(start, time));
		}

		int[] time1 = dijkstra(roadList);
		int[] time2 = dijkstra(reverseRoadList);
		
		int result = 0;
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, time1[i] + time2[i]);
		}
		System.out.println(result);
	}

	private static int[] dijkstra(ArrayList<Road>[] list) {
		PriorityQueue<Road> pq = new PriorityQueue<>();

		pq.add(new Road(X, 0));
		
		int[] times = new int[N + 1];
		Arrays.fill(times, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];
		times[X] = 0;
		
		while (!pq.isEmpty()) {
			Road road = pq.poll();
			int cur = road.index;
			int time = road.time;
			
			if (!visited[cur]) {
				visited[cur] = true;
				
				for (Road r: list[cur]) {
					if (!visited[r.index] && times[r.index] > times[cur] + r.time) {
						times[r.index] = times[cur] + r.time;
						pq.add(new Road(r.index, times[r.index]));
					}
				}
			}
		}
		return times;
	}
	
}
