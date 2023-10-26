package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1991_트리순회 {
	
	static StringBuilder sb;
	
	static class Node {
		char key;
		Node left, right;
		public Node(char key, Node left, Node right) {
			this.key = key;
			this.left = left;
			this.right = right;
		}
	}
		
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Node root = new Node(st.nextToken().charAt(0), null, null);
		insert(root, root.key, st.nextToken().charAt(0), st.nextToken().charAt(0));
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			insert(root, st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}
		
		sb = new StringBuilder();
		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);
		System.out.println(sb);
	}
	
	public static void insert(Node curr, char parent, char left, char right) {
		if(curr.key == parent) {
			curr.left = (left == '.'? null : new Node(left, null, null));
			curr.right = (right == '.'? null : new Node(right, null, null));
			return;
		}
		if(curr.left != null) insert(curr.left, parent, left, right);
		if(curr.right != null) insert(curr.right, parent, left, right);
	}
	
	public static void preOrder(Node node) {
		if(node == null) return;
		sb.append(node.key);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public static void inOrder(Node node) {
		if(node == null) return;
		inOrder(node.left);
		sb.append(node.key);
		inOrder(node.right);
	}
	
	public static void postOrder(Node node) {
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.key);
	}
}
