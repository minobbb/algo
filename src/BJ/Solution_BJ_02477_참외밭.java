package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_BJ_02477_참외밭 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K = Integer.parseInt(in.readLine());
		int maxSquareSize = 0;
		int total = 0;
		// solve
		st = new StringTokenizer(in.readLine());
		st.nextToken(); // 방향 미사용
		int pre = Integer.parseInt(st.nextToken()); // 변의 길이
		int temp = pre;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(in.readLine());
			st.nextToken(); // 방향 미사용
			int next = Integer.parseInt(st.nextToken());
			maxSquareSize = Math.max(pre * next, maxSquareSize); // 큰 사각형 넓이
			total += pre * next;
			pre = next;
		}
		maxSquareSize = Math.max(pre * temp, maxSquareSize); // 큰 사각형 넓이
		total += pre * temp;
		
		// total = 큰 사각형 넓이 * 2 + 육각형 넓이
		// total - 큰 사각형 넓이 * 2 = 육각형 넓이
		System.out.println((total - maxSquareSize * 2) * K);
		
	}
}
