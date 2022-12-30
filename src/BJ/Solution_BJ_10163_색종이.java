package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// 17408kb, 300ms
public class Solution_BJ_10163_색종이 {

	public static class Paper {
		int x, y, w, h;

		public Paper(int x, int y, int w, int h) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int map[][] = new int[1001][1001];
		int[] result = new int[N];
		Stack<Paper> stack = new Stack<>();
		// stack에 색종이 정보 넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			stack.add(new Paper(x, y, w, h));
		}
		int idx = 0;
		// stack에서 꺼내면서 넓이 계산
		while (!stack.isEmpty()) {
			int cnt = 0;
			Paper paper = stack.pop();
//			System.out.println(paper.x + " " + paper.y + " " + paper.w + " " + paper.h);
			for (int i = 0; i < paper.h; i++) {
				for (int j = 0; j < paper.w; j++) {
					if (map[paper.y + i][paper.x + j] == 0) {
						cnt++;
						map[paper.y + i][paper.x + j] = 1;
					}
				}
			}
			result[idx++] = cnt;
		}
		
		for (int i = result.length - 1; i >= 0; i--) {
			sb.append(result[i]).append("\n");
		}
		
		System.out.println(sb);
	}
}
