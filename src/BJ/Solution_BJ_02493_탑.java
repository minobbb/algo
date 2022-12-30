package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_BJ_02493_탑 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<int[]> stack = new Stack<>();
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());
			// 신호를 받을 왼쪽 탑이 있을 때
			while (!stack.isEmpty()) {
				if (stack.peek()[1] >= height) {
					sb.append(stack.peek()[0] + " ");
					break;
				}
				stack.pop();
			}
			// 신호를 받을 왼쪽 탑이 없을 때
			if (stack.isEmpty())
				sb.append("0 ");

			stack.push(new int[] { i, height });
		}
		System.out.println(sb);
	}
}
