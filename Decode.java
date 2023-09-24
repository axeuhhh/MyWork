import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.InputMismatchException;
import java.util.Scanner;

class Tree{
	//class stores the tree and all the connections
	private class TreeNode{
		Character chr; 
		TreeNode right;
		TreeNode left;
		 
		TreeNode(){
			chr = null;
			right =null;
			left =null;
		}
	}
	TreeNode last;
	TreeNode head;
	TreeNode current;
	
	Tree(){
		head=null;
		last=null;
		current=null;
	}
	// this method is the public method that handles the 2 different private mehtods
	public void add(char c, String s) {
		if (head==null) {
			head = new TreeNode();
		}
		current=head;
		//head should be current and then have set it to head
		for (int i = 0;i<s.length();i++) {
			add(current, Character.getNumericValue(s.charAt(i)));
		}
		add(last,c);
	}
	//this method sets the value of the leaf node by setting the last node to the value
	private void add(TreeNode n, char c) {
		n.chr=c;
		
	}//method adds the other nodes to the tree
	private void add(TreeNode n, int i) {// the thing is is that when you add you have to return
		if (i == 1){	
			if (n.right == null) {
				n.right = new TreeNode();
				last=n.right;
				current = n.right;
			} 
			else {
				current = n.right;
			}
		}else {
			if(n.left == null) {
				n.left = new TreeNode();
				last=n.left;
				current = n.left;
			}
			else {
				current = n.left;
			}

		}
		
	}
//this functions searches in the tree to decode the encoded value
	public String search( String s) {
		current=head;
		Character c =0;
		String returnString="";
		//head should be current and then have set it to head
		for (int i = 0;i<s.length();i++) {
			c=search(current, Character.getNumericValue(s.charAt(i)));
			if(c!=null) {
				current=head;
				returnString+=c;
			}
		}
		if (c==null) {
			try {
				throw new InputMismatchException("There is a missing character in the in the input");
			}catch(InputMismatchException ime) {
				System.err.println(ime);
			}
		}
		return returnString;
	}

// returns a character if there is any in the leaf node and when it is eventually found, is resets the current to the head
	private Character search(TreeNode n, int i) {// the thing is is that when you add you have to return
		if (i == 1){	
			current = n.right;
			if(current.chr!=null) {
				return current.chr;
			}
			return null;
			
		}else if (i==0){//this functions sets all the number that are less than or equal top the left of the top node.
			current = n.left;
			if (current.chr!=null) {
				return current.chr;
			}
			return null;
		}
		else {
			System.err.println("There is an illegal (not 0 or 1) character in the file");
			System.exit(0);
			return 0;
		}
	}
}


public class Decode {
//this function creates the tree from the codebook.
	public static void codebook(Tree t) throws FileNotFoundException {
		File file = new File("codebook");
		Scanner scan = new Scanner(file);
		String[] sa;
		
		while(scan.hasNextLine()) {
			sa=scan.nextLine().split(":");
			t.add((char) Integer.parseInt(sa[0]), sa[1]);
		}
		scan.close();
	}
//this method scans the line from the coded file
	public static String scan(File file) throws FileNotFoundException {//handle empty file
		Scanner scan = new Scanner(file);// does it have to take in the full file or just the line from decode?
		String s="";
		if(scan.hasNextLine()) {
			s = scan.nextLine();
		}
		scan.close();
		return s;
		
	}
// it takes the coded info and writes the decoded info to a file 
	public static void output(File file,String s) throws IOException, FileNotFoundException {
		Writer w = new FileWriter(file);
		for (int i =0;i<s.length();i++) {
			if (s.charAt(i)!=(char)4) {
				w.write(s.charAt(i));
			}
		}
		w.close();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		if(args.length<2) {
			System.err.println("There are not command Line arguments"); 
			System.exit(0);
		}
		File file = new File(args[0]);
		if (file.length()==0) {//check if the file is empty
			System.err.println("The file is empty!");
			System.exit(0);
		}
		Tree tree = new Tree();//creates the tree
		codebook(tree);
		output(new File(args[1]),tree.search(scan(new File(args[0]))));
		

	}
}
//////////////////////////////
