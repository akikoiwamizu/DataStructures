import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BuildTree
{
	private LinkedList<String> linesList;
	private AVL<String> treeWords;
	
	
	public BuildTree(String fileName)
	{
		linesList = new LinkedList<String>();
		readFile(linesList, fileName);
		treeWords = makeTree(linesList);
		treeWords.printTree();
	}
	
	//converts file lines into a linkedlist
	private LinkedList<String> readFile(LinkedList<String> list, String fileName)
	{
		File file = new File(fileName);

		try{
			Scanner input = new Scanner(file);
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				linesList.add(line);
			}
		}catch(FileNotFoundException e){
			System.out.println("File: " + fileName + " does not exist. Program terminated.");
		}	
		return linesList;
	}
	
	private AVL<String> makeTree(LinkedList<String> list)
	{
		AVL<String> textTree = new AVL<String>();
		for(int i = 0; i < list.size(); i++)
		{
			String line = list.get(i);
			StringTokenizer tokenizer = new StringTokenizer(line);
			String word;
			
			while(tokenizer.hasMoreTokens())
			{
				word = (tokenizer.nextToken()).toLowerCase();
				word = word.replaceAll("^[^a-zA-Z']+", "").replaceAll("[^a-zA-Z']+$", ""); 
				// first replace all removes leading punctuation, second replace all removes trailing punctuation
				textTree.insert(word,(i+1)); // inserts the word and its' location into the tree
			}
		}
		return(textTree);
	}
}