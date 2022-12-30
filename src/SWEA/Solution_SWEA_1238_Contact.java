package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_SWEA_1238_Contact {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		final int T = 10; // 테스트 케이스 수 10
		final int size = 101; // 최대 인원 100명. (1번 ~ 100번)
		for (int tc = 1; tc <= T; tc++) {
			// input
			sb.append("#").append(tc).append(" ");
			boolean[][] matrix = new boolean[size][size]; // 인접 행렬
			boolean[] visited = new boolean[size]; // 연락 체크
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken()); // 데이터 길이
			int start = Integer.parseInt(st.nextToken()); // 시작점
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				matrix[from][to] = true; // 뱡향그래프
			}
			
			// solve
			ArrayList<Integer> person = new ArrayList<>(); // 연락할 사람의 번호 저장
			person.add(start); // 당번 번호 저장
			solve(matrix, visited, person, size);
		}
		System.out.println(sb);
	}

	private static void solve(boolean[][] matrix, boolean[] visited, ArrayList<Integer> person, int size) { // matrix : 인접행렬, visited : 연락받은 번호 체크,
																											// person : 연락할 번호 리스트, size : 행렬 크기 size * size
		for (int p : person) { // 연락한 번호 체크
			visited[p] = true;
		}

		ArrayList<Integer> temp = new ArrayList<>(); // 다음에 연락을 수신할 번호를 저장하는 리스트
		for (int p : person) { // 연락을 보내는 번호가 연락을 받는 것이 가능하면서, 아직 연락을 받지 못한 번호를 찾으면 temp에 추가해준다.
			for (int i = 1; i < size; i++) {
				if (matrix[p][i] && !visited[i]) { // 연락을 받는 것이 가능하면서, 아직 연락을 받지 못한 경우
					temp.add(i);
				}
			}
		}

		if (temp.isEmpty()) { // 더 이상 다음에 연락할 번호가 없는 경우
			sb.append(maxValue(person)).append("\n");
			return;
		}
		
		solve(matrix, visited, temp, size);
	}

	private static int maxValue(ArrayList<Integer> numbers) { // 최대값 찾기
		int max = 0;
		for(int n: numbers) {
			if (max < n)
				max = n;
		}
		return max;
	}
}
