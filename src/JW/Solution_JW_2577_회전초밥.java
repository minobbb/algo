package JW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 53604kb, 465ms
public class Solution_JW_2577_회전초밥 {

	static int N, d, k, c, max;
	static int[] sushi, pick;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 회전초밥 벨트에 놓인 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		max = 0;
		sushi = new int[N];
		pick = new int[d + 1];
		for (int i = 0; i < N; i++) {
			int s = Integer.parseInt(in.readLine());
			sushi[i] = s;
		}

		solve();
		System.out.println(max);
	}

	private static void solve() {
		// 초기값 설정
		int cnt = 0;
		for (int i = 0; i < k; i++) { 
			if (pick[sushi[i]] == 0)
				cnt++;
			pick[sushi[i]]++;
		}
		pick[c]++;
		if (pick[c] == 1) {
			cnt++;
		}
		max = cnt;
		// 왼쪽에서 하나 빼고 오른쪽에 하나 추가
		for (int i = 0; i < N - 1; i++) {
			if (max == k + 1) // 최대값이면 종료
				return;
			
			// 왼쪽에서 하나 빼기
			pick[sushi[i]]--; 
			if (pick[sushi[i]] == 0) cnt--;
			
			 // 오른쪽에서 하나 추가
			if (i + k > N - 1) { // 범위 초과 시
				pick[sushi[i + k - N]]++;
				if (pick[sushi[i + k - N]] == 1) cnt++;
			}
			else {
				pick[sushi[i + k]]++;
				if (pick[sushi[i + k]] == 1) cnt++;
			}
			
			max = Math.max(max, cnt);
		}
	}
}
