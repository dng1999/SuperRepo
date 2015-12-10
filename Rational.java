public class Rational implements Comparable{

    public int numerator, denominator;
    
    public Rational() {
        numerator = 0;
        denominator = 1;
    }
    
    public Rational(int newn, int newd) {
        this();
        if (newd != 0) {
            numerator = newn;
            denominator = newd;
        }
        else {
            System.out.println("You have tried to divide by 0. Time to die.");
        }
    }
    
    public void setRational(int newn, int newd) {
        numerator = newn;
        denominator = newd;  //helper to make the test cases more organized, but not really necessary
    }
    
    public String toString() {
        return numerator + "/" + denominator + "\n";
    }
    
    public double floatValue() {
        return ( (double) numerator ) /  denominator;
    }
    
    public void multiply(Rational tom) {   //tom is tomultiply
	if (tom.equals(null)){
	    throw new NullPointerException ("Error: null input");
	}
	else {
	    numerator *= tom.numerator;
	    denominator *= tom.denominator;
	}
    }
    
    public void divide(Rational tod) {      //tod is todivide
	if (tod.equals(null)){
	    throw new NullPointerException ("Error: null input");
	}
	else {
	    numerator *= tod.denominator;
	    denominator *= tod.numerator;
	}
    }
    
    public void add(Rational toad) {           // toad is toadd
	if (toad.equals(null)){
	    throw new NullPointerException ("Error: null input");
	}
	else {
	    numerator*=toad.denominator;
	    numerator+=(toad.numerator*denominator);
	    denominator*=toad.denominator;
	}
    }
    
    public void subtract(Rational toss) {      //toss is tosubtract
	if (toss.equals(null)){
	    throw new NullPointerException ("Error: null input");
	}
	else {
	    numerator*=toss.denominator;
	    numerator-=(toss.numerator*denominator);
	    denominator*=toss.denominator;
	}
    }
    
    public int gcd() {
        int i;
        int a = numerator;
        int b = denominator;
        while(a!=0) {
            i=a;
            a=b%a;
            b=i;
        }
        return b;
    }
    
    public void reduce() {
        int common = gcd();
        numerator/=common;
        denominator/=common;
    }
    
    public boolean equals(Rational other) {
	if (other.equals(null)){
	    throw new NullPointerException ("Error: null input");
	}
        else if (this == other) {
            return true;
        }
        else {   //reduces fractions
            Rational reducethis = this;
            Rational reduceother = other;
            reducethis.reduce();
            reduceother.reduce(); //reasoning is if you multiply the two they should be equal
            int p1 = this.numerator * other.denominator;  //hooray cross multiplication!
            int p2 = other.numerator * this.denominator;
            if (p1 == p2) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public int compareTo(Object o) {
	if (o.equals(null)){
	    throw new NullPointerException ("Error: null input");
	}
        else if (o instanceof Comparable) {
            Rational dis = this;
            Rational other = ((Rational)o);
            dis.reduce();
            other.reduce();
            int p1 = dis.numerator * other.denominator; //same implementation as equals
            int p2 = other.numerator * dis.denominator;
            int subtract = p1 - p2;
            if (subtract < 0) {
                return -1;
            }
            else if (subtract == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else {
            throw new ClassCastException ("Error: Not of Comparable class.");
        }
    }

    public static void main(String[] args) { //tests all methods and constructors
        System.out.println("The values are set back to default (1/2 and 2/3) after every method.\n");
        System.out.println("Testing default constructor...");
        try {
            Rational tester = new Rational();
            System.out.println(true + "\n");
        }
        catch (Exception e) {
            System.out.println(false + "\n");
        }
        System.out.println("Should print failure to create Rational message...");
        Rational printer = new Rational(1, 0);
        Rational equaler = new Rational(2, 4);
        Rational x = new Rational(1, 2);
        Rational y = new Rational(2, 3);
        System.out.println("\n" + "Testing print and constructors...");
        System.out.println(x.toString() + "\n");
        System.out.println("Testing multiplication method...");
        x.multiply(y);
        System.out.println(x.toString() + "\n");
        x.setRational(1, 2);
        System.out.println("Testing division method...");
        x.divide(y);
        System.out.println(x.toString() + "\n");
        x.setRational(1, 2);
        System.out.println("Testing addition method...");
        x.add(y);
        System.out.println(x.toString() + "\n");
        x.setRational(1, 2);
        System.out.println("Testing subtraction method...");
        x.subtract(y);
        System.out.println(x.toString() + "\n");
        x.setRational(1, 2);
        System.out.println("Testing GCD method...");
        System.out.println(x.gcd() + "\n");
        System.out.println("Testing reduce method...");
        x.reduce();
        System.out.println(x.toString() + "\n");
        x.setRational(1, 2);
        System.out.println("Testing equals method...");
        System.out.println(x.equals(equaler) + "\n");
        System.out.println("Testing compareTo method...");
        System.out.println(x.compareTo(y));
    }

}
