package programmers;


public class PGMS_퍼즐조각채우기 {
	
	public static int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(
				solution(
						new int[][]{
								{1,1,0,0,1,0},
								{0,0,1,0,1,0},
								{0,1,1,0,0,1},
								{1,1,0,1,1,1},
								{1,0,0,0,1,0},
								{0,1,1,1,0,0}
						}, 
						new int[][]{
								{1,0,0,1,1,0},
								{1,0,1,0,1,0},
								{0,1,1,0,1,1},
								{0,0,1,0,0,0},
								{1,1,0,1,1,0},
								{0,1,0,0,0,0}
						}
				));
	}

}
