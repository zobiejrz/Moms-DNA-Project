import java.io.*;
import java.util.*;
	
public class Primary {
	
	public static void main(String[] args) {

		String file = "All DNA Matches.csv";
		LinkedList<String> originalList = populateListFromFile(file);
		
		file = "DNA Matches to Exclude.csv";
		LinkedList<String> comparisonList = populateListFromFile(file);
		
		LinkedList<String> notFoundList = new LinkedList<String>();
		
		// Object to process two different lists, and add them to their
		// respective LinkedList (Data or ComparisonList)
		
//		populateOriginalList(originalList);
//		populateComparisonList(comparisonList);
		
		// loop through the kit numbers in list A
		for (int originalListItr = 0; originalListItr < originalList.size(); originalListItr++) {
			
			boolean canSave = true;
			// loop through kit numbers in list B, and
			for (int comparisonListItr = 0; comparisonListItr < comparisonList.size(); comparisonListItr++) {
				
				// compare them to the current kit number in list A
				// If both kit numbers are the same
				
				String originalLineVal = originalList.get(originalListItr);
				String comparisonLineVal = comparisonList.get(comparisonListItr);
				boolean doLinesMatch = doStringValsMatch(originalLineVal, comparisonLineVal);
				
				if (doLinesMatch) {
					// Indicates that the current Data.get(DLnum) should NOT be
					// saved, b/c it can be found in ComparisonList
//					System.out.println(">" + originalList.get(originalListItr) + "< is equal to >"
//							+ comparisonList.get(comparisonListItr) + "<");
					canSave = false;
					break;
				} else {
//					System.out.println(">" + originalList.get(originalListItr) + "< is NOT equal to >"
//							+ comparisonList.get(comparisonListItr) + "<");
				}
			}
			if (canSave) // If we reach the end of list B, and the current list
							// A number is not found, save it
			{
//				System.out.println(">" + originalList.get(originalListItr)
//						+ "< was only found in originalList. Adding it to notFoundList.");
				notFoundList.add(originalList.get(originalListItr));
			}
			
		}
		
		outputResults(notFoundList);
	}

	private static boolean doStringValsMatch(String originalLineVal, String comparisonLineVal) {
		boolean valuesMatch = false;
		
		String[] origVals = originalLineVal.split(",");
		String[] compVals = comparisonLineVal.split(",");
		
		valuesMatch = origVals[0].equals(compVals[0]);
		
		if (valuesMatch)
		{
//			System.out.println("MATCH!!  -  origVals[0]: " + origVals[0] + "\tcompVals[0]: " + compVals[0]);
		} else {
//			System.out.println("NO MATCH!!  -  origVals[0]: " + origVals[0] + "\tcompVals[0]: " + compVals[0]);
		}
		
		return valuesMatch;
	}
	
	private static void outputResults(LinkedList<String> notFoundList) {
		// Save LinkedList Output to a .txt doc (new object)

		String file = "Autumn's File.csv"; 
		try (PrintWriter out = new PrintWriter(file)) {
			for (int i = 0; i <= notFoundList.size() - 1; i++) {
//				System.out.println("Output: " + notFoundList.get(i));
				out.println(notFoundList.get(i));
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
		}
		
		System.out.println ("File: >" + file + "< created." );
	}
	
	
	private static LinkedList<String> populateListFromFile (String file) {
		
		System.out.println (">" + file + "< is being read.");
		
		LinkedList<String> tempList = new LinkedList<String>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = in.readLine()) != null)
			{
				tempList.add(line);
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("File: >" + file + "< read with " + tempList.size() + " lines.");
		return tempList;
	}
	
}