package SearchApplicationInvertedIndex;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class AmazonSearch {

	public static void main(String[] args) {

		System.out.println("Creating inverted indices");
		InvertedIndex reviews = createInvertedIndex("Cell_Phones_and_Accessories_5.json","qa_Cell_Phones_and_Accessories.json");
		System.out.println("Inverted index created");
		System.out.println("Exiting the program.....");
	}

	/**
	 * Calls the intoInvertedIndex method of InvertedIndex class to create inverted indices for both the files.
	 * 
	 * @param args1 - Path for Review file.
	 * @param args2 - Path for QA file.
	 * @return - A hashmap containing both the inverted indices.
	 */
	public static InvertedIndex createInvertedIndex(String arg1,String arg2) {

		Path path1 = Paths.get(arg1);	
		Review r = new Review();
		QA qa = new QA();
		InvertedIndex reviews = ReadAndDisplayOperation.readFile(path1,r);
		path1 = Paths.get(arg2);
		InvertedIndex qas = ReadAndDisplayOperation.readFile(path1,qa);
		qas.sortAll();
		return qas;
	}
}