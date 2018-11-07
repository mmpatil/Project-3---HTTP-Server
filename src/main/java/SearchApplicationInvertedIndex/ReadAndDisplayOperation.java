package SearchApplicationInvertedIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ReadAndDisplayOperation {

	/**
	 * Reads each json element from the file passes to intoInvertedIndex function of InvertedIndex class 
	 * and createAsinHashMap function of AsinHashMap class.
	 * @param <T>
	 * 
	 * @param path - Path of the file containing json elements.
	 * @param type - The type of T the file contains (Review OR QA).
	 * @param asinList - The asin HashMap to be created.
	 * @return - The updated inverted index.
	 */
	public static <T> InvertedIndex readFile(Path path,T type)
	{
		InvertedIndex index = InvertedIndex.getInstance();
		String[] splitLine;
		Gson gson = new Gson();
		long count = 0;
		try(BufferedReader reader = Files.newBufferedReader(path,Charset.forName("ISO-8859-1"))
				){
			String line;

			while((line = reader.readLine()) != null)
			{
				if(count == 10000) {
					break;
				}
				count++;
				if(type instanceof Review) {
					try {
						type = (T) gson.fromJson(line, Review.class);
					} 
					catch(JsonSyntaxException jse) {
						System.out.println("Skipping...");
					}
					splitLine = StringOperations.split(((Review) type).getReviewText().toString());
					index.createAsinHashMapForReview(((Review)type).getAsin(), ((Review)type));
					for(String term : splitLine){
						term = StringOperations.processTerm(term);
						if(!(term.equals("")))
							index.intoInvertedIndex(term,(Review)type);
					}
				}
				else {
					try {
						type = (T) gson.fromJson(line, QA.class);
					} 
					catch(JsonSyntaxException jse) {
						System.out.println("Skipping...");
					}
					index.createAsinHashMapForQA(((QA)type).getAsin(), ((QA)type));
				}
			}

		}catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}

	/**
	 * Validates the user input.
	 * 
	 * @param input - The user input.
	 * @return - True if the user input is valid.
	 * 			 False if the user input is not valid.
	 */
	public static boolean validateInput(String[] input) {
		if((input.length < 2  && (!input[0].toLowerCase().equals("exit"))) || input.length > 2)
			return false;
		return true;
	}
}
