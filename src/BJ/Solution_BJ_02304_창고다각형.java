package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_BJ_02304_창고다각형 {

	// 기둥의 l, h를 포함한 클래스. Comparable 인터페이스를 상속받아 l를 기준으로 오름차순 정렬이 가능하게 함.
	static class Pillar implements Comparable<Pillar>{
		int l, h;

		public Pillar(int l, int h) {
			super();
			this.l = l;
			this.h = h;
		}
		@Override
		public int compareTo(Pillar o) {
			return this.l - o.l;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		Pillar[] pillar = new Pillar[N];
		for (int i = 0; i < pillar.length; i++) {
			st = new StringTokenizer(in.readLine());
			pillar[i] = new Pillar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		// l를 기준으로 오름차순 정렬
		Arrays.sort(pillar);

		solve(pillar);
	}
	// solve
	private static void solve(Pillar[] pillar) {

		int size = 0; // 결과값
		// 왼쪽에서 오른쪽으로 기둥이 높아질때마다 size 에 면적 더해주기
		int leftHeight = pillar[0].h;
		int leftWidth = pillar[0].l;
		for (int i = 1, len = pillar.length; i < len; i++) {
			if (leftHeight < pillar[i].h) {
				size = size + (pillar[i].l - leftWidth) * leftHeight;
				leftHeight = pillar[i].h;
				leftWidth = pillar[i].l;
			}
		}
		// 오른쪽에서 왼쪽으로 기둥이 높아질때마다 size에 면적 더해주기
		int rightHeight = pillar[pillar.length - 1].h;
		int rightWidth = pillar[pillar.length - 1].l;
		for (int i = pillar.length - 2; i >= 0; i--) {
			if (rightHeight < pillar[i].h) {
				size = size + (rightWidth - pillar[i].l) * rightHeight;
				rightHeight = pillar[i].h;
				rightWidth = pillar[i].l;
			}
		} 
		// 왼쪽에서 오른쪽, 오른쪽에서 왼쪽으로 탐색하면 제일 높은 높이에 도달하게 된다.
		// 기둥의 높이가 가장 높을때의 면적을 size에 더해주는 구문이다.
		// leftHeight = rightHeight 이고, 둘 중 아무거나 연산에 넣어도 상관 없다.
		// 기둥의 폭이 1 이기 때문에 rightWidth + 1
		size = size + ((rightWidth + 1) - leftWidth) * rightHeight;
		System.out.println(size);
	}
}
