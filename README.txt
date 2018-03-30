1. I, Angela English, worked alone on this project.
2. This project took approximately 20 hours for me to do, including writeup.
3. I expected the hash table to be fastest because it requires much less transversal than the BST family of data structures in order to place a new node.
4. Hash is in fact fastest. It has a very similar runtime to BST however, and in some cases might be faster.
5. Hash was much simpler to write from scratch than a BST or especially an AVL. Considering that AVL is both harder to write and slower to build, I believe AVL trees are only preferable in a very large database that is expected to exist for a long time after being built only once. Hash and Tree are comparable in run time, but I believe hash is easier to read, write and debug as it has no recursive algorithms.
6. AVL is extremely slow with large texts. It does well with small documents (a sonnet, for instance) but the weight of balance rotations seem to take a toll on it.
7. After writing working code and processing the-new-atlantis and hamlet, I got a number...that I had absolutely no context for. So I ran some other tests. I compared some of Shakespeare's other works with each other, including A Midsummer Night's Dream, Othello, and a collection of his sonnets. This gave me a control group with which to compare. In the interest of scientific rigor I also downloaded on of Bacon's other essays with which to compare. 

Shakespeare vs. Shakespear gave me a good idea of what a low frequency was, but I still did not know what a high frequency should have been. To obtain this, I compared a collections of Shakespeare texts to texts that certainly could not have been written by Shakespeare, including Catcher in the Rye, Tom Sawyer, the King James Bible, and Oedipus Rex. The full analytical conclusions are present in my submission under "science.txt", but the conclusion was this:
===============================
Average Shakespeare and Shakespear:	1.5397152836551484E-4
Average Shakespeare and Bacon:		2.2641508888806362E-4
Average Shakespeare and Other:		2.2003049984632987E-4
===============================
These results would indicate that Shakespeare's works and Bacon's works are actually less similar then Shakespeare's text and a collection of vastly different texts. Therefore I conclude that Francis Bacon is not the author of Hamlet nor any other works credited to Shakespeare.
8. For my benchmark I calculated System.currentTimeMillis() before and after creating an instance of WordCount and output the difference between the timestamps. 
The 'better' data structure is the one that determines the word occurrence frequency of Hamlet the fastest.
Time is the better measurement for this case, because the amount of space the program takes is negligible.
Code:

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

Controlled groups for the experiment include the file processed(hamlet.txt only). The sorting algorithm does not run on my separate benchmark driver, so it does not need to be taken into consideration. I ran the benchmark driver multiple times to get a good idea of the average runtime of the program with each of the datatypes.
Sources of Error: For some reason the program does not take the exact same time to run each time. I ran multiple trials in order to overcome this, but I do not know why it occurs. I believe it's because milliseconds is a very small unit. 
In any case, AVL takes about 6 seconds while BST and Hash take about one-tenth of a second, so it's clear that AVL is inferior in this case.
Smaller cases are not as bad for AVL, but vocabulary analysis is a problem for large data structures. In the future, to implement a data structure such as this I will probably use a hash table for its simplicity and speed.
9. I really enjoy when projects have a real goal that you must do some problem solving and investigation to find. This project and the sound clip reversal project have that in common, and were very satisfying to complete. I had a bit too much fun comparing the difference metrics between Pride and Prejudice and Catcher in the Rye and other such combinations. However, we did not talk about benchmarking in class before this was assigned and there is no example on how to do it except for some questionably relevant example code and a single page worth of instructions. Internet research on 'benchmarking' yields a collection of very lengthy research pdfs which I also do not think is expected. So I'm not sure what to do for benchmarking. I wish it had been discussed in class prior to the assignment.