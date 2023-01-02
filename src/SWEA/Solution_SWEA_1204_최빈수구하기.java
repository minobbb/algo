package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1204_최빈수구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			in.readLine();
			int[] mode = new int[101]; // 0~100점
			int max = 0;
			int result = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			while (st.hasMoreTokens()) {
				mode[Integer.parseInt(st.nextToken())]++;
			}
			for (int i = 0; i < mode.length; i++) {
				if (mode[i] >= max) {
					max = mode[i];
					result = i;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}