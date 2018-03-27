import java.io.IOException;

/**
 * Angela English
 * CSC 130 MW 4-5:15
 * Working alone
 */

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

    private static DataCounter<String> countWords(String file, DataCounter<String> counter) {

        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        } catch (IOException e) {
            System.err.println("Error processing " + file + e);
            System.exit(1);
        }
        
        return counter;

        /*
         * refactoring this because I hate it
        */
    }

    /**
     * Helper function, delete later
     */

	
	public static void DumpArray(DataCount[] array) {
		System.out.print("Size: "+array.length+"\n");
		for(int i=0; i<array.length; i++)
		{
			System.out.print(i+"\t: "+array[i].data+"\t"+array[i].count+"\n");
		}
	}
	
    /**
     * TODO Replace this comment with your own.
     * 
     * Sort the count array in descending order of count. If two elements have
     * the same count, they should be in alphabetical order (for Strings, that
     * is. In general, use the compareTo method for the DataCount.data field).
     * 
     * This code uses insertion sort. You should modify it to use a different
     * sorting algorithm. NOTE: the current code assumes the array starts in
     * alphabetical order! You'll need to make your code deal with unsorted
     * arrays.
     * 
     * The generic parameter syntax here is new, but it just defines E as a
     * generic parameter for this method, and constrains E to be Comparable. You
     * shouldn't have to change it.
     * 
     * @param counts array to be sorted.
     */
    private static <E extends Comparable<? super E>> void sortByDescendingCount(DataCount<E>[] counts) {
    	//System.out.print("Pre sort array:\n");
    	//DumpArray(counts);
        for (int i = 1; i < counts.length; i++) {
            DataCount<E> x = counts[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
            	//System.out.print("i:"+i+"\tj"+j+"\n");
            	try {
                if (counts[j].count >= x.count) {
                    break;
                }
            	}catch(NullPointerException e) {}
                counts[j + 1] = counts[j];
            }
            counts[j + 1] = x;
        }
    }
	public static void usage(){
		System.out.print("Usage:	java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>\n"+
		"-b\tUse an Unbalanced BST to implement the DataCounter\n"+
		"-a\tUse an AVL Tree\n"+
		"-h\tUse a Hashtable\n"+
		"-frequency \tPrint all the word/frequency pairs, ordered by frequency, \n"+
		"\t\tand then by the words in lexicographic order\n"+
		"\n"+
		"-num_unique \tPrint the number of unique words in the document. \n"+
		"\t\tThis is the total number of distinct (different) words in the \n"+
		"\t\tdocument. Words that appear more than onceare only counted \n"+
		"\t\tas a single word for this statistic.\n");
	}
	
    public static void main(String[] args) {
        if (args.length != 3) {
            usage();
            System.exit(1);
        }
        //won't compile unless this is initialized
        DataCounter<String> counter=new BinarySearchTree<String>();
        switch(args[0]) {
        case "-b":
        	counter=new BinarySearchTree<String>();
        	break;
        case "-a":
        	counter=new AVL<String>();
        	break;
        case "-h":
        	counter=new HashTable();
        	break;
        default:
        	usage();
        	System.exit(2);
        }
        
        counter=countWords(args[2], counter);

        System.out.print("Size of counter: "+counter.getSize()+"\n");
    	DataCount<String>[] counts = counter.getCounts();
        //System.out.print(counter.dump());
        sortByDescendingCount(counts);

        System.out.print("Size of counts: "+counts.length+"\n");
        System.out.print("0: "+counts[0]+"\n");
        
        switch(args[1]) {
        case "-frequency":
        case "-f":
            for (DataCount<String> c : counts)
                System.out.println(c.count + " \t" + c.data);
        	break;
        case "-num_unique":
        case "-n":
            for (DataCount<String> c : counts)
                System.out.println(c.data);
        	break;
        default:
        	usage();
        }
        
    }
}
