package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_JW_1681_해밀턴순환회로_dijkstra만구현하고망함 {

	static class Vertex implements Comparable<Vertex> {
		int no, minDistance;

		public Vertex(int no, int minDistance) {
			super();
			this.no = no;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex v) {
			return this.minDistance - v.minDistance;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dijkstra
		int[] distance = new int[N];
		boolean[] visited = new boolean[N];
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0; // 시작점
		pQueue.offer(new Vertex(0, distance[0])); // 시작점
		
		while (!pQueue.isEmpty()) {
			Vertex cur = pQueue.poll(); // 최소 비용을 가진 정점
		
			if (visited[cur.no]) continue;
			visited[cur.no] = true;
			
			for (int i = 0; i < N; i++) {
				if (!visited[i] && map[cur.no][i] != 0 && distance[i] > distance[cur.no] + map[cur.no][i]) {
					distance[i] = distance[cur.no] + map[cur.no][i];
					pQueue.offer(new Vertex(i, distance[i]));
				}
			}
		}
		System.out.println(Arrays.toString(distance));
	}

}
