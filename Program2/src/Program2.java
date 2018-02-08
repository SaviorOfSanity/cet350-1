/*
Andrew Siddall
Nick Cavalancia
Josh Moore
Technical Computing with Java
CET 350
Group Number: 5
sid1134@calu.edu
cav9904@calu.edu
moo6486@calu.edu
Program2.java
*/

import java.io.*;
import java.util.*;

public class Program2 {
    public static void main (String[] args) throws IOException {
        //variable declaration
        int count = 0, sum = 0;
        boolean ok = true;
        String word = null;  									//single parsed word
        String inBuffer = null;                 				//string to hold buffered data
        String inputFileName = null;            				//to store input filename
        String outputFileName = null;           				//to store output filename
        String delims = " ,./;:\"[{]}\\|`~!@#$%^&*()_+=\n\t";	//delimiters for StringTokenizer
        int arraySize = 100;									//max number of words
        Word[] words = new Word[arraySize];     				//array of "words"
        BufferedReader inFile;                  				//to read in from the file
       // PrintWriter outFile;                    				//to print to the file
		boolean found = false;
		String choice = "";

        //for keyboard input
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		

		
        //check to see if any command line arguments were entered and save them
        if (args.length > 1) {
            inputFileName = args[0];
            outputFileName = args[1];
			//If output file is found presents user with a menu
			choice = menu(outputFileName, stdin, found, ok);  //funtion returns choice
			if (choice.equals("quit")){
				ok = false;
				found = true;
			}
			else if (choice.equals("1")){  //choice 1 was to enter a new filename
				outputFileName = stdin.readLine();
				if (outputFileName.length() == 0) {  //if empty, close program
				ok = false;
				found = true;
				}
			}
				
        }
		else if (args.length == 1) {  //1 command line argument, input file name
			inputFileName = args[0];
			System.out.println("Please enter an output file name: ");
			outputFileName = stdin.readLine();
			if (outputFileName.length() == 0) {
				ok = false;
				found = true;
			}
			choice = menu(outputFileName, stdin, found, ok);
			if (choice.equals("quit")){
				ok = false;
				found = true;
			}
			else if (choice.equals("1")){
				outputFileName = stdin.readLine();
				if (outputFileName.length() == 0) {
				ok = false;
				found = true;
				}
			}
		}
		else //no file names supplied
		{
			System.out.println("Please enter an input file name: ");  //asks user to give a input file name because no were provided
			inputFileName = stdin.readLine();
				if (inputFileName.length() == 0) {
				ok = false;
				found = true;
				}else {
					System.out.println("Please enter an output file name: ");
					outputFileName = stdin.readLine();
					if (outputFileName.length() == 0) {
						ok = false;
						found = true;
					}
					choice = menu(outputFileName, stdin, found, ok);
					if (choice.equals("quit")){
						ok = false;
						found = true;
					}
					else if (choice.equals("1")){
						outputFileName = stdin.readLine();
						if (outputFileName.length() == 0) {
						ok = false;
						found = true;
						}
					}				
				}
		}
		

		
		
		//at this point there are two file names, try their validity
		while (found == false){  //checks if input file exists, starts at false
			found = true;  // true unless there is a FileNotFoundException
			try {  //try to open file and determines if it exists
				inFile = new BufferedReader(new FileReader(inputFileName)); //Creates a ReadFile
				
				inBuffer = inFile.readLine();


		
		while (inBuffer != null) {   //While not EOF read lines and parse data
			if (ok) {
				
				
				StringTokenizer newLine = new StringTokenizer(inBuffer, delims);  //Starts a stringtokenizer

				while (newLine.hasMoreTokens()) {  //while there are more tokens
					word = newLine.nextToken().toLowerCase();
					boolean worked = isInt(word); //checks if the token can be converted to an int
					if (worked) {
						sum+=Integer.parseInt(word);  //if so  add to sum
						
					}
					else { //if not must be a word
						int index = findWord(words, word, count);  //checks if it exists
						if (index >= 0) {
							words[index].addOne();  //if so adds one to count
							
						}
						else if (index == -1 && count < arraySize ) { //if it doesn't exist then add it to array
							words[count] = new Word(word);
							count++;
							
						}
					}
				}
			
		

			
			}
		inBuffer = inFile.readLine();  //Reads next line
		}  //END While
		writeToFile(words, count, outputFileName, sum, choice);  //calls the write function
		

		//Closing of files
		inFile.close();  //closes the file being read
		
			}catch(FileNotFoundException e) {  //catches the exception
			//System.out.println(e);  //for debugging could be useful so just commented out. Will display the error
			System.out.println("Input File Not Found! Please select another input file: ");  //input file doesn't exist so ask for a new one
			
			inputFileName = stdin.readLine();
			if (inputFileName.length() == 0) {  //if empty string the quit program
				ok = false;
				found = true;
			}
			else
				found = false;
			}
			
		}
		
    } //end of main

    //BEGIN isInt
    public static boolean isInt (String word) {
        try {                                   //try parsing the int
            int i = Integer.parseInt(word);
            //it worked
            return true;
        }
        catch (NumberFormatException e) {
            //it failed
            return false;
        }
    }//END isInt

    //BEGIN findWords
    public static int findWord(Word words[], String word, int count) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            String curWord = words[i].getWord();
            if (curWord.equals(word)) {
                index = i;
            }
        }
        return index;
    }//END findWords
	
	//BEGIN writeToFile
	public static void writeToFile(Word words[], int count, String outputFileName, int sum, String choice){
		//Writes to a File the contains of words 
		try{
	
		if (choice.equals("-1") || choice.equals("1") || choice.equals("3")){  //these choices pretty much all overwrite the file, -1 is no file, 1 is a new file, 3 is overwrite
			
			PrintWriter outFile = new PrintWriter(new FileWriter(outputFileName)); //Creates a PrintWriter to write to a file after the checks
			
			for (int i = 0; i < count; i++) {  //loops through array and writes to file
			//for file writing

			outFile.println("Word: " + words[i].getWord());
			outFile.println("Count: " + words[i].getCount()); //Writes to a file the contents of words[]

		
			}
			outFile.println("\n\nTotal Amount of Unique Words: " + count);
			outFile.println("Sum is: " + sum);
			outFile.close();  //closes open output file stream
			
		}else {
			backupFile(outputFileName);  //for backup option
			PrintWriter outFile = new PrintWriter(new FileWriter(outputFileName)); //Creates a PrintWriter to write to a file after the checks
			
			for (int i = 0; i < count; i++) {  //loops through array and writes to file
			//for file writing

			outFile.println("Word: " + words[i].getWord());
			outFile.println("Count: " + words[i].getCount()); //Writes to a file the contents of words[]

		
			}
			outFile.println("\n\nTotal Amount of Unique Words: " + count);
			outFile.println("Sum is: " + sum);
			outFile.close();  //closes open output file stream
		}
		}catch (IOException e){
			System.out.println(e);  //checks for exception and prints error
		}
		
	}
	
	//checks if the output file already exists
	public static boolean checkIfExist(String fileName) throws IOException{
		try {
				BufferedReader test = new BufferedReader(new FileReader(fileName)); //Creates a ReadFile
				test.close();
			}catch(FileNotFoundException e) {
				return false; //returns false if no exception
				
			
		}
		return true;  //otherwise file must exist
		
	}
	
	
	//backups the file and renames it as a backup
	public static void backupFile(String fileName) throws IOException{
		BufferedReader oldFile = new BufferedReader(new FileReader(fileName));  //bufferedreader of the file that will be backed up
		String backup = fileName + "-backup.txt";  //adds -backup.txt to the old file
		String inBuffer = oldFile.readLine();  //Reads next line
		while(inBuffer != null){  //backs up file by writing it to other file
			PrintWriter outFile = new PrintWriter(new FileWriter(backup));
			inBuffer = oldFile.readLine();  //Reads next line
			outFile.println(inBuffer);
		}
		oldFile.close();
		
	}
	
	public static String menu(String outputFileName, BufferedReader stdin, boolean found, boolean ok) throws IOException {
		String choice = "";
		if(checkIfExist(outputFileName) == false)
		{  //means file does not exist
				choice = "-1";
		} 
		else if (checkIfExist(outputFileName))
		{
				System.out.println("File Name Exists! Please Select an option!");
				System.out.println("1. Enter a New File Name");
				System.out.println("2. Backup");
				System.out.println("3. Overwrite");
				choice = stdin.readLine();
				
				if (choice.length() == 0) 
				{
					choice = "quit";
				}
				else if (choice.equals("1") || choice.equals("2") || choice.equals("3"))
				{
					choice = choice;
				}
				else 
				{
					System.out.println("Choice Invalid! Exiting!");
					choice = "quit";
				
				}
			
		}
		return choice;
	}

} //end of class Program2

//BEGIN class Word
class Word {
    private String word;
    private int quant;
    public Word(String word) {
        this.word = word;
        quant = 1;
    }
    public void addOne() {
        quant++;
    }
    public int getCount() {
        return quant;
    }
    public boolean isWord(String word) {
        return this.word.equals(word);
    }
    public String getWord() {
        return word;
    }
}
//END class Word