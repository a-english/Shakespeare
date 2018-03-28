
/**
 * Angela English
 * CSC 130 MW 4-5:15
 * Working alone
 */

/**
 * TODO Replace this comment with your own.
 * 
 * Stub code for an implementation of a DataCounter that uses a hash table as
 * its backing data structure. We included this stub so that it's very clear
 * that HashTable works only with Strings, whereas the DataCounter interface is
 * generic.  You need the String contents to write your hashcode code.
 */

public class HashTable implements DataCounter<String> {
	//static final int SIZE = 6997; 	//prime number large enough to keep lambda below 1. Shooting for 1/2-3/4 of array being filled
	static final int SIZE = 131;
	DataCount<String>[] table;
	int size;
	
	public HashTable() {
		table=new DataCount[SIZE];
		size=0;
	}
	/*
	 * Original table contains blank spaces.
	 * This creates a new array without blank spaces to return.
	 */
    public DataCount<String>[] getCounts() {
		//System.out.print("Creating new array of size "+size+"\n");
    	//System.out.print(dump());
    	DataCount<String>[] counts =  new DataCount[size];
    	int i=0;
    	for(DataCount<String> hashCell : table) {
    		if(hashCell!=null) {
    			counts[i]=new DataCount<String>(hashCell.data, hashCell.count);
    			//System.out.print("Added '"+counts[i].data+"',"+counts[i].count+" at "+i+"\n");
    			i++;
    		}
    	}
    	
        return counts;
    }

    public int getSize() {
        return size;
    }

    public void incCount(String data) {
    	int hash=0; char temp;
		//System.out.print(data+"=");
    	for(int i=0; i<data.length(); i++)
    	{
    		temp=data.charAt(i);
    		hash+=temp;
    		//System.out.print(temp+"("+hash+")"+"+");
    	}
		//System.out.print(" = "+hash+"\n");
    	//Hash now contains an int representing the sum of all the letters. Now let's hash.
    	hash=hash%SIZE;
		boolean found=false;
		int i=1;
		while(!found){
			//System.out.print("Trying "+hash+"\n");
	    	if(table[hash]==null){
	    		//if that spot is empty, store the new entry.
	    		table[hash]=new DataCount<String>(data, 1);
	    		//System.out.print("'"+data+"' placed in "+hash+"\n");
	    		found=true;
	    		//only increment on fresh entry
	    		size++;
	    		
	    	}else{
	    		//System.out.print("Collision detected placing '" + data+"' into '"+table[hash].data+"' at "+hash+"\n");
	        	//promptEnterKey();
	        	//System.out.print(dump());
	    		//if it is full, two things may have happened
	    		//1. either this is the same element and should be incremented
	    		//2. or the hash needs to be run again
    			if (table[hash].data.equals(data))
    			{
    				//case 1
    	    		//System.out.print("Same value. Incrementing.\n");
    				table[hash].count++;
    				found=true;
    			}
    			else
    			{
    				//case 2
    				//quadratic probing
    				hash=hash+i*i;
    				i++;
    				//System.out.print("Trying "+hash+"\n");
    			}
    		}
    	}
		//System.out.print("Successfully placed '" + data+"' into '"+table[hash].data+"' at "+hash+"\n");
    }
    
    public String dump(){
    	String dump="";
    	for(int i=0; i<SIZE; i++) {
    		dump+=i+"\t: ";
    		if(table[i]==null)
    			dump+="[null]\n";
    		else
    			dump+=table[i].data+"\t"+table[i].count+"\n";
    	}
    	return dump;
    }
    
    public void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
