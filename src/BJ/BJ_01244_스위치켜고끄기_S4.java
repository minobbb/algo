package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_01244_스위치켜고끄기_S4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int[] switchs = new int[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < switchs.length; i++) {
			switchs[i] = Integer.parseInt(st.nextToken());
		}
		int c = Integer.parseInt(in.readLine());
		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(in.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			// solve
			if (gender == 1) { // 남자
				for (int j = num - 1; j < N; j += num) { // 스위치 번호는 1번부터 시작하므로 num - 1이 시작 인덱스
					switchs[j] = (switchs[j] == 1) ? 0 : 1;
				}
			}

			else if (gender == 2) { // 여자
				int idx = 1;
				switchs[num - 1] = (switchs[num - 1] == 1) ? 0 : 1; // 스위치 번호의 상태 바꿈
				// 인덱스 범위 넘지 않고, num을 기준으로 스위치 상태가 좌우대칭이면 좌우대칭인 스위치 상태 모두 변경
				while (num - 1 - idx >= 0 && num - 1 + idx < N && switchs[num - 1 - idx] == switchs[num - 1 + idx]) {
					switchs[num - 1 - idx] = (switchs[num - 1 - idx] == 1) ? 0 : 1;
					switchs[num - 1 + idx] = (switchs[num - 1 + idx] == 1) ? 0 : 1;
					idx++;
				}
			}
		}
		for (int i = 0; i < switchs.length; i++) {
			sb.append(switchs[i]).append(" ");
			if ((i + 1) % 20 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}
