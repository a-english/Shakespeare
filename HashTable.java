
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
	static final int SIZE = 6997; 	//prime number large enough to keep lambda below 1
	DataCount<String>[] table;
	int size;
	
	public HashTable() {
		table=new DataCount[SIZE];
		size=0;
		
		dump();
	}

    public DataCount<String>[] getCounts() {
    	DataCount<String>[] counts =  new DataCount[size];
    	int i=0;
    	for(DataCount hashCell : table) {
    		if(hashCell!=null) {
    			counts[i]=new DataCount(hashCell.data, 1);
    			i++;
    		}
    	}
        return counts;
    }

    public int getSize() {
        return size;
    }

    public void incCount(String data) {
        // TODO Auto-generated method stub
    	int hash=0; char temp;
    	for(int i=0; i<data.length(); i++)
    	{
    		temp=data.charAt(i);
    		hash+=temp;
    	}
    	//Hash now contains an int representing the sum of all the letters. Now let's hash.
    	hash=hash%SIZE;
    	//that probably did nothing.
    	if(table[hash]==null)
    	{
    		//this might not work, if uninitialized values are not stored as null
    		//if that spot is empty, store the new entry.
    		table[hash]=new DataCount(data, 1);
    		
    	}
    	else
    	{
    		//if it is full, two things may have happened
    		//1. either this is the same element and should be incremented
    		//2. or the hash needs to be run again
    		boolean found=false;
    		int i=1;
    		while(!found){
    			if (table[hash].data==data)
    			{
    				//case 1
    				table[hash].count++;
    				found=true;
    			}
    			else
    			{
    				//case 2
    				//quadratic probing
    				hash=hash+i*i;
    				i++;
    				
    			}
    		}
    	}

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
    	return "";
    }
    

}
