
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
        	   outputType = args[1],
        	   filename1 = args[2];
		DataCounter dc1, dc2;
		System.out.print("Denominator: " + FrequencyDivisor +"\n");
		//dc1=WordCount.countWords(args[2], dc1);
	}

}
