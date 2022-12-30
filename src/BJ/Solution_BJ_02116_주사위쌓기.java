package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_BJ_02116_주사위쌓기 {

	// 주사위 마주보는 면 인덱스
	static final Map<Integer, Integer> bottomTop = new HashMap<Integer, Integer>() {
		{
			put(0, 5);
			put(1, 3);
			put(2, 4);
			put(3, 1);
			put(4, 2);
			put(5, 0);
		}
	};

	static int[][] dices;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		// input
		dices = new int[N][6];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 6; j++)
				dices[i][j] = Integer.parseInt(st.nextToken());
		}

		int result = 0;

		// solve
		for (int i = 0; i < 6; i++) {
			// 처음 주사위 초기값 설정
			int botIdx = i;
			int topIdx = bottomTop.get(i);
			int sum = getSideMaxValue(dices[0][botIdx], dices[0][topIdx]); // 맨 아래 주사위 사이드 최대값 더함.

			// 다음 주사위부터 마지막 주사위까지
			for (int j = 1; j < N; j++) {
				botIdx = nextBottomIdx(j, dices[j - 1][topIdx]); // 다음 주사위 아랫면 인덱스
				topIdx = bottomTop.get(botIdx); // 다음 주사위 윗면 인덱스
				sum += getSideMaxValue(dices[j][botIdx], dices[j][topIdx]); // 주사위 옆면의 최대값 더하기
			}
			result = (result < sum) ? sum : result; // 최대값 저장
		}
		System.out.println(result);
	}

	static int getSideMaxValue(int bottomNbr, int topNbr) { // 옆면 최대값 찾기
		
		int maxValue = 6;
		 while (bottomNbr == maxValue || topNbr == maxValue) 
			maxValue--;
		return maxValue;
	}

	static int nextBottomIdx(int cnt, int top) { // 인덱스를 가져와야 함.
		for (int i = 0; i < 6; i++) {
			if (dices[cnt][i] == top)
				return i;
		}
		return -1;
	}
}
