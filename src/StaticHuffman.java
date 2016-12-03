import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class StaticHuffman {
	
	private int CHARS[] = new int[257];
	private boolean vis[] = new boolean[257];
	private	String Data = "";
	private Vector<Node> nodes = new Vector<Node>();
	private Node root = new Node((char)350);
	
	private void sort(){
		Vector<Node> temp = new Vector<Node>();
		Node n;
		while(!nodes.isEmpty()){
			n=nodes.get(0);
			for(int i=1;i<nodes.size();++i)
				if(nodes.get(i).freq < n.freq){
					n=nodes.get(i);
				}
			nodes.remove(n);
			temp.add(n);
		}
		nodes=temp;	
	}
	
	private void CODE(Node n , String s){
		if(n!=null){
			n.code=s;
			CODE(n.left,s+"0");
			CODE(n.right,s+"1");
			
		}
	}
	private String find(Node n , char c){
		Queue<Node> x = new LinkedList<Node>();
		x.add(n);
		while(!x.isEmpty()){
			Node r = x.remove();
			if(r.symbol == c)
			return r.code;
			if(r.left!=null)x.add(r.left);
			if(r.right!=null)x.add(r.right);
		}
		return "";
	}
	private void type(Node n , PrintWriter p){
		Queue<Node> x = new LinkedList<Node>();
		x.add(n);
		while(!x.isEmpty()){
			Node r = x.remove();
			if(r.symbol != 350){
				p.println(r.symbol+" "+r.code);
			}
			if(r.left!=null)x.add(r.left);
			if(r.right!=null)x.add(r.right);
		}
	}
///////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
	
	public void Compress(String Path){
		Path+=".txt";
		File file = new File(Path);
		try {
		Scanner in = new Scanner(file);
		while(in.hasNextLine())Data+=in.nextLine();
		in.close();
		} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Couldn't find file with such name");
		}
		
		
		for(int i=0;i<Data.length();++i)CHARS[Data.charAt(i)]++;
				
		for(int i=0;i<256;++i)
				if(CHARS[i]>0)nodes.add(new Node((double)CHARS[i]/Data.length(),(char)i));
		
		
		
		while(nodes.size()>2){
			sort();
			Node n = new Node();
			n.symbol=350;
			n.right=nodes.get(1);
			n.left=nodes.get(0);
			n.freq=nodes.get(1).freq+nodes.get(0).freq;
			nodes.add(n);
			nodes.remove(n.right);
			nodes.remove(n.left);
		}
		root.left=nodes.get(0);
		root.right=nodes.get(1);
		
		CODE(root,"");
		
		File f = new File("z-"+Path);
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter pw = new PrintWriter(f);
			type(root, pw);
			for(int i=0;i<Data.length();++i)pw.print(find(root,Data.charAt(i)));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	///////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	public void decompress(String Path){
		Path+=".txt";
		ArrayList<String> arr = new ArrayList<String>();
		String code[] = new String[257];
		File file = new File(Path);
		
		
		
		try {
			Scanner in = new Scanner(file);
				while(in.hasNext())arr.add(in.next());
			
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		
		
		
		String data=arr.get(arr.size()-1);
		arr.remove(arr.size()-1);
		for(int i=0;i<arr.size();i+=2)
			code[arr.get(i).charAt(0)]=arr.get(i+1);
		
		ArrayList<String> dum = new ArrayList<String>();
		for(String k : code)dum.add(k);
		String original = "";
		
		int sz = 0;
		for(int i=0;i<data.length();++i){
			for(int j=i;j<data.length()+1;++j){
				if(dum.contains(data.substring(i, j))){
					original+=(char)dum.indexOf(data.substring(i, j));
					sz = j-i-1;
					i+=sz;
					break;
				}
			}
		}
		Path=Path.substring(2);
		File f = new File(Path);
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.print(original);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
