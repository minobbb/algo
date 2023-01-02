package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 11600kb, 80ms
public class BJ_02628_종이자르기_S5 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int width = Integer.parseInt(st.nextToken()); // 종이의 가로 길이
		int height = Integer.parseInt(st.nextToken()); // 종이의 세로 길이
		int n = Integer.parseInt(in.readLine()); // 자르는 횟수
		
		ArrayList<Integer> rowCut = new ArrayList<>(); // 가로로 자르는 리스트
		ArrayList<Integer> colCut = new ArrayList<>(); // 세로로 자르는 리스트

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == 0) // 가로로 자름
				rowCut.add(Integer.parseInt(st.nextToken()));
			else // 세로로 자름
				colCut.add(Integer.parseInt(st.nextToken()));
		}

		// 가로와 세로의 잘린 길이 중 최대값을 계산하기 위해 추가
		rowCut.add(0);
		rowCut.add(height);
		colCut.add(0);
		colCut.add(width);

		Collections.sort(rowCut); // 오름차순으로 정렬
		Collections.sort(colCut);

		// 세로 최대값 구하기
		int maxHeight = 0;
		int pre = rowCut.get(0);
		for (int i = 1; i < rowCut.size(); i++) {
			int next = rowCut.get(i);
			if (maxHeight < next - pre) maxHeight = next - pre;
			pre = next;
		}

		// 가로 최대값 구하기
		int maxWidth = 0;
		pre = colCut.get(0);
		for (int i = 1; i < colCut.size(); i++) {
			int next = colCut.get(i);
			if (maxWidth < next - pre) maxWidth = next - pre;
			pre = next;
		}

		System.out.println(maxHeight * maxWidth);

	}
}
