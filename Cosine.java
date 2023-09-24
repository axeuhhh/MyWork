//this program caclulates the cosine of double entered in the first element of the command line
public class  Cosine {
    static long factorial(long n){//calculates the factorial
        if (n>=1){
            long fact=1; 
            for (long i = n ;i>=1 ;i--){
                fact=fact *i;
            }
            return fact;
        }
        else{
            return 1l;
        }
    }
   static  double power (double x, int p){//calculates the power by mulitplying the number by itself equivalent to the number of the power
        double pow=x;
        for (int i=1;i<p;i++){
            pow*=x;
        }
        return pow;
    }
    static double Cosine (double input){// given function that calulates the value of the cosine
        while (input<(-1*Math.PI)){
            input+= (2.0*Math.PI);
        }
        while (input>Math.PI){
            input-=(2.0*Math.PI);
        }
        double result = 1.0;
        for(int i = 1;i<=5;i++){
            result-= ((power(input, (4*i-2)))/(factorial((4*(long)i-2))));
            result+= ((power(input, 4*i))/factorial(4*(long)i));
        }
        return result;
    }
    public static void main(String[] args){
        double d = Double.parseDouble(args[0]);//reads only the first argument in the command line 
        System.out.println(Cosine(d));
    }
}
