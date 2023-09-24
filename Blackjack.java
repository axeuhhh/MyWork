import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.io.IOException; 
import java.io.DataInputStream; 
import java.io.DataOutputStream;

/**this program plays blackjack with a dealer though internet using a socket. It uses some strategy to play and bet using the information from the dealer.
 * */
public class Blackjack {
	// function to write and send that info to to the dealer
	private static void write(String s, DataOutputStream dos) throws IOException {
		dos.writeUTF(s);
		dos.flush(); 
	}
	//function to read input from the dealer
	private static String read(DataInputStream dis) throws IOException { 
		return dis.readUTF();
	}
	//it sets the number of cards that are left in the deck by subtracting from the cards that have been played
	/* these are the indexes of followed by the element present in the Array
	    * o A
	    * 1 2
	    * 2 3
	    * 3 4
	    * 4 5
	    * 5 6
	    * 6 7
	    * 7 8
	    * 8 9
	    * 9 10 & J & K & Q
	    */
	private static int[] numberOfCards(String[] arrayOfInput) {//function counts the cards that are left in the deck
		int[] numberOfCards = {28,28,28,28,28,28,28,28,28,112};
		for (int i=3; i<(arrayOfInput.length);i++) {
			
			if (arrayOfInput[i].charAt(0)=='1'){
				numberOfCards[9]-=1;
			}
			else if (Character.isDigit(arrayOfInput[i].charAt(0))){
				numberOfCards[((Character.getNumericValue((arrayOfInput[i].charAt(0)))-1))]-=1;
			}
			else if (arrayOfInput[i].charAt(0)=='J'|| arrayOfInput[i].charAt(0)=='Q' || arrayOfInput[i].charAt(0)=='K'){
				numberOfCards[9]-=1;
			}
			else if (arrayOfInput[i].charAt(0)=='A'){
				numberOfCards[0]-=1;
			}
			
		}
		return numberOfCards;
	}
	//this method uses the cards left in the deck and totals to the number of cards
	private static int totalNumberOfCards(int[] numberOfCards) {
		int totalNumberOfCards=0;
		for (int i = 0;i<numberOfCards.length;++i) {
			totalNumberOfCards+=numberOfCards[i];
		}
		return totalNumberOfCards;
	}
	// set the bet amount based on cards that have been played and cards that are left
	private static int amount(int totalNumberOfCards, int[] numberOfCards, int myMoney) {
		int amount=0;
		if ((numberOfCards[9]+numberOfCards[0])>(totalNumberOfCards-(numberOfCards[9]+numberOfCards[0]))&&numberOfCards[0]>0){//if there is at least 50% more 10 and aces then you want to bet higher
			
			amount=(int)(myMoney*.1);
			if (((numberOfCards[9]+numberOfCards[0])/totalNumberOfCards)>(3/4)) {// if there is more than 60% chance of having a blackjack, then I bet 1/3 if my amount
				amount= (int)(myMoney*.3);
			}
			if (amount<1) {
				amount=1;
			}
			
		}
		// check for betting
		//40% tens at least one ace 
		//50%
		// check for betting in the code  10%for ten  5%aces
		else {
			amount=1;
			
		}
		return amount;
	}
	
	// determines whether to hit
	private static boolean hit( int sumOfMyCards, int number, int[] numberOfCards, int totalNumberOfCards) {
		// does some math to determine whether there are enough cards to hit 21 or less rather then more than 21
		
        boolean hit=false;
        if ((20-sumOfMyCards)>9){
	        hit = true;
	    }
		if ((20-sumOfMyCards)<=9){
            for (int i =0;i<(20-sumOfMyCards);i++){
                number+=numberOfCards[i];
            }
            if (number>totalNumberOfCards-number){
                hit = true;
            }
        }
		return hit;
	}
	// determines whether should stand or not
	private static boolean stand(int sumOfMyCards, int number, int[] numberOfCards, int totalNumberOfCards) {
		boolean stand=true;
		if ((20-sumOfMyCards)<=9){
            for (int i =0;i<(20-sumOfMyCards);i++){
                number+=numberOfCards[i];
            }
            if (((double)number/(totalNumberOfCards-number))>((double)46/100)){// only if there are 54% or you recieving a cards that makes you bomb out then you want to stand
                stand = false;
            }
        }
		return stand;
	}
	
	// based on the cards left and if didn't split or double it determines whether to hit or stand
	private static String play(boolean stand,boolean hit, String play, int sumOfMyCards, int amount, String[] arrayOfInput, int myMoney){//function determines what is played
		if (sumOfMyCards>=17) {
			play="stand";
		}
		
		
		else if(((2*amount)<myMoney)&&arrayOfInput.length==6){// allows the program to split and double only on the first round and not after hitting
			if(arrayOfInput[4].charAt(0)=='A' && arrayOfInput[5].charAt(0)=='A'){
				play="split";
				
			}//Split 8 if dealers cards 2-9
			else if  ((arrayOfInput[4].charAt(0)=='8' && arrayOfInput[5].charAt(0)=='8') && (arrayOfInput[2].charAt(0)<='9' && arrayOfInput[2].charAt(0)>='2')) {
				play="split";
			}// split 7 if the dealer shows 7 or less
			else if (((arrayOfInput[4].charAt(0)=='7' && arrayOfInput[5].charAt(0)=='7')&& arrayOfInput[2].charAt(0)<='7')) {
				play="split";
			}//split 6if the dealer shows 6 or less
			else if (((arrayOfInput[4].charAt(0)=='6' && arrayOfInput[5].charAt(0)=='6')&& arrayOfInput[2].charAt(0)<='6')) {
				play = "stand";
			}//Split 3s only if the dealer shows 4-7
			else if (((arrayOfInput[4].charAt(0)=='3' && arrayOfInput[5].charAt(0)=='3')&&(arrayOfInput[2].charAt(0)<'7' && arrayOfInput[2].charAt(0)>='4'))) {//may be hit 
				play="stand";
			}// split 2s if the dealer shows 3-7
			else if (((arrayOfInput[4].charAt(0)=='2' && arrayOfInput[5].charAt(0)=='2')&&(arrayOfInput[2].charAt(0)<'7' && arrayOfInput[2].charAt(0)>='3'))) {
				play = "stand";
			}//
			
				
			
			///////// double  14 and 13  against dealers 5-6
			else if((sumOfMyCards==14||sumOfMyCards==13)&&(arrayOfInput[2].charAt(0)<='6' && arrayOfInput[2].charAt(0)>='5')) {
				play = "double";
			}// always double 11
			else if ((sumOfMyCards==11)) {
				play = "double";
			}// double 10 against dealer 2-9
			else if (((sumOfMyCards==10)&&(arrayOfInput[2].charAt(0)<='7' && arrayOfInput[2].charAt(0)>='2'))) {
				play = "double";//dealer has a nine or 8 
			}
			
			
		}
		
		if(play.length()==0){
            if (hit) {
                play="hit";
            }
            else if (!stand) {
                play="hit";
            }
            else if (stand) {
            	play="stand";
            }
            
        }
		return play;
	}
//this function that sums the values of my card
	private static int sumOfMyCards(String[] arrayOfInput, boolean ace ) {//Sums my cards 
		
		int sumOfMyCards=0;
		for(int i = 4;i<arrayOfInput.length;i++) {
			if (arrayOfInput[i].charAt(0)=='1'){
				sumOfMyCards+=10;
			}
			else if (Character.isDigit(arrayOfInput[i].charAt(0))){// handles other number regardless of its value
				sumOfMyCards+=(Character.getNumericValue(arrayOfInput[i].charAt(0)));
			}
			else if (arrayOfInput[i].charAt(0)=='J'|| arrayOfInput[i].charAt(0)=='Q' || arrayOfInput[i].charAt(0)=='K'){//handles j k and q
				sumOfMyCards+=10;
			}
			else if (arrayOfInput[i].charAt(0)=='A') {//handles if for A
				sumOfMyCards+=11;
			}
			
		}
		 
		return sumOfMyCards;
	}
    
	
	
	
	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException  {
		String IpAddress = args[0];
		String IpPort=args[1];
		Socket socket = new Socket(IpAddress, Integer.valueOf(IpPort));
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos =new DataOutputStream(socket.getOutputStream());
		//initializing the variables so that they can be used in all the parts
		int myMoney=0;
		int amount=0,totalNumberOfCards=0 ; 
		int[] numberOfCards= {28,28,28,28,28,28,28,28,28,112}; 
		int win=0;
		int lose=0;
		boolean run=true;
		boolean ace=false;
		
		while(run) {
			String inputFromDealer=read(dis);
			String[] arrayOfInput=inputFromDealer.split(":");
			
			if (arrayOfInput[0].equals("login")) {
				write("axeuhhh:A", dos);
			}
			else if (arrayOfInput[0].equals("bet")) {
				
				myMoney = Integer.valueOf(arrayOfInput[1]);
				String bet="bet:";
				amount=0;
				
				numberOfCards=numberOfCards(arrayOfInput);
				
				totalNumberOfCards=totalNumberOfCards(numberOfCards);
				
				amount=amount(totalNumberOfCards, numberOfCards, myMoney);
				bet+=String.valueOf(amount);
				write(bet,dos);
			}
			else if(arrayOfInput[0].equals("play")) {
				// blackjack ratio is when you have a alot of As and 10s
				String play="";
				int sumOfMyCards=0;
				int number =0;
				
				sumOfMyCards=sumOfMyCards(arrayOfInput,ace);
				
				boolean hit = hit( sumOfMyCards, number, numberOfCards, totalNumberOfCards); 
				
				play=play(stand(sumOfMyCards, number, numberOfCards, totalNumberOfCards),hit, play, sumOfMyCards, amount, arrayOfInput, myMoney);
				System.out.println(inputFromDealer);
				System.out.println(play);
				write(play,dos);
			}
			else if(arrayOfInput[0].equals("status")) {
				// it records the amount of times 
				System.out.println(inputFromDealer);
				
				if (arrayOfInput[1].equals("lose")) {
					lose+=1;
				}
				else if(arrayOfInput[1].equals("win")) {
					win+=1;
				}
			}
			else if(arrayOfInput[0].equals("done")) {
				// tells you what happened in the game, if you won more than you lost or vice versa
				if(win>lose) {
					System.out.println("more wins than losses");
					System.out.println("win: "+win);
					System.out.println("lose: "+lose);
				}
				else {
					System.out.println("more losses than wins");
					System.out.println("win: "+win);
					System.out.println("lose: "+lose);
				}
				run=false;
				System.out.println(inputFromDealer);
				//tells you why you exit the game 
				System.out.println("The game is over.");
			}
		}
		socket.close();
	}
}