package com.ssafy.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_JW_1828_냉장고 {

	static class Temperature implements Comparable<Temperature> {
		int min, max;

		public Temperature(int min, int max) {
			super();
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Temperature o) {
			return this.max != o.max ? this.max - o.max : this.min - o.min;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		Temperature[] temperatures = new Temperature[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			temperatures[i] = new Temperature(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// 최고온도 기준으로 오름차순, 만약 최고온도가 같다면 최저온도 기준 오름차순 정렬
		Arrays.sort(temperatures);
		// output
		System.out.println(refrigerator(temperatures));
	}

	//solve
	private static int refrigerator(Temperature[] temperatures) {
		int size = temperatures.length;
		int cnt = 1; // 냉장고 개수
		int pre = 0; // 비교인덱스
		int next = 1; // 비교인덱스
		while (true) {
			// 모든 물질 체크 완료 시 break
			if (next >= size)
				break;
			// 두 물질이 같은 냉장고에 보관 가능 (pre의 최고온도가 next의 최저온도보다 높거나 같음)
			if (temperatures[pre].max >= temperatures[next].min) {
				next++;
				continue;
			}
			// 두 물질이 같은 냉장고에 보관 불가능 (pre의 최고온도보다 next의 최저온도가 높음)
			else {
				pre++;
				next++;
				cnt++;
			}
		}
		return cnt;
	}
}
