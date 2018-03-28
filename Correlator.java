
public class Correlator {

	public static final Double Frequency = 0.001;
	public static final int FrequencyDivisor = (new Double(1/Frequency)).intValue();
	
	public static void usage(){
		System.out.print("This program compares the word frequency of two different txt files.\n"+
		"Usage:	java Correlator [ -b | -a | -h ] <filename1> <filename2>\n"+
		"-b\tUse an Unbalanced BST to implement the DataCounter\n"+
		"-a\tUse an AVL Tree\n"+
		"-h\tUse a Hashtable\n");
	}

	public static void main(String[] args) {
        if (args.length != 3) {
            usage();
            System.exit(1);
        }
        String structureType = args[0],
        	   filename1 = args[1],
        	   filename2 = args[2];
		DataCount<String>[] dc1, dc2;
		//oh my god that's ugly
		dc1=(new WordCount(filename1, structureType).getCount().getCounts());
		dc2=(new WordCount(filename2, structureType).getCount().getCounts());
		//dc1 and dc2 now contain separate arrays populated with words, sorted alphabetically.
		int length;
		if(dc1.length>dc2.length)
			length=dc1.length;
		else	length=dc2.length;
		
		/*
		for(int i=0; i<length; i++){
			System.out.println(dc1[i].data+" "+dc1[i].count+"\t"+dc2[i].data+" "+dc2[i].count);
		}
		*/
		
		System.out.print("Denominator: " + FrequencyDivisor +"\n");
		
	}
	/**
	 * Function uses a binary search algorithm to find key in array.
	 * TODO NOT TESTED
	 * @param array	- array to be searched
	 * @param key	- query to be found
	 * @return index of key if found, or -1 if not found.
	 */
	public static int find(DataCount<String>[] array, String key)
	{
		int index=-1;
		int min=0, max=array.length-1, mid;
		boolean found=false;
		while(!found&&(min<max)) {
			
			mid=(min+max)/2;
			String current=array[mid].data;
			
			if(current.equals(key)) {
				index=mid;
				found=true;
			}
			else
			{
				if(current.compareTo(key)>0){	//follows
					min=mid+1;
				}
				else {	//precedes
					max=mid-1;
				}
			}
		}
		return index;
	}

}
