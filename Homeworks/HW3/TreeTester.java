public class TreeTester
{
	public static void main(String[] args)
	{
		if(args.length < 1)
		{
			System.out.println("Error: Please enter a file on the command line.");
			System.exit(1);
		}
		String file = args[0];
		new BuildTree(file);
	}
}