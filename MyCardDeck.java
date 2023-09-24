/** This program is a program that uses object oriented programming to make cards and a deck of cards
 * I used this program with other to create cards for card games.
 */

class Card{// class card, contains the constructors that are overloaded.
    int in;
    String string;
    void initialize(int i, String s){// this function is the power house of this class, it sets the value of the int and the value of the string or sets it to 0
        if (i>=2 && i<=14){
            in=i;
        }
        else{
            in=0;
            string="ERROR";
        }
        if (s.charAt(0)=='s'|| s.charAt(0)=='S'){
            string="Spades";
        }
        else if (s.charAt(0)=='h'|| s.charAt(0)=='H'){
            string="Hearts";
        }
        else if (s.charAt(0)=='c'|| s.charAt(0)=='C'){
            string="Clubs";
        }
        else if (s.charAt(0)=='d'|| s.charAt(0)=='D'){
            string="Diamonds";
        }
        else{
            in=0;
            string="ERROR";
        }
    }
    public Card(int i, String s){ // calls initialize
        initialize(i,s);
    }
    public Card(String s){// divides the string in different portions and calls the class initialize
        if (s.charAt(0)=='1'){// handles the special case opf 10
            if (s.charAt(1)=='0'){
                initialize(10, String.valueOf(s.charAt(2)));
            } 
        }
        else if (Character.isDigit(s.charAt(0))){// handles other number regardless of its value
            if (Character.getNumericValue(s.charAt(0))!=1){
                initialize(Character.getNumericValue(s.charAt(0)), String.valueOf(s.charAt(1)));
            }
        }
        else if (s.charAt(0)=='j'|| s.charAt(0)=='J'){//handles j by setting the value to 11 which print() will clean up before printing
            initialize(11, String.valueOf(s.charAt(1)));
        }
        else if (s.charAt(0)=='a'|| s.charAt(0)=='A'){//handles q by setting the value to 12 which print() will clean up before printing
            initialize(14, String.valueOf(s.charAt(1)));
        }
        else if (s.charAt(0)=='k'|| s.charAt(0)=='K'){//handles k by setting the value to 13 which print() will clean up before printing
            initialize(13, String.valueOf(s.charAt(1)));
        }
        else if (s.charAt(0)=='q'|| s.charAt(0)=='Q'){//handles a by setting the value to 14 which print() will clean up before printing
            initialize(12, String.valueOf(s.charAt(1)));
        }
        
        else{
            initialize(0, "o");
        }   
    }
    public Card(String s, String s2){// class initailize after changing the value of the first string to a number
        if (s.charAt(0)>='1'){
            initialize(Integer.valueOf(s), s2);
        }
        else if (s.charAt(0)=='j'|| s.charAt(0)=='J'){
            initialize(11, s2);
        }
        else if (s.charAt(0)=='a'|| s.charAt(0)=='A'){
            initialize(14, s2);
        }
        else if (s.charAt(0)=='k'|| s.charAt(0)=='K'){
            initialize(13, s2);
        }
        else if (s.charAt(0)=='q'|| s.charAt(0)=='Q'){
            initialize(12, s2);
        }
        else{
            initialize(0, "E"); 
        }
    }
    public int Value(){ //return the value of an int
        return in;
    }
    public String Suit(){ //return a capital letter of the first charater of the suit
        return Character.toString(string.charAt(0));
    }
    public String print(){ //cleans up the porgram to only 2 character for printing
        if (in>=2 && in <=10){
            String newString= String.valueOf(Value());
            return newString+Suit();
        }
        else if(in==11) {
            return "J"+Suit();
        }
        else if(in==12) {
            return "Q"+Suit();
        }
        else if(in==13) {
            return "K"+Suit();
        }
        else if(in==14) {
            return "A"+Suit();
        }
        else {
            return "0ERROR";
          }
    }
}
class Deck{ //creates all the deck of 52 cards
    /*Card[] allMyCards = new Card[52];
    Deck(){   
        int i1=0;
        int cardValue=2;
        while(i1<=12){
            allMyCards[i1] =new Card(cardValue++, "s");
            i1++;
        }
        int i2=13;
        cardValue=2;
        while(i2<=25){
            allMyCards[i2] =new Card(cardValue++,"h");
            i2++;
        }
        int i3=26;
        cardValue=2;
        while(i3<=38){
            allMyCards[i3] =new Card(cardValue++,"c");
            i3++;
        }
        int i4=39;
        cardValue=2;
        while(i4<=51){
            allMyCards[i4] =new Card(cardValue++,"d");
            i4++;
        }  
    } */
    ////////////////
    Card[] allMyCards = new Card[364];
    Deck(){  
    	for(int i=0;i<7;i++) {
	        int i1=0;
	        int cardValue=2;
	        while(i1<=12){
	            allMyCards[i1] =new Card(cardValue++, "s");
	            i1++;
	        }
	        int i2=13;
	        cardValue=2;
	        while(i2<=25){
	            allMyCards[i2] =new Card(cardValue++,"h");
	            i2++;
	        }
	        int i3=26;
	        cardValue=2;
	        while(i3<=38){
	            allMyCards[i3] =new Card(cardValue++,"c");
	            i3++;
	        }
	        int i4=39;
	        cardValue=2;
	        while(i4<=51){
	            allMyCards[i4] =new Card(cardValue++,"d");
	            i4++;
	        }  
    	}
    } 
    ////////////////
    void Print(){//functions prints cards
        int print=0;
        while(print<=51){
        System.out.print(allMyCards[print].print());//
        System.out.print(" ");
        ++print;
        }
    } void shuffle(){// shuffle method that I am working on
        int i=0;
        int[] iAr= new int[52];
        while (i<52){
            iAr[i]=i;
            i++;
        }
    }
}
public class MyCardDeck{
    public static void main(String[] args){
        if (args.length==0){
            Deck axel = new Deck();//creates the object deck of cards
            //axel.Print();// calls the print fucntions that prints each card
            for (int i = 0; i<7;i++) {
            	axel.Print();// calls the print fucntions that prints each card
            //axel.shuffle();
            }
            //axel.shuffle();
        }
        else{
            Card ndombasi = new Card(args[0]);// takes commande line argument and check if it is a valid card type
            System.out.println(ndombasi.print());// prints the card or if there is an error, prints error
            }
    }
}