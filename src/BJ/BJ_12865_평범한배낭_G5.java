package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭_G5 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		
		int[] value = new int[K + 1];
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			W[i] = Integer.parseInt(st.nextToken()); // 물건의 무게
			V[i] = Integer.parseInt(st.nextToken()); // 물건의 가치
		}
		
		for (int i = 1; i <= N; i++) {// 물건 하나씩 선택
			for(int j = K; j - W[i] >= 0; j--) { // 뒤에서부터 해야 1차원 배열로 처리 가능 (앞에서부터 하면 필요 데이터가 덮어써짐)
				// 핵심 로직
				value[j] = Math.max(value[j], value[j - W[i]] + V[i]); // 물건을 추가하는 것과 추가하지 않는 것 중 가치가 높은 경우의 수 저장
			}
		}
		
		System.out.println(value[K]);
		
	}
}
