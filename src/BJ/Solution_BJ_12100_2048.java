package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 108440kb, 480ms
public class Solution_BJ_12100_2048 {

	static int N, max = 0;
	static int[] dir;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		dir = new int[5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if (N == 1) {
			System.out.println(map[0][0]);
		}
		else {
			solve(0);
			System.out.println(max);
		}
	}

	private static void solve(int idx) {

		if (idx == 5) {
			findMaxNum(play(dir));
			return;
		}
	    
		for (int i = 0; i < 4; i++) { // 상하좌우(0: 상, 1: 하, 2: 좌, 3: 우)
			dir[idx] = i;
			solve(idx + 1);
		}
	}

	private static int[][] play(int[] dir) {
		int[][] playMap = copyMap();
		for (int d : dir) {
			switch (d) {
			case 0:
				playMap = moveUp(playMap);
				break;
			case 1:
				playMap = moveDown(playMap);
				break;
			case 2:
				playMap = moveLeft(playMap);
				break;
			case 3:
				playMap = moveRight(playMap);
				break;
			}
		}

		return playMap;
	}


	private static int[][] moveUp(int[][] playMap) {
		Queue<Integer> queue1 = new LinkedList<>(); // 맵에서 0을 제외한 모든 숫자 담는 큐
		int[][] nextMap = new int[N][N]; // 움직인 후의 맵
		
		// 0을 제외한 모든 숫자 담기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (playMap[j][i] != 0) {
					queue1.add(playMap[j][i]);
				}
			}
			
			// 2048 게임 진행 (움직이기)
			Queue<Integer> queue2 = move(queue1);
			
			// 움직인 후의 맵 만들기
			int idx = 0;
			while (!queue2.isEmpty()) {
				int num = queue2.poll();
				nextMap[idx++][i] = num;
			}
		}
		
		return nextMap;
	}

	private static int[][] moveDown(int[][] playMap) {
		Queue<Integer> queue1 = new LinkedList<>();
		int[][] nextMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (playMap[j][i] != 0) {
					queue1.add(playMap[j][i]);
				}
			}
			Queue<Integer> queue2 = move(queue1);
			
			int idx = N - 1;
			while (!queue2.isEmpty()) {
				int num = queue2.poll();
				nextMap[idx--][i] = num;
			}
		}
		
		return nextMap;	
	}

	private static int[][] moveLeft(int[][] playMap) {
		Queue<Integer> queue1 = new LinkedList<>();
		int[][] nextMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (playMap[i][j] != 0) {
					queue1.add(playMap[i][j]);
				}
			}
			Queue<Integer> queue2 = move(queue1);
			int idx = 0;
			while (!queue2.isEmpty()) {
				int num = queue2.poll();
				nextMap[i][idx++] = num;
			}
		}
		
		return nextMap;	
	}


	private static int[][] moveRight(int[][] playMap) {
		Queue<Integer> queue1 = new LinkedList<>();
		int[][] nextMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (playMap[i][j] != 0) {
					queue1.add(playMap[i][j]);
				}
			}
			Queue<Integer> queue2 = move(queue1);
			int idx = N - 1;
			while (!queue2.isEmpty()) {
				int num = queue2.poll();
				nextMap[i][idx--] = num;
			}
		}
		
		return nextMap;	
	}

	private static Queue<Integer> move(Queue<Integer> queue1) {
		Queue<Integer> queue2 = new LinkedList<>();
		int cur = 0;
		int next = 0;
		while (!queue1.isEmpty()) {
			if (cur == 0)
				cur = queue1.poll();
			if (!queue1.isEmpty())
				next = queue1.poll();
			
			if (cur == next) { // 숫자가 같다면
				queue2.add(cur + next);
				cur = 0;
				next = 0;
			}
			else { // 숫자가 다르다면
				queue2.add(cur);
				if (queue1.isEmpty()) { // 만약 next가 마지막 숫자라면
					queue2.add(next);
				}
				cur = next;
				next = 0;
			}
		}
		return queue2;
	}

	private static int[][] copyMap() {
		int[][] playMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				playMap[i][j] = map[i][j];
			}
		}
		return playMap;
	}
	
	private static void findMaxNum(int[][] playMap) {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (playMap[i][j] > temp) temp = playMap[i][j];
			}
		}
		max = (temp > max) ? temp : max;
	}
}
