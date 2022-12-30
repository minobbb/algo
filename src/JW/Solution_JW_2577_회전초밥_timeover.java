package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_JW_2577_회전초밥_timeover {

	static int N, d, k, c, max;
	static int[] sushi;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 회전초밥 벨트에 놓인 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		max = 0;
		sushi = new int[N];
		for (int i = 0; i < N; i++) {
			int s = Integer.parseInt(in.readLine());
			sushi[i] = s;
		}

		solve();
		System.out.println(max);
	}

	private static void solve() {
		for (int i = 0; i < N; i++) {
			for (int j = i; j < k + i; j++) {
				if (j >= N) {
					set.add(sushi[j - N]);
				} 
				else {
					set.add(sushi[j]);
				}
			}
			set.add(c);
			max = Math.max(max, set.size());
			if (max == k + 1)
				return;
			set.clear();
		}
	}
}
