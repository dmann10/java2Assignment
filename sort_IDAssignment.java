import java.util.*;
import java.io.*;

public class sort_IDAssignment {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner inputFile = new Scanner(new File("C:/Users/dylan_000/Documents/sort_IDAssignment/sortInput.txt"));
		ArrayList<String> listOfIds = new ArrayList<String>();
		ArrayList<String> sortedListOfIds = new ArrayList<String>();

		while(inputFile.hasNext())
		{
			String str = inputFile.nextLine();
			listOfIds.add(str);
		}

		ArrayList<String> letterArray = findLetters(listOfIds);
		ArrayList<Integer> numArray = findNumbers(listOfIds);

		// Add letter and number arrays to new sorted id list
		for(int x = 0; x < letterArray.size(); x++)
			sortedListOfIds.add(letterArray.get(x));
		for(int y = 0; y < numArray.size(); y++)
			sortedListOfIds.add(Integer.toString(numArray.get(y)));

		// Write sorted array to output file
		PrintWriter outputFile = new PrintWriter(new File("output.csv"));
		for(int z = 0; z < sortedListOfIds.size(); z++)
		{	
			outputFile.println(sortedListOfIds.get(z));
		}
		outputFile.close();

	}

	/* Find all ids starting with letters from original id list and 
	   create a list of those ids. Sort those ids and return them to main	
	*/
	public static ArrayList<String> findLetters(ArrayList<String> listOfIds) {

		ArrayList<String> letterIds = new ArrayList<String>();

		// Pick out letter ids and add them to a new list
		for(int i = 0; i < listOfIds.size(); i++)
		{
			char firstIdChar = listOfIds.get(i).charAt(0);
			if(Character.isLetter(firstIdChar))
			{
				letterIds.add(listOfIds.get(i));
			}
		}

		sortLetters(letterIds);

		return letterIds;
	}

	// Sort list of ids starting with a letter
	public static void sortLetters(ArrayList<String> letterArray) {

		int order;

		for (int i = 0 ; i < letterArray.size(); i++)
		{
			for(int x = 0 ; x < letterArray.size() ; x++)
			{
				String str = letterArray.get(i);
				order = str.compareToIgnoreCase(letterArray.get(x));
				
				if (order > 0)
				{
					letterArray.set(i, str);
				}
				else if (order < 0)
				{
					letterArray.set(i, letterArray.get(x));
					letterArray.set(x, str);
				}	
			}
		}

	}

	/* Finds all ids starting with a number, creates a list of those ids, sorts them
	   and returns the list to main	
	*/
	public static ArrayList<Integer> findNumbers(ArrayList<String> listOfIds) {

		ArrayList<Integer> numArray = new ArrayList<Integer>();

		for(int i = 0; i < listOfIds.size(); i++)
		{
			char firstIdChar = listOfIds.get(i).charAt(0);
			if(Character.isDigit(firstIdChar))
			{
				numArray.add(Integer.parseInt(listOfIds.get(i)));
			}
		}

		sortNumbers(numArray);

		return numArray;
	}

	// Sort the list of number ids
	public static void sortNumbers(ArrayList<Integer> numArray) {

		for(int x = 0; x < numArray.size(); x++)
		{
			int smallestIndex = getSmallestIndex(x, numArray);
			swapPlaces(smallestIndex, numArray, x);
		}
	}

	public static int getSmallestIndex(int x, ArrayList<Integer> array) {

		int smallestIndex = x;

		for(int i = x; i < array.size(); i++)
		{
			if(array.get(i) < array.get(smallestIndex))
			{
				smallestIndex = i; 
			}
		}
		return smallestIndex;
	}

	public static void swapPlaces(int smallestIndex, ArrayList<Integer> array, int index) {

		int placeholder = array.get(index);
		array.set(index, array.get(smallestIndex));
		array.set(smallestIndex, placeholder);
	}
}