
public class Correlator {

	public static final Double upperBound = 0.01;
	public static final Double lowerBound = 0.0001;
	
	public static void usage(){
		System.out.print("This program compares the word frequency of two different txt files.\n"+
		"Usage:	java Correlator [ -b | -a | -h ] <filename1> <filename2>\n"+
		"-b\tUse an Unbalanced BST in the backend\n"+
		"-a\tUse an AVL Tree in the backend\n"+
		"-h\tUse a Hashtable in the backend\n");
	}

	/**
	 * My test function, delete later
	 */
	private static void pseudomain() {
		String[] Shakespeares= {"hamlet","midsummer","sonnets"};
		String[] Bacons= {"the-new-atlantis","bacon"};
		String[] Other= {"bible","oedipus", "catcher-in-the-rye"};
		
		double shakespeare_shakespeare,shakespeare_bacon, shakespeare_other;
		for(String SSfilename : Shakespeares) {
			
		}
	}
	
	public static void main(String[] args) {
		if(args.length==0) {
			pseudomain();
		}
        if (args.length != 3) {
            usage();
            System.exit(1);
        }
        String structureType = args[0],
        	   filename1 = args[1],
        	   filename2 = args[2];
		//dc1 and dc2 now contain separate arrays populated with words, sorted alphabetically.
		
		double diffMetric=getDifferenceMetric(args[0], args[1], args[2]);
		
		System.out.print("Difference metric between "+filename1+" and "+filename2+": "+diffMetric+
				"\n\tUsing normalizers ("+lowerBound+","+upperBound+")");
		
	}
	
	/**
	 * Finds difference metric by taking the Euclidean distance between two frequencies.
	 */
	
	/**
	 * 
	 * @param dc1 Array to be compared
	 * @param dc2 Array to be compared
	 * @returns difference metric
	 */
	//private static double getDifferenceMetric(DataCount<String>[] dc1, DataCount<String>[] dc2) {
	private static double getDifferenceMetric(String structureType, String filename1, String filename2) {
		DataCount<String>[] dc1, dc2;
		//oh my god that's ugly
		dc1=(new WordCount(filename1, structureType).getCount().getCounts());
		dc2=(new WordCount(filename2, structureType).getCount().getCounts());
		int index;
		String word;
		double freq1, freq2;
		double diffMetric=0;
		for(DataCount<String> entry : dc1) {
			word=(String) entry.data;
			index=find(dc2, word);
			if(index!=-1) {
				freq1=getFrequency(word, dc1);
				freq2=getFrequency(word, dc2);
				if(		(freq1>lowerBound&&freq1<upperBound)&&
						(freq2>lowerBound&&freq2<upperBound)		) {
					diffMetric+=((freq1-freq2)*(freq1-freq2));
					
				}
				
			}
		}
		return diffMetric;
	}
	
	/**
	 * Finds the percentage of the text that is composed of this word
	 * @param word
	 * @param document
	 * @return percentage
	 */
	private static double getFrequency(String word, DataCount<String>[] document) {
		int index=find(document, word);
		if(index==-1)
			return 0;
		double freq=document[index].count;
		double total=getWordCount(document);
		//System.out.print("'"+word+"' : "+freq+"/"+total+"\n" );
		return freq/total;
	}
	
	/**
	 * Finds the total number of words in document based on data structure
	 * @param DataCount array
	 * @return word count
	 */
	
	private static int getWordCount(DataCount<String>[] document) {
		int sum=0;
		for(int i=0; i<document.length; i++) {
			sum+=document[i].count;
		}
		return sum;
	}
	
	
	/**
	 * Function uses a binary search algorithm to find key in array.
	 * @param array	- array to be searched
	 * @param key	- query to be found
	 * @return index of key if found, or -1 if not found.
	 */
	public static int find(DataCount<String>[] array, String key)
	{
		//System.out.println(" Searching for "+key.data);
		int index=-1;
		int min=0, max=array.length-1, mid;
		boolean found=false;
		while(!found&&(min<max)) {
			
			mid=(min+max)/2;
			String current=array[mid].data;
			//System.out.println(" Comparing "+current+" and "+key.data);
			if(current.equals(key)) {
				index=mid;
				found=true;
			}
			else
			{
				if(current.compareTo(key)<0){	//follows
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
