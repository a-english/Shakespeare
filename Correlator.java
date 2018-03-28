
public class Correlator {

	
	public static void usage(){
		System.out.print("This program compares the word frequency of two different txt files.\n"+
		"Usage:	java Correlator [ -b | -a | -h ] [ -frequency | -num_unique ] <filename1> <filename2>\n"+
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
		DataCounter dc1, dc2;
		
		dc1=WordCount.countWords(args[2], dc1);
	}

}
