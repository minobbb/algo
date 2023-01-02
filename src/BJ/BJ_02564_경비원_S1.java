package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02564_경비원_S1 {

	static int r, c, len;
	static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine());
		c = Integer.parseInt(st.nextToken()); // 세로 (x축)
		r = Integer.parseInt(st.nextToken()); // 가로 (y축)
		int n = Integer.parseInt(in.readLine()); // 상점 개수
		len = (r + c) * 2;
		// input
		int[] loc = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			loc[i] = location(st.nextToken(), Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(in.readLine());
		int myLoc = location(st.nextToken(), Integer.parseInt(st.nextToken()));

		for (int i = 0; i < n; i++) {
			calcLocation(myLoc, loc[i]);
		}
		System.out.println(sum);
	}
	
	// 사각형의 4 변을 북-동-남-서 순서로 직선으로 펼친 후, 직선에 대한 좌표 리턴.
	private static int location(String dir, int pos) {
		switch (dir) {
		case "1": // 북쪽
			return pos;

		case "2": // 남쪽
			return c + r + (c - pos);

		case "3": // 서쪽
			return c + r + c + (r - pos);

		case "4": // 동쪽
			return c + pos;
		}
		return -1;
	}
		
	// 바로 가는 거리와 돌아가는 거리 중 짧은 거리를 sum에 더함
	private static void calcLocation(int myLoc, int loc) {
		int distance1 = Math.abs(myLoc - loc); // 바로 가는 거리
		int distance2 = (myLoc > loc) ? (len - myLoc + loc) : (len - loc + myLoc); // 돌아가는 거리
		sum += distance1 > distance2 ? distance2 : distance1; 
	}
}
