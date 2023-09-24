// this program play the Casino game crap
//Starting with 20s and playing until the program is out of money
import java.util.Random;

class Die{
	int number;
	Die(){
		Random rand = new Random();
		number = rand.nextInt(7);
	}
	
}
class Dice{
	int sum;
	Dice(){
		Die die1= new Die();
		Die die2= new Die();
		sum+=die1.number;
		sum+=die2.number;
	}
	
	
}
public class BackAlley {
	
	
	
	public static void main(String[] args) {
		int money=20;
		int point=0;
		
		
		while (money>0) {
			Dice dice= new Dice();
			
			
			if (dice.sum==7 || dice.sum==11) {
				money+=1;
				System.out.println("$"+money+" win");
				
			}
			else if(dice.sum==2 || dice.sum==12 || dice.sum==3) {
				money-=1;
				System.out.println("$"+money+" lose");
				 
			}
			else {
				point= dice.sum;
				boolean run=true;
				
				
				while(run) {
					
					Dice diceP= new Dice();
					if (point==diceP.sum) {
						money+=1;
						System.out.println("$"+money+" win");
						run=false;
						
					}
					else if(diceP.sum==7) {
						money-=1;
						System.out.println("$"+money+" lose");
						run=false;
						
					}
					
				}
				
			}
		}
	
	}
}
