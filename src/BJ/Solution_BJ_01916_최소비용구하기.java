package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 51296kb, 476ms
public class Solution_BJ_01916_최소비용구하기 {

	static class Pos implements Comparable<Pos>{
		int idx, dist;

		public Pos(int to, int dist) {
			super();
			this.idx = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pos o) {
			return this.dist - o.dist;
		}
	}

	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine()); // 도시의 개수
		int M = Integer.parseInt(in.readLine()); // 버스의 개수
		List<Pos>[] buses = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			buses[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			buses[from].add(new Pos(to, weight));
		}
		
		st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int dist[] = dijkstra(buses, start);
			
		System.out.println(dist[end]);
	}

	private static int[] dijkstra(List<Pos>[] buses, int start) {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.add(new Pos(start, 0));
		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if (visited[cur.idx])
				continue;
			visited[cur.idx] = true;
			
			for (Pos bus: buses[cur.idx]) {
				if (!visited[bus.idx] && dist[bus.idx] > dist[cur.idx] + bus.dist) {
					dist[bus.idx] = dist[cur.idx] + bus.dist;
					pq.add(new Pos(bus.idx, dist[bus.idx]));
				}
			}
		}
		
		return dist;
	}
}
