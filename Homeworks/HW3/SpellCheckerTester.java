public class SpellCheckerTester
{
	public static void main(String[] args)
	{
		if(args.length < 3)
		{
			System.out.println("Error: Please enter two files on the command line.");
			System.exit(1);
		}
		String dictionary = args[0];
		String personal = args[1];
		String inputFile = args[2];
		new SpellChecker(dictionary, personal, inputFile);
	}
}