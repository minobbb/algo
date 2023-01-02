package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 15432kb, 524ms
public class BJ_15811_복면산_G4 {

	static char[] word1, word2, word3;
	static int[] number = new int[26];
	static boolean[] alphabet = new boolean[26];
	static boolean[] checked = new boolean[10];
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		word1 = st.nextToken().toCharArray();
		word2 = st.nextToken().toCharArray();
		word3 = st.nextToken().toCharArray();
		
		for (int i = 0; i < word1.length; i++) {
			alphabet[word1[i] - 'A'] = true;
		}
		for (int i = 0; i < word2.length; i++) {
			alphabet[word2[i] - 'A'] = true;
		}
		for (int i = 0; i < word3.length; i++) {
			alphabet[word3[i] - 'A'] = true;
		}
		
		for (int i = 0; i < 26; i++) {
			if (alphabet[i])
				list.add(i);
		}
		
		if (list.size() > 10) {
			System.out.println("NO");
			System.exit(0);
		}
		
		solve(0);
		System.out.println("NO");
		
	}
	
	private static void solve(int idx) {
		if (idx == list.size()) {
			int a = getNum(word1);
			int b = getNum(word2);
			int c = getNum(word3);
			if (a + b == c) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		
		for (int i = 0; i <= 9; i++) {
			if (checked[i])
				continue;
			checked[i] = true;
			number[list.get(idx)] = i;
			solve(idx + 1);
			checked[i] = false;
			
		}
	}
	private static int getNum(char[] word) {
		int num = 0;
		
		for (int i = 0; i < word.length; i++) {
			num = num * 10 + number[word[i] - 'A'];
		}
		return num;
	}
}
