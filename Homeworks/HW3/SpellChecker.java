import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SpellChecker
{
	private LinkedList<String> dictLines;
	private LinkedList<String> inputLines;

	private myHash hashDict;

	public SpellChecker(String dictionary, String personal, String inputFile)
	{
		hashDict = new myHash();
		dictLines = new LinkedList<String>();
		inputLines = new LinkedList<String>();
		readFile(dictLines, dictionary, personal);
		readDict(dictLines);
		readInputFile(inputLines, inputFile);
		checker(inputLines);
	}

	//converts file lines into a linkedlist
	private LinkedList<String> readFile(LinkedList<String> dictLines, String fileName1, String fileName2)
	{
		File dictionary = new File(fileName1);
		File personal = new File(fileName2);

		// convert first file to a list
		try{
			Scanner input = new Scanner(dictionary);
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				dictLines.add(line);
			}
			Scanner input2 = new Scanner(personal);
			while(input2.hasNextLine())
			{
				String line2 = input2.nextLine();
				dictLines.add(line2);
			}
		}catch(FileNotFoundException e){
			System.out.println("File: " + fileName1 + " or " + fileName2 + " does not exist. Program terminated.");
		}		
		return dictLines;
	}

	private LinkedList<String> readInputFile(LinkedList<String> inputList, String fileName3)
	{
		File inputText = new File(fileName3);
		try{
			Scanner input3 = new Scanner(inputText);

			while(input3.hasNextLine())
			{
				String line = input3.nextLine();
				inputLines.add(line);
			}
		}catch(FileNotFoundException e){
			System.out.println("File: " + fileName3 + " does not exist. Program terminated.");
		}
		return inputLines;
	}

	public void readDict(LinkedList<String> dictLines)
	{
		String word = "";
		for(int i = 0; i < dictLines.size(); i++)
		{
			word = dictLines.get(i).toLowerCase();
			word = word.replaceAll("^[^a-zA-Z']+", "").replaceAll("[^a-zA-Z']+$", "");
			hashDict.insert(word);
		}
	}

	public boolean lookUpWord(String word)
	{
		return hashDict.contains(word);
	}

	public LinkedList<String> checker(LinkedList<String> inputLines)
	{
		LinkedList<String> misspelled = new LinkedList<String>();
		for(int i = 0; i < inputLines.size(); i++)
		{
			String line = inputLines.get(i);
			StringTokenizer tokenizer = new StringTokenizer(line);
			String word = "";
			while(tokenizer.hasMoreTokens())
			{
				word = (tokenizer.nextToken()).toLowerCase();
				word = word.replaceAll("^[^a-zA-Z']+", "").replaceAll("[^a-zA-Z']+$", ""); 
				// first replace all removes leading punctuation, second replace all removes trailing punctuation
				if(!lookUpWord(word))
				{
					StringBuilder testWords = new StringBuilder();
					testWords.append(word + ", Line: " + (i+1) + ", Suggestions: ");
					
					String suggestion = suggestWords(word);
					testWords.append(suggestion);
					
					misspelled.add(testWords.toString());
				}
			}
		}
		if(misspelled.isEmpty() || inputLines.isEmpty())
			System.out.println("There are no spell check errors.");
		for(int j = 0; j < misspelled.size(); j++)
			System.out.println(misspelled.get(j));
		return misspelled;
	}

	public String suggestWords(String wrongWord)
	{
		LinkedList<String> results = new LinkedList<String>();

		// Add one character
		for(int i = 0; i < (wrongWord.length() + 1); i++)
		{
			for(char c = 'a'; c <= 'z'; c++)
			{
				StringBuilder newWord = new StringBuilder(wrongWord);
				newWord.insert(i,c);
				if(lookUpWord(newWord.toString()))
				{
					if(!results.contains(newWord.toString()))
						results.add(newWord.toString());
				}
			}
		}

		// Remove one character 
		for(int i = 0; i < (wrongWord.length()); i++)
		{
			StringBuilder newWord = new StringBuilder(wrongWord);
			newWord.deleteCharAt(i);
			if(lookUpWord(newWord.toString()))
			{
				if(!results.contains(newWord.toString()))
					results.add(newWord.toString());
			}
		}

		// Exchange adjacent characters
		for(int i = 0; i < (wrongWord.length() - 1); i++)
		{
			StringBuilder newWord = new StringBuilder(wrongWord);
			char temp = newWord.charAt(i);
			newWord.setCharAt(i, newWord.charAt(i+1));
			newWord.setCharAt(i+1, temp);
			if(lookUpWord(newWord.toString()))
			{
				if(!results.contains(newWord.toString()))
					results.add(newWord.toString());
			}
		}
		
		String suggestion = "";
		if(results.isEmpty())
			suggestion = "Could not find any suggestions";
		else 
			suggestion = results.toString().substring(1, results.toString().length() - 1);
		return suggestion;
	}

}