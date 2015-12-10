public class SuperArray implements ListInt{

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

    //=====Accessors for instance variables=========
    public int lastPos() {
        return _lastPos;
    }

    public int size() { //better to have and not need than to not have and need
        return _size;
    }
    
    //~~~~~METHODS~~~~~
    //default constructor that initializes 10-item array
    public SuperArray() {
        _data = new Comparable[10];
        _lastPos = -1; //nothing has been changed yet'
        _size = 0;
    }

        
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
        String retstr = "["; 
        for (int i = 0 ; i < _size ; i++) {  
            retstr += _data[i];
            retstr += ", ";  //personal preference to have a space along with the comma
        }
        if (retstr.length() > 1) {
            retstr = retstr.substring(0, retstr.length() - 2); //get rid of trailing comma
        }
        retstr += "]";
        return retstr;
    }

        
    //double capacity of this SuperArray
    public void expand() {
        Comparable[] temp = new Comparable[_data.length * 2];
        for (int i = 0 ; i < _data.length ; i++) {
            temp[i] = _data[i];
        }      
        _data = temp;
    }

        
    //accessor -- return value at specified index
    public Comparable get( int index ) {
        return _data[index];
    }

        
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) {
        Comparable old = _data[index];
        _data[index] = newVal;
        return old;
    }
    
    public void add(Comparable a) { 
        _lastPos += 1;
        if (_lastPos >= _size) { //expand the array until the size is big enough
            expand();
        }
        _data[_lastPos] = a;
        _size += 1;
    }
    
    public void add(int index, Comparable a) {
        if (index > _lastPos) {
            add(a);
        }
        else {
            _size += 1;
            while (_data.length < _size) { 
                expand();
            }
            add(a);
            _size -= 1;
            for (int i = _lastPos; i >= index; i--) { //starts at the lastpos
                set(i, set(i + 1, _data[i]));  //puts all elements after last position one step up
            }
        }
    }

    public void remove(int index) {
        for (int i = index ; i < _lastPos ; i++) { //starts loop at deleted index
            set(i, set(i + 1, _data[i])); 
        }
        _size -= 1;
        _lastPos -= 1;
    }

    //~~~~~METHODS additional~~~~~
    //finds first instance of input
    public int linSearch (Comparable c){
	for (int i=0;i<_lastPos+1;i++){
	    if (_data[i].compareTo(c)==0){
		return i;
	    }
	}
	return -1;
    }

    //checks to see if _data is sorted from least to greatest or vice versa
    public boolean isSorted(){
	boolean LToG=true;
	boolean GToL=true;
	for (int i=1;i<_lastPos+1;i++){
	    //check from least to greatest first
	    if (_data[i-1].compareTo(_data[i])>0){
		LToG=false;
	    }
	    //check from greatest to least first
	    if (_data[i-1].compareTo(_data[i])<0){
		GToL=false;
	    }
	}
	return (LToG || GToL);
    }

    //main method for testing
    public static void main( String[] args ) {
	System.out.println("---linSearch---");
	
	SuperArray zed=new SuperArray();
	zed.add(new Binary(1));
	zed.add(new Rational(4,2));
	zed.add(new Hexadecimal(3));
	zed.add(new Binary(4));
	zed.add(new Hexadecimal(5));
	System.out.println(zed.linSearch(new Binary(1)));
	System.out.println(zed.linSearch(new Hexadecimal(5)));
	System.out.println(zed.linSearch(new Rational (4,2)));
	System.out.println(zed.isSorted());
	
	SuperArray yve=new SuperArray();
	yve.add(new Binary(1));
	yve.add(new Binary(4));
	yve.add(new Rational(4,2));
	yve.add(new Hexadecimal(5));
	yve.add(new Hexadecimal(3));
	System.out.println(yve.isSorted());
        /* System.out.println("Testing constructors...");
        SuperArray test = new SuperArray();
        System.out.println("Last Position: " + test._lastPos);
        System.out.println("Size: " + test._size);
        System.out.println("\nTesting toString by printing empty array...");
        System.out.println(test.toString());
        System.out.println("\nTesting accessors and mutators...");
        for (int i = 0 ; i < test._data.length ; i++) {  
            test.set(i, i + 1);
        }
        for (int a = 0 ; a < test._data.length ; a++) {  //hardcoded 10
            System.out.println(test.get(a));
        }
        System.out.println("\nTesting expand...");
        test.expand();
        System.out.println("New Length: " + "20");  //this will be replaced in future with a getlength accessor
        System.out.println("\nFilling expanded superarray...");
        for (int b = 0 ; b < test._data.length ; b++) { 
            test.set(b, b + 1);
        }
        System.out.println("\nTesting add...");
        test.add(128);
        test.add(256);
        test.add(512);
        test.add(1024);
        test.add(2048);
        System.out.println(test.toString());
        System.out.println("\nTesting remove...");
        test.remove(3);
        test.remove(4);
        System.out.println(test.toString());
        System.out.println("\nTesting add at index...");
        test.add(4096, 10);
        test.add(8192, 20);
        System.out.println(test.toString());   
        System.out.println("\n--------------------------------");
        System.out.println("Now testing ListInt's methods...");
        System.out.println("--------------------------------");
        ListInt ltest = new SuperArray();
        System.out.println("\nTesting add...");
        ltest.add(128);
        ltest.add(256);
        ltest.add(512);
        ltest.add(1024);
        ltest.add(2048);
        System.out.println(ltest.toString());
        System.out.println("\nTesting remove...");
        ltest.remove(3);
        ltest.remove(4);
        System.out.println(ltest.toString());
        System.out.println("\nTesting add at index...");
        ltest.add(4096, 10);
        ltest.add(8192, 20);
        System.out.println(ltest.toString()); */
    }//end main

}//end class
