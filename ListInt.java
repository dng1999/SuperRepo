public interface ListInt{
    
    //double the list 
    void expand(); 
    
    //add n to end of array
    void add(Comparable n);
    
    //add n at index of array
    void add(int index, Comparable a);
    
    //remove the value at array[index]
    //left-justifies
    void remove(int index);
    
    //return the size (of significant items)
    int size();
    
    //return value at index
    Comparable get(int index); 
    
    //set newVal to index
    Comparable set(int index, Comparable newValue);
}
