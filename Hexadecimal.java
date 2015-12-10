public class Hexadecimal {

    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF";

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	_hexNum = dectoHexR(n);
	_decNum = n;
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_decNum = hextoDecR(s);
	_hexNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;
    }


    /*=====================================
      String dectoHex(int) -- converts base-10 input to hexadecimal
      pre:  n >= 0
      post: returns String of bits
      eg  dectoHex(0) -> "0"
      dectoHex(1) -> "1"
      dectoHex(2) -> "2"
      dectoHex(3) -> "3"
      dectoHex(14) -> "E"
      =====================================*/
    public static String dectoHex( int n ) {
	String te = "";
	while(n != 0){
	    te = HEXDIGITS.substring((n % 16), (n % 16) + 1) + te;
	    n /= 16;
	}
	return te;
    }


    /*=====================================
      String dectoHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  dectoHexR(0) -> "0"
      dectoHexR(1) -> "1"
      dectoHexR(2) -> "2"
      dectoHexR(3) -> "3"
      dectoHexR(14) -> "E"
      =====================================*/
    public static String dectoHexR( int n ) { 
	String te = "";
	if(n != 0){
	    te = dectoHexR(n / 16) + HEXDIGITS.substring((n % 16), (n % 16) + 1);
	}
	else{
	    return "";
	}
	return te;
    }


    /*=====================================
      String hextoDec(String) -- converts base-10 input to hexadecimal
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hextoDec("0") -> 0
      hextoDec("1") -> 1
      hextoDec("2") -> 2
      hextoDec("3") -> 3
      hextoDec("E") -> 14
      =====================================*/
    public static int hextoDec( String s ) {
        int te = 0;
	int ctr = 0;
	for(int i = s.length(); i > 0; i--){
	    te += HEXDIGITS.indexOf(s.substring(i - 1, i) + "") * Math.pow(16, ctr);
	    ctr++;
	}
	return te;
    }


    /*=====================================
      String hextoDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hextoDecR("0") -> 0
      hextoDecR("1") -> 1
      hextoDecR("2") -> 2
      hextoDecR("3") -> 3
      hextoDecR("E") -> 14
      =====================================*/
    public static int hextoDecR( String s ) { 
	int te = 0;
	if(s.length() > 1){
	    te = hextoDecR(s.substring(1, s.length())) + HEXDIGITS.indexOf(s.substring(0, 1)) * (int)Math.pow(16, s.length() - 1);
	}
	else{
	    return ((int)Math.pow(16, 0) * HEXDIGITS.indexOf(s));
	}//could return parseInt(s), but I chose this because it's a power of 2
	return te;
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexadecimal values
      =============================================*/

    public boolean equals( Object other ) { 
	return ((this==other) || (this.toString().equals(other.toString())));
    }
    /*
    */

    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/

    public int compareTo( Object other ) {
	if(this._decNum == ((Hexadecimal)other)._decNum){
	    return 0;
	}
	else if(this._decNum > ((Hexadecimal)other)._decNum){
	    return 1;
	}
	else{
	    return -1;
	}
    }
    /*
    */

    //main method for testing
    public static void main( String[] args ) {

	System.out.println(hextoDec("5")); //5
	System.out.println(hextoDec("80520")); //525600
	System.out.println(hextoDecR("5")); //5 
	System.out.println(hextoDecR("80520")); //525600
	System.out.println(dectoHex(5)); //5
	System.out.println(dectoHex(525600)); //80520
	System.out.println(dectoHexR(5)); //5
	System.out.println(dectoHexR(525600)); //80520


	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal("5");
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);
	Hexadecimal b5 = new Hexadecimal(15);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       
	System.out.println( b5 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
