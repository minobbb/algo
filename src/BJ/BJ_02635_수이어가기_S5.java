package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
// 13852kb 100ms
public class BJ_02635_수이어가기_S5 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(in.readLine());
		
		int maxCnt = 0;
		ArrayList<Integer> result = null;
		for (int i = n / 2; i <= n; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			// n과 선택한 값을 미리 넣어줌.
			int pre = n;
			int cur = i;
			temp.add(pre);
			temp.add(cur);
			int cnt = 2; // 처음에 값 2개 넣고 시작하므로 초기값 2
			while (true) {
				int next = pre - cur; // 다음에 저장할 값
				if (next < 0) break; // 음수면 break
				// 값 저장하고 pre와 cur 값 바꿔주기
				temp.add(next); 
				pre = cur;
				cur = next;
				cnt++;
			}
			if (maxCnt < cnt) { // 최대 길이 찾기
				maxCnt = cnt;
				result = temp;
			}
		}
	
		sb.append(maxCnt).append("\n");
		for (int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}
		System.out.println(sb);
		
	}
}
