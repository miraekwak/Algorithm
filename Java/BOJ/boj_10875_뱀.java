package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class boj_10875_뱀 {

	static int L, N;
	static List<Black> blacks;
	static int[] times;
	static char[] direction;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static class Black {
		int r, c;
		int wsize, hsize;
		public Black(int r, int c, int wsize, int hsize) {
			this.r = r;
			this.c = c;
			this.wsize = wsize;
			this.hsize = hsize;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		times = new int[N];
		direction = new char[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			direction[i] = st.nextToken().charAt(0);
		}
		blacks = new ArrayList<Black>();
		System.out.println(getDieTime());
	}
	
	public static long getDieTime() {
		long total = 0;
		int d = 0;
		int r= L, c= L;
		int edge_r =r+dr[d], edge_c =c+dc[d];
		int nr, nc;
		blacks.add(new Black(r, c, 1, 1));
		for(int idx = 0; idx < N; idx++) {
			nr = r + dr[d]*times[idx];
			nc = c + dc[d]*times[idx];				

			long diff = isTouchSnake(edge_r, edge_c, nr, nc, d);
			if(diff != -1) return total + diff;
			diff = isInRange(edge_r, edge_c, nr, nc, d);
			if(diff != -1) return total + diff;
			
			total+= times[idx];		
			
			if(direction[idx] == 'L') {
				d--;
				if(d < 0) d = 3;
			} 
			else if(direction[idx] == 'R') {
				d = (d+1) % 4;					
			}
			
			if(edge_r == nr) {
				if(edge_c < nc) blacks.add(new Black(edge_r, edge_c, nc-edge_c+1, 1));
				else blacks.add(new Black(nr, nc, edge_c-nc+1, 1));
			}
			else if(edge_c == nc) {
				if(edge_r < nr) blacks.add(new Black(edge_r, edge_c, 1, nr-edge_r+1));
				else blacks.add(new Black(nr, nc, 1, edge_r-nr+1));
			}
			edge_r = nr + dr[d];
			edge_c = nc + dc[d];
			
			r = nr;
			c = nc;
		}
		nr = r + dr[d]*(2*L+1);
		nc = c + dc[d]*(2*L+1);
		long diff = isTouchSnake(edge_r, edge_c, nr, nc, d);
		if(diff != -1) return total + diff;
		diff = isInRange(edge_r, edge_c, nr, nc, d);
		return total + diff;
	}
	
	public static long isTouchSnake(int r, int c, int nr, int nc, int d) {
		long diff = (2*L+1);
		for(int i=blacks.size()-1; i >= 0; i--) {
			Black black = blacks.get(i);
			if(d == 0) {
				if(black.r <= r && nr <= black.r + black.hsize-1) {
					if(c <= black.c && black.c + black.wsize-1 <= nc) {
						diff = Math.min(diff, black.c - c);
					}
				}
			}
			else if(d == 1) {
				if(black.c <= c && nc <= black.c + black.wsize-1) {
					if(r <= black.r && black.r + black.hsize-1 <= nr) {
						diff = Math.min(diff, black.r - r);
					}
				}
			}
			else if(d == 2) {
				if(black.r <= nr && r <= black.r + black.hsize-1) {
					if(nc <= black.c && black.c + black.wsize-1 <= c) {
						diff = Math.min(diff, c - (black.c + black.wsize -1));
					}
				}
			}
			else if(d == 3) {
				if(black.c <= nc && c <= black.c + black.wsize-1) {
					if(nr <= black.r && black.r + black.hsize-1 <= r) {
						diff = Math.min(diff, r - (black.r + black.hsize -1));
					}
				}
			}
		}
		return diff == (2*L+1) ? -1 : diff + 1;
	}
	
	public static long isInRange(int r, int c, int nr, int nc, int d) {
		if(nr >= 0 && nr < (L*2+1) && nc >= 0 && nc < (L*2+1)) return -1;

		if(d == 0) {
			return L*2 - c +2;
		}
		else if(d == 1) {
			return L*2 - r +2;
		}
		else if(d == 2) {
			return c +2;
		}
		else {
			return r +2;
		}
	}
}

