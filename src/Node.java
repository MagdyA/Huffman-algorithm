
public class Node {
	
	double freq = 0 ;
	char symbol ;
	String code = "" ;
	Node left;
	Node right;
	
	
	Node(){}
	
	Node(char c){
		symbol=c;
	}
	
	Node(double f , char s){
		freq = f;
		symbol = s;
	}
	
	
	Node(double f , char s,Node l , Node r){
		freq = f;
		symbol = s;
		left = l;
		right = r;
	}
}
