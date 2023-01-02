package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사실 이거 N - 1 이 답임
// 모든 비행스케줄은 항상 연결그래프 
// 연결그래프 : a 정점에서 b 정점으로 가는 경로는 항상 존재 
// 어디서 출발하는지 명시가 되어있지 않으므로..

// 22992kb, 180ms
public class BJ_09372_상근이의여행_S4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken()); // 국가의 수
			int M = Integer.parseInt(st.nextToken()); // 비행기의 종류
			
			for (int i = 0; i < M; i++) {
				in.readLine();
			}
			System.out.println(N - 1);
		}
	}
}
