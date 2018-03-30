import java.io.IOException;

public class Benchmarker {

	public static void main(String[] args) {
		String filename="texts/hamlet.txt";

		long before, after;
		//BST
		before= System.currentTimeMillis();
		new WordCount(filename, "-b");
		after=System.currentTimeMillis();
		System.out.println("Time taken on BST: "+(after-before)+"ms");
		//AVL
		before= System.currentTimeMillis();
		new WordCount(filename, "-a");
		after=System.currentTimeMillis();
		System.out.println("Time taken on AVL: "+(after-before)+"ms");
		//Hash
		before= System.currentTimeMillis();
		new WordCount(filename, "-h");
		after=System.currentTimeMillis();
		System.out.println("Time taken on Hash table: "+(after-before)+"ms");
		
	}

}
