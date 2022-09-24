import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SearchHelperDriver {

	public static void main(String[] args) {
		try {
			/*
			 * Main Test Cases
			 */
			ArrayList<String> al1 = SearchHelper.loadLines("love_and_honor.txt");
			assert al1.size() == 9;
			assert al1.get(8).compareTo("Forever and a day.") == 0;
			System.out.println("Success1");
			
			ArrayList<String> res = SearchHelper.search(al1, "the");
			assert res.size() == 3;
			assert res.get(2).compareTo("Alma mater now we praise thee,") == 0;
			System.out.println("Success2");
			
			res = SearchHelper.search(al1, "blah");
			assert res.size() == 0;
			System.out.println("Success3");
			
			res = SearchHelper.search(al1, "?h?");
			assert res.size() == 6;
			assert res.get(5).compareTo("Love and honor to Miami,") == 0;
			System.out.println("Success4");
			
			al1 = SearchHelper.loadLines("alma_mater.txt");
			assert al1.size() == 22;
			res = SearchHelper.search(al1, "?he?");
			assert res.size() == 9;
			assert res.get(8).compareTo("Crimson tow'rs against the sky;") == 0;
			System.out.println("Success5");
			
			/*
			 * Other Test Cases
			 */
			
			assert SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "abcdk");
			assert SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "?bcdk");
			assert SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "?????");
			assert SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "?");
			assert SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "fasfs");
			assert SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "fasfs?");
			assert !SearchHelper.containsPhrase("abcdkjhdfkjhasdkjfhakdsfasfs", "fadsfasfsafss");
			
			System.out.println("Success6");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
