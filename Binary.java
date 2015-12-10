public class Binary {

    private int _decNum;
    private String _binNum;

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	_binNum = dectoBinR(n);
	_decNum = n;
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	_decNum = bintoDecR(s);
	_binNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum;
    }


    /*=====================================
      String dectoBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  dectoBin(0) -> "0"
      dectoBin(1) -> "1"
      dectoBin(2) -> "10"
      dectoBin(3) -> "11"
      dectoBin(14) -> "1110"
      =====================================*/
    public static String dectoBin( int n ) {
	String te = "";
	while(n != 0){
	    te = n%2 + te;
	    n /= 2;
	}
	return te;
    }


    /*=====================================
      String dectoBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  dectoBinR(0) -> "0"
      dectoBinR(1) -> "1"
      dectoBinR(2) -> "10"
      dectoBinR(3) -> "11"
      dectoBinR(14) -> "1110"
      =====================================*/
    public static String dectoBinR( int n ) { 
	String te = "";
	if(n != 0){
	    te = dectoBinR(n / 2) + n%2;
	}
	else{
	    return "";
	}
	return te;
    }


    /*=====================================
      String bintoDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      bintoDec("0") -> 0
      bintoDec("1") -> 1
      bintoDec("10") -> 2
      bintoDec("11") -> 3
      bintoDec("1110") -> 14
      =====================================*/
    public static int bintoDec( String s ) {
	int dec=0;
	int i=1;
	while (i!=s.length()+1){
	    dec+=Integer.parseInt(s.substring(i-1,i))*((int)Math.pow(2,s.length()-i));
	    i++;
	}
	return dec;
    }


    /*=====================================
      String bintoDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      bintoDecR("0") -> 0
      bintoDecR("1") -> 1
      bintoDecR("10") -> 2
      bintoDecR("11") -> 3
      bintoDecR("1110") -> 14
      =====================================*/
    public static int bintoDecR( String s ) { 
	int te = 0;
	if(s.length() > 1){
	    te = bintoDecR(s.substring(1, s.length())) + (Integer.parseInt(s.substring(0, 1)) * (int)Math.pow(2, s.length() - 1));
	}
	else{
	    return ((int)Math.pow(2, 0) * Integer.parseInt(s));
	}
	return te;
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/

    public boolean equals( Object other ) { 
	return ((this==other) || (this.toString().equals(other.toString())));
    }
    /*
     */

    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/

    public int compareTo( Object other ) {
	if(this._decNum == ((Binary)other)._decNum){
	    return 0;
	}
	else if(this._decNum > ((Binary)other)._decNum){
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

	System.out.println(bintoDec("101")); //5
	System.out.println(bintoDec("1110")); //14
	System.out.println(bintoDecR("101")); //5 
	System.out.println(bintoDecR("1110")); //14
	System.out.println(dectoBin(5)); //101
	System.out.println(dectoBin(14)); //1110
	System.out.println(dectoBinR(5)); //101
	System.out.println(dectoBinR(14)); //1110


	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary("5");
	Binary b3 = b1;
	Binary b4 = new Binary(7);
	Binary b5 = new Binary(15);

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
