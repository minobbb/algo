package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

// 23508kb, 928ms
public class Solution_BJ_15811_복면산_map {

	static String[] word = new String[3];
	static char[] alphabet;
	static Map<Character, Integer> map = new HashMap<>();
	static boolean[] checked = new boolean[10]; // 숫자 사용 여부 체크
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < 3; i++) {
			word[i] = st.nextToken();
			for (int j = 0; j < word[i].length(); j++) {
				set.add(word[i].charAt(j));
			}
		}
		// set -> char[] 로 데이터 옮기기
		alphabet = new char[set.size()];
		Iterator<Character> iter = set.iterator();
		int idx = 0;
		while(iter.hasNext()) {
			alphabet[idx++] = iter.next();
		}
		
		if (alphabet.length > 10) { // 알파벳 개수가 10보다 크면 (알파벳에 숫자가 매칭이 안됨)
			System.out.println("NO");
			System.exit(0);
		}
		
		solve(0);
		System.out.println("NO");
	}

	private static void solve(int idx) {
		if (idx == alphabet.length) {
			calc();
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			if (checked[i]) // 숫자 중복 체크 (서로 다른 숫자)
				continue;
			checked[i] = true;
			map.put(alphabet[idx], i);
			solve(idx + 1);
			checked[i] = false;
		}
	}

	private static void calc() {
		int a = 0;
		int b = 0;
		int c = 0;
		
		for (int i = 0; i < word[0].length(); i++) {
			a = a * 10 + map.get(word[0].charAt(i));
		}
		for (int i = 0; i < word[1].length(); i++) {
			b = b * 10 + map.get(word[1].charAt(i));
		}
		for (int i = 0; i < word[2].length(); i++) {
			c = c * 10 + map.get(word[2].charAt(i));
		}
//		System.out.println(a + " + " + b + " = " + c);
		if (a + b == c) {
			System.out.println("YES");
			System.exit(0);
		}
	}
}
