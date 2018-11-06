package SearchApplicationInvertedIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class InvertedIndex {

	private static InvertedIndex index;
	private HashMap <String,HashMap<Review, Integer>> invertedIndex = new HashMap <String,HashMap<Review, Integer>>();
	private HashMap <String,LinkedHashMap<Review, Integer>> sortedInvertedIndex = new HashMap <String,LinkedHashMap<Review, Integer>>();
	private HashMap<String,ArrayList<Review>> asinDetailsForReview = new HashMap<String,ArrayList<Review>>();
	private HashMap<String,ArrayList<QA>> asinDetailsForQA = new HashMap<String,ArrayList<QA>>();

	private InvertedIndex() {
	}

	public static InvertedIndex getInstance() {
		if(index == null) {
			index = new InvertedIndex();
		}
		return index;
	}

	/**
	 * Inserts the term in the inverted index with the document containing the term if the term does not exist in the inverted index 
	 * and sets frequency to 1 else increments the frequency.
	 * 
	 * @param term - Key in inverted index.
	 * @param obj - Object/Document that contains the term.
	 * @return 
	 */
	public void intoInvertedIndex(String term,Review review) {
		HashMap<Review, Integer> document = null;
		if(invertedIndex.containsKey(term) == false) {
			document = new HashMap<Review, Integer>();
			document.put(review, 1);
		}
		else {
			document = invertedIndex.get(term);
			if(document.containsKey(review)) {
				document.put(review,(document.get(review)+1));
			}
			else
				document.put(review, 1);
		}
		invertedIndex.put(term, document);
	}

	/**
	 * Searches for the exact term in the inverted index and 
	 * passes the result to the Output function of ReadAndDisplayOperation class.
	 * 
	 * @param term - Key in inverted index to be search.
	 * @return 
	 */
	public ArrayList<Review> search(String term) {
		HashMap<Review,Integer> value;
		ArrayList<Review> details = null;
		LinkedHashMap<Review,Integer> sorted = new LinkedHashMap<Review,Integer>();
		if(sortedInvertedIndex.containsKey(term)) {
			value = sortedInvertedIndex.get(term);
			details = new ArrayList(value.keySet());
		}
		return details;
	}

	public ArrayList<Review> searchAll(String term){
		ArrayList<Review> allDetails = new ArrayList<Review>();
		ArrayList<Review> details = null;
		if(term.contains("+")) {
			String[] terms = term.split("\\+");
			for(String query : terms) {
				details = search(StringOperations.processTerm(query));
				if(details != null) {
					allDetails.addAll(details);
				}
			}
		}
		else {
			allDetails = search(StringOperations.processTerm(term));
		}
		return allDetails;
	}

	/**
	 * Sorts the objects in the hashmap according to the frequency of term occurrences.
	 * 
	 * @param unsortedMap - Unsorted Hashmap to be sorted containing object and frequency.
	 * @return HashMap which is sorted.
	 */
	public static LinkedHashMap<Review,Integer> sort(HashMap<Review,Integer> unsortedMap){
		List<HashMap.Entry<Review,Integer>> unsortedList = new LinkedList<HashMap.Entry<Review,Integer>>(unsortedMap.entrySet()); 
		Collections.sort(unsortedList, new Comparator<HashMap.Entry<Review,Integer>>(){
			public int compare(Map.Entry<Review, Integer> object1,
					Map.Entry<Review, Integer> object2) {
				return object2.getValue().compareTo(object1.getValue());
			}
		});
		LinkedHashMap<Review,Integer> sortedMap = new LinkedHashMap<Review,Integer>();
		for(Map.Entry<Review, Integer> aa :unsortedList) {
			sortedMap.put(aa.getKey(), aa.getValue());
		}
		return sortedMap;
	}

	/**
	 * Sorts the objects in the hashmap according to the frequency of term occurrences.
	 * 
	 */
	public void sortAll() {
		ArrayList<String> terms = new ArrayList(invertedIndex.keySet());
		for(String term : terms) {
			LinkedHashMap<Review,Integer> sorted = sort(invertedIndex.get(term));
			sortedInvertedIndex.put(term, sorted);
		}
		invertedIndex = null;
	}

	/**
	 * Inserts an Asin and object in which it appears into the HashMap
	 * 
	 * @param asin - Contains the asin
	 * @param object - String containing the details of the object
	 * @return 
	 */
	public void createAsinHashMapForReview(String asin, Review review) {
		ArrayList<Review> details;
		if(asinDetailsForReview.containsKey(asin) == false) {
			details =  new ArrayList<Review>();
			details.add(review);
		}
		else {
			details = asinDetailsForReview.get(asin);
			details.add(review);
		}
		asinDetailsForReview.put(asin, details);
	}

	/**Searches the HashMap for a particular Asin and passes the result to the Output function of ReadAndDisplayOperation class.
	 * 
	 * @param asin - Contains the Asin
	 * @param obj - String containing the details of the object.
	 * @return 
	 */
	public ArrayList<Review> getReviewsAndQAsForReviews(String asin) {
		ArrayList<Review> list = null;
		if(asinDetailsForReview.containsKey(asin)) {
			list = asinDetailsForReview.get(asin);
		}
		return list;
	}

	/**
	 * Inserts an Asin and object in which it appears into the HashMap
	 * 
	 * @param asin - Contains the asin
	 * @param object - String containing the details of the object
	 * @return 
	 */
	public void createAsinHashMapForQA(String asin, QA qa) {
		ArrayList<QA> details;
		if(asinDetailsForQA.containsKey(asin) == false) {
			details =  new ArrayList<QA>();
			details.add(qa);
		}
		else {
			details = asinDetailsForQA.get(asin);
			details.add(qa);
		}
		asinDetailsForQA.put(asin, details);
	}

	/**Searches the HashMap for a particular Asin and passes the result to the Output function of ReadAndDisplayOperation class.
	 * 
	 * @param asin - Contains the Asin
	 * @param obj - String containing the details of the object.
	 * @return 
	 */
	public ArrayList<QA> getAsinDetailsForQA(String asin) {
		ArrayList<QA> list = null;
		if(asinDetailsForQA.containsKey(asin)) {
			list = asinDetailsForQA.get(asin);
		}
		return list;
	}
}