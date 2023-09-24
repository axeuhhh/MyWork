import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

//this program uses the if statment instead of the hashmap
public class HuffTest {/** add an other index that will not make the file runout.*/
	
	// this function compares the two functions
	// it uses a file input stream to look at all the characters in the file
	static void compare(File file1, File file2) throws IOException {// use the file reader 
		if (file1.length()<1 || file2.length()<1) {
			System.err.println("the files/file that you gave me is/are empty 😡 you made me crash!");
			System.exit(0);
		}
		Reader r1 = new FileReader(file1);
		Reader r2 = new FileReader(file2);
		int data1=r1.read();
		int data2=r2.read();
		///////
		int index1=0, index2 =0, last=0;
		boolean run1=true, run2=true;
		for (int i =0;i< file1.length() && i<file2.length();i++) {
			
			while (run1) {
		
				if((data1!=4)&&!(data1 >= 7 && data1 <= 254)){
					data1=r1.read();
					index1++;
				}else {
					break;
				}
			}
			while (run2) {
				if((data2!= 4)&&!(data2 >= 7 && data2 <= 254)) {
					data2=r2.read();
					index2++;
				}else {
					break;
				}
			}
			if (data1!=data2) {
				System.out.println("FAIL input "+(char)data1+" @ "+String.valueOf(index1)+" output "+(char)data2+" @ "+String.valueOf(index2));
				System.exit(0);
			}
			index1++;
			index2++;
			last = data2;
			data1=r1.read();
			data2=r2.read();
			
			
		}
		if (index1<file1.length()) {// check for the previou character if the other file is longer that the
			for (int i = index1;i<file1.length();i++) {
				
				if((data1==4)||(data1 >= 7 && data1 <= 254)) {
					--index2;
					System.out.println("FAIL input "+(char)data1+" @ "+String.valueOf(index1)+" output "+(char)last+" @ "+String.valueOf(index2));
					System.exit(0);
				}
				data1=r1.read(); 
				index1++;
			}
		}

		System.out.println("PASS");
		r1.close();
		r2.close();
	}
	public static void main(String[] args) throws IOException {
		compare(new File(args[0]),new File(args[1]));
	}
}