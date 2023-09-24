// this program reads a line with ints and does division
import java.util.Scanner;
public class Fractions {
    public static double fraction(int b, int c){//this is the method tah calculates the fractions
        double e= (double)b, f= (double)c;
        return (e/f);
    }
    public static double mixedNumber(int a, int b, int c){// this method calculates the value of mixed numbers
        double x = (double)a, y = (double)b, z = (double)c;
        if (x<0){// this conditional statement checks if the whole number of the mixed number is negative and if so it solves the mixed number normally and adds a negative value at the end 
            return (-(((z*Math.abs(x))+y)/z));
        }else{// else it will calculate the value of the mixed number regularly
            return (((z*x)+y)/z);
        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(args[0]);
        //Scanner scan = new Scanner(System.in);
        int a = 0, b =0, c=0,i, o=0,n=0;// initializing ints
        String s="", s2="";//initializing strings
        boolean runMath=true,mixedNum1=false, mixedNum2=false, frac=false, wholeNum=false,validWholeNum=true;// initializing booleans
        while (scan.hasNext()){// this loop runs while there is a a word in the input.
            if (scan.hasNextInt()){// if there is a whole number is takes the value and stores it in a.
                a = scan.nextInt();
                mixedNum1=true;
                wholeNum=true;
                
                if (o==1){// this condtional prints an error message for situations like "1 2"
                    runMath=false;
                }
                o++;
            }
            else{
                s=scan.next();
                if (n==1){//this condtional prints an error message for situations that have more than one fractions  
                    runMath=false;
                }
                n++;
                if (s.charAt(0)=='/'){
                    break;
                }
                i=0;
                while(i<s.length()){// in this while loop, the program divides the strings into different parts and then assigns the integer value to an int to make calculations later
                    if (Character.isDigit(s.charAt(i))||s.charAt(i)=='+' || s.charAt(i)=='-' ){
                        s2+=String.valueOf(s.charAt(i));
                        i++;
                    }
                    else if(s.charAt(i)=='/'){
                        i++;
                        b=Integer.parseInt(s2);
                        s2="";
                        if (i==s.length()){
                            validWholeNum=false;
                        }
                    }
                    else{
                        System.err.println("ERROR");
                        runMath=false;
                        break;
                    }
                }
                if(runMath==true&&s2.length()>0){
                    c=Integer.parseInt(s2);
                    mixedNum2=true;
                    frac=true;
                }
            }
        }
        scan.close();
        if(runMath==true){//This conditional only checks if all the variables are set to do the math
            if(mixedNum1==true && mixedNum2==true){// check if math is ready for mixed numbers
                System.out.println(mixedNumber(a, b, c));
            }
            else if (frac==true){//check if math is ready for fractions
                System.out.println(fraction(b, c));
            }
            else if (wholeNum==true && validWholeNum==true){//check if math is ready for whole numbers, in this case it just prints the whole number as a double
                System.out.println((double)a);
            }
            else{
                System.err.println("ERROR");// otherwise an error is printer if one of these conditions are not valid
            }
        }
        else{// prints and error message
            System.err.println("ERROR");
        }
    }
}