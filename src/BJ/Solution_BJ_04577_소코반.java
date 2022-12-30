package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

//11856kb, 80ms
public class Solution_BJ_04577_소코반 {
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static Map<Character, Integer> dir = new HashMap<Character, Integer>() {{
			put('U', 0);
			put('D', 1);
			put('L', 2);
			put('R', 3);
	}};
	
	static int H, W;
	static Pos cur;
	static List<Pos> target;
	static char[][] map;
	static char[] commends;
	static int[] dx = { 0, 0, -1, 1 }; // UDLR(상하좌우)
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			if (H == 0 && W == 0)
				break;
			
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				map[i] = in.readLine().toCharArray();
			}
			commends = in.readLine().toCharArray();

			target = new ArrayList<>(); // 목표점 초기화
			
			sb.append("Game ").append(tc++).append(":");
			if (sokoban())
				sb.append(" complete").append("\n");
			else
				sb.append(" incomplete").append("\n");
			for (int i = 0; i < H; i++) {
				sb.append(map[i]).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean sokoban() {
		init(); // 캐릭터의 위치, 목표점의 위치 찾기
		
		for (char commend: commends) {
			move(dir.get(commend));
			
			if (checkTarget()) { // 모든 목표점에 박스가 올라가 있다면 게임 종료
				return true;
			}
		}
		return false;
	}


	private static void move(int commend) {
		int x = cur.x; // 캐릭터의 현재 좌표
		int y = cur.y;
		int nx = x + dx[commend]; // 캐릭터가 움직여야 할 좌표
		int ny = y + dy[commend];
		int mx = nx + dx[commend]; // 박스가 움직여야 할 좌표
		int my = ny + dy[commend];
		boolean isMove = true; // 캐릭터가 움직였는지 체크

		if (map[ny][nx] == '.') { // 빈 공간
			moveCharacter(x, y);
			map[ny][nx] = 'w';
		}
		
		else if (map[ny][nx] == '+') { // 목표점
			moveCharacter(x, y);
			map[ny][nx] = 'W';
		}
		
		else if (map[ny][nx] == '#') { // 벽
			// 가만히 있기
			isMove = false;
		}
		
		else if (map[ny][nx] == 'b') { // 박스
			if (map[my][mx] == '.') { // 박스를 빈 공간으로 밀 수 있다면
				moveCharacter(x, y);
				map[my][mx] = 'b';
				map[ny][nx] = 'w';
			}
			
			else if (map[my][mx] == '+') { // 박스를 목표점으로 밀 수 있다면
				moveCharacter(x, y);
				map[my][mx] = 'B';
				map[ny][nx] = 'w';
			}
			
			else if (map[my][mx] == 'b' || map[my][mx] == 'B' || map[my][mx] == '#'){ // 박스를 밀 수 없다면
				isMove = false;
			}
		}
		
		else if (map[ny][nx] == 'B') { // 목표점 위에 있는 박스
			if (map[my][mx] == '.') { // 박스를 빈 공간으로 밀 수 있다면
				moveCharacter(x, y);
				map[my][mx] = 'b';
				map[ny][nx] = 'W';
			}
			
			else if (map[my][mx] == '+') { // 박스를 목표점으로 밀 수 있다면
				moveCharacter(x, y);
				map[my][mx] = 'B';
				map[ny][nx] = 'W';
			}
			
			else if (map[my][mx] == 'b' || map[my][mx] == 'B' || map[my][mx] == '#'){ // 박스를 밀 수 없다면
				isMove = false;
			}
		}
		
//		else if (map[ny][nx] == 'b' || map[ny][nx] == 'B') { // 박스
//			
//			if (map[my][mx] == '.' || map[my][mx] == '+') { // 박스를 밀 수 있다면
//				moveCharacter(x, y);
//				
//				if (map[ny][nx] == 'B') { // 목표점 위에 있는 박스
//					map[ny][nx] = 'W'; // 캐릭터는 목표점으로 이동
//					if (map[my][mx] == '+') { // 박스가 움직일 공간이 목표점이면
//						map[my][mx] = 'B';
//					}
//					else { // 박스가 움직일 공간이 빈 공간이면
//						map[my][mx] = 'b';
//					}
//				}
//				else { // 목표점 위에 있지 않는 박스
//					map[ny][nx] = 'w'; // 캐릭터 빈공간으로 이동
//					if (map[my][mx] == '+') { // 박스가 움직일 공간이 목표점이면
//						map[my][mx] = 'B';
//					}
//					else { // 박스가 움직일 공간이 빈 공간이면
//						map[my][mx] = 'b';
//					}
//				}
//			}
//			
//			else { // 박스를 밀 수 없다면
//				isMove = false;
//			}
//		}
		
		// 움직였다면 캐릭터 위치 갱신
		if (isMove) cur = new Pos(nx, ny);
	}

	private static void init() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'W' || map[i][j] == 'B' || map[i][j] == '+')
					target.add(new Pos(j, i)); // 목표점 target리스트에 추가
				if (map[i][j] == 'W' || map[i][j] == 'w')
					cur = new Pos(j, i);
			}
		}
	}
	
	private static boolean checkTarget() {
		for(Pos pos: target) {
			if (map[pos.y][pos.x] != 'B')
				return false;
		}
		return true;
	}
	
	private static void moveCharacter(int x, int y) {
		if (map[y][x] == 'W') map[y][x] = '+'; // 캐릭터가 목표점에 있다면 현재 좌표에 목표점 표시해주기
		else map[y][x] = '.';
	}
}
