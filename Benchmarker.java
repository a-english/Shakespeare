//import java.io.IOException;

public class Benchmarker {

	public static void main(String[] args) {
		String filename="texts/hamlet.txt";

		System.out.println("Time taken on BST:\t"+time("-b",filename)+"ms");
		System.out.println("Time taken on AVL:\t"+time("-a",filename)+"ms");
		System.out.println("Time taken on hash:\t"+time("-h",filename)+"ms");
	}
	
	private static long time(String structureType, String filename) {
		long before, after;
		//BST
		before= System.currentTimeMillis();
		new WordCount(filename, structureType);
		after=System.currentTimeMillis();
		return after-before;
	}

}
