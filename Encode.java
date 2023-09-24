import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

public class Encode {
	//this functions makes a hashmap based on the codebook given to it
	static HashMap<Character, String> codebook() throws FileNotFoundException {// skip over the character
		
		File file = new File("codebook");
		HashMap<Character,String> map= new HashMap<>();
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String[] sa=scan.nextLine().split(":");
			map.put(((char)Integer.parseInt(sa[0])),sa[1]);
		}
		scan.close();
		return map;
	}//this function turn the input file with characters to the encoded values that are in the codebook and writes it to a file
	static void turn(Reader ips, Writer ops, HashMap<Character, String> map) throws IOException {// throw exceptions if there is a character that is not in the hashmap
		String s = "";
		int data=ips.read();
		while(data!=-1) {
			if (map.get((char)data)==null){
				data = ips.read();
			}
			else{
				s+=map.get((char)data);
				data = ips.read();
			}
			
		}
		//s+=map.get((char)4);// adds eof to the end of the encoded file.
		ips.close();
		ops.write(s);
		ops.close();
	}
	public static void main(String[] args) throws IOException {
		if(args.length<2) {
			System.err.println("There are not command Line arguments"); 
			System.exit(0);
		}
		File file = new File(args[0]);
		if (file.length()==0) {//checks whether there is something in the file.
			System.err.println("The file is empty!");
			System.exit(0);
		}
		Reader ips = new FileReader(args[0]);
		Writer ops = new FileWriter(args[1]);
		turn(ips,ops,codebook());
		
		

	}

}
