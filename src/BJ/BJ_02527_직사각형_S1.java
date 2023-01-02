package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02527_직사각형_S1 {

	static class square {
		int x;
		int y;
		int p;
		int q;
		
		public square(int x, int y, int p, int q) {
			super();
			this.x = x;
			this.y = y;
			this.p = p;
			this.q = q;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 4;
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			square s1 = new square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			square s2 = new square(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			if (s1.p < s2.x || s1.q < s2.y || s1.x > s2.p || s1.y > s2.q) {	// 공통 범위 없음
				sb.append("d\n");
			}
			else if ((s1.x == s2.p && s1.y == s2.q) || (s1.p == s2.x && s1.q ==  s2.y) || (s1.x == s2.p && s1.q == s2.y) || (s1.p == s2.x && s1.y == s2.q)) { // 공통 범위 점
				sb.append("c\n");
			}
			else if ((s1.x == s2.p && s1.y != s2.q) || (s1.p == s2.x && s1.q != s2.y) || (s1.y == s2.q && s1.x != s2.p) || (s1.q == s2.y && s1.p != s2.x)) { // 공통 범위 선
				sb.append("b\n");
			}
			else { // 공통범위 직사각형
				sb.append("a\n");
			}
		}
		System.out.println(sb);
	}
}
