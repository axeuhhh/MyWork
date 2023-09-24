import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//public class HuffTestSample {
//	// the methods of the program
//	static void compareFile(File file1, File file2,HashMap<Character,Long> map) throws FileNotFoundException  {
//		String s1=scanner(file1);
//		String s2=scanner(file2);
//		if (s1.length()<1 || s2.length()<1) {
//			System.err.println("the files/file that you gave me is/are empty 🤣");
//			System.exit(0);
//		}
//		boolean run1=true, run2=true;
//		int index1=0, index2 =0;
//		for (int i =0;i< s1.length() && i<s2.length();i++) {
//			while (run1) {
//				// add one while loop to do the checking for both strings
//				if(map.get(s1.charAt(index1)) == null) {
//					index1++;
//				}else {
//					break;
//				}
//			}
//			while (run2) {
//				if(map.get(s2.charAt(index2)) == null) {
//					index2++;
//				}else {
//					break;
//				}
//			}
//			if (s1.charAt(index1)!=s2.charAt(index2)) {
//				System.out.println("FAIL input "+s1.charAt(index1)+" @ "+String.valueOf(index1)+" output "+s2.charAt(index2)+" @ "+String.valueOf(index2));
//				System.exit(0);
//			}
//			index1++;
//			index2++;
//		}
//		System.out.println("PASS");
//	}
//	static String scanner(File file) throws FileNotFoundException {
//		Scanner scan = new Scanner(file);
//		String s = "";
//		
//		while(scan.hasNextLine()){
//			s += scan.nextLine();
//		}
//		scan.close();
//		return s;
//		
//		
//	}
//	static HashMap<Character, Long> codebook() throws FileNotFoundException {
//		// Change because it may go out of bounds
//		File file = new File("codebook");
//		HashMap<Character,Long> map= new HashMap<>();
//		Scanner scan = new Scanner(file);
//		while (scan.hasNextLine()) {
//			String[] sa=scan.nextLine().split(":");
//			map.put(((char)Integer.parseInt(sa[0])),0l);
////			map.put(((char)Integer.parseInt(sa[0])), Long.parseLong(sa[1]));
//		}
//		scan.close();
//		return map;
//	}
//	
//	//main method
//	public static void main(String[] args) throws FileNotFoundException{
//		if (args.length<2) {
//			System.err.println("There are not enough command line arguments 🤣");
//			System.exit(0);
//		}
//		//initializing the variables
//		File file1 = new File(args[0]);
//		File file2 = new File(args[1]);
//		HashMap<Character,Long> map= new HashMap<>();
//		map=codebook();
//		compareFile(file1,file2, map);
//	}
//}

//public class HuffTest {
//	
//	static HashMap<Character, Long> codebook() throws FileNotFoundException {
//		// Change because it may go out of bounds
//		File file = new File("codebook");
//		HashMap<Character,Long> map= new HashMap<>();
//		Scanner scan = new Scanner(file);
//		while (scan.hasNextLine()) {
//			String[] sa=scan.nextLine().split(":");
//			map.put(((char)Integer.parseInt(sa[0])),0l);
//		}
//		scan.close();
//		return map;
//	}
//	// this function compares the two functions
//	// it uses a file input stream to look at all the characters in the file
//	static void compare(File file1, File file2, HashMap<Character, Long> map) throws IOException {// use the file reader 
//		Reader r1 = new FileReader(file1);
//		Reader r2 = new FileReader(file2);
//		int data1=r1.read();
//		int data2=r2.read();
//		///////
//		int index1=0, index2 =0;
//		boolean run1=true, run2=true;
//		for (int i =0;i< file1.length() && i<file2.length();i++) {
//			while (run1) {
//		
//				if(map.get((char)data1) == null) {
//					data1=r1.read();
//					index1++;
//				}else {
//					break;
//				}
//			}
//			while (run2) {
//				if(map.get((char)data2) == null) {
//					data2=r2.read();
//					index2++;
//				}else {
//					break;
//				}
//			}
//			if (data1!=data2) {
//				System.out.println("FAIL input "+(char)data1+" @ "+String.valueOf(index1)+" output "+(char)data2+" @ "+String.valueOf(index2));
//				System.exit(0);
//			}
//			index1++;
//			index2++;
//			data1=r1.read();
//			data2=r2.read();
//		}
//		System.out.println("PASS");
//		 
//		///////
//		r1.close();
//		r2.close();
//	}
//	public static void main(String[] args) throws IOException {
//		
//		long startTime = System.nanoTime();
//		compare(new File(args[0]),new File(args[1]), codebook());
//		long endTime = System.nanoTime();
//		double executionTime = (endTime - startTime) / 1_000_000_000.0;
//
//		System.out.println("Execution time: " + executionTime + " seconds");
//	}
//}
//

public class HuffTestSample{
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("decoded.txt");
		Scanner scan = new Scanner(f);
		String s= new String("");
		if (scan.hasNextLine()) {
			s=scan.nextLine();
		}
		System.out.println(s.indexOf((char)4));
	}
}


