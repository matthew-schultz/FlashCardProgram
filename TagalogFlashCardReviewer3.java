/**
 *A flash card application to review Tagalog words from a .csv file 
 *	@author		Schultz,	Matthew
 *	@date			July, 29 2015
 */
 
 /*Bugs
 Bug 1: After the user enters numOfFC correct answers, the user is prompted to answer one more question instead of exiting
 the while loop
 */
 
 /* Issues
 Issue 1: The user has no way of distinguishing the difference between 2 cards with the same answer or question
 	-possible solutions- combine into one question; implement as a two part question? User can answer with one long answer or 
	two different answers
 Issue 2: The method of choosing questions that have not been answered relies on 
 */
 
 /*Future Features
 Multipart/answer questions
 GUI
 Multiply choice options
 Interpolation/interpretation/input correction
 */
 
import java.io.*;	//allows	use of File
import java.util.*; //allows me to use	scanners

public class TagalogFlashCardReviewer3{
	static final int MAX_NUMBER_OF_FLASH_CARDS= 1000;

	public static void main(String[] args) {
		try {
			// open file
			String fileName= args[0]; //the filename
			
			int numOfFC= countFlashCards(fileName);
				
			// create an array of strings based on
			FlashCard[] flashCards= new FlashCard[numOfFC];
			
			flashCards= storeFlashCardsInArray(fileName, numOfFC);
			
			String answer= "";
			
			//choose a random number corresponding to the number of items in the list using the '%' operator
			Random generator = new Random();
			//int randomNum= -1;
			int randomNum= 0;
			
			//print program info
			System.out.println("TagologFlashCard program. Type '0' or 'quit' or 'end' to exit.\n");
			
			//obtain flashcard data, and print front data
			randomNum= generator.nextInt(numOfFC);
			System.out.println(flashCards[randomNum].getBack() + "?");
			
			//get user input (answer/quit option- where loop begins
			Scanner userSc= new Scanner(System.in);
			
         answer=getUserInput(answer, userSc);
			
			int correctAnswers= 0;
													
			//test if the user wanted to quit
			while( !answer.equals("0") && !answer.equals("quit") && !answer.equals("exit") /*&& correctAnswers < numOfFC*/){
				if( answer.equals( flashCards[randomNum].getFront() ) ) { //test if the user's answer is correct
					flashCards[randomNum].setAnswered(true);  //if correct, mark the boolean value, so the question is not chosen again
					System.out.println("Your answer was correct~" +'\n'); //if correct, signify that the answer is correct to the user
					correctAnswers++;
				}
				else {
					System.out.println( "The correct answer was \'" + flashCards[randomNum].getFront() + "\'\n");
				}
				
								
            if(correctAnswers < numOfFC){
               //obtain flashcard data
   				randomNum= generator.nextInt(numOfFC);
  
     				while(flashCards[randomNum].getAnswered() == true /*&& correctAnswers < numOfFC*/) { //this while loop changes the flashcard 
   				//if it has already been answered correctly, however it may cause the system to hang if the user answers
   				//every question correctly
   					randomNum= generator.nextInt(numOfFC);
   				}
   				
   				System.out.println(flashCards[randomNum].getBack() + "?");
            
   				//get user input (answer/quit option)
   				answer= getUserInput(answer, userSc);
				}
				else/*if(correctAnswers == numOfFC)*/ {
					System.out.println("You answered all the flashcards correctly! :D");
               answer= "0";
				}
			}
			System.out.println("\nExiting TagalogFlashCardsReviewer");
		}
		catch	(ArrayIndexOutOfBoundsException e) { //catch	if	the user	just enters	spaces
			System.out.println ("Error: Please enter a filename.");
		}
		catch	(FileNotFoundException e) {
			System.out.println("Error:	File not found");
		}
		catch (NoSuchElementException e) { //catch if the file is empty
			System.out.println("Error: File was empty.");
		}
	}
	
	public static int countFlashCards (String fileName) throws FileNotFoundException  {
		int num= 0;
		
		Scanner sc=	new Scanner( new File(fileName) ); //create a scanner to read through the file
		
		sc.nextLine(); //skip past the first line since that input is unnecessary to add to the array
		
		while( sc.hasNext() ) {
			num++;
			sc.nextLine();
		}
		
		return num;
	}
	
	public static FlashCard[] storeFlashCardsInArray(String fileName, int numOfFC) throws FileNotFoundException {
		FlashCard[] flashCards= new FlashCard[numOfFC];
		
		Scanner sc=	new Scanner( new File(fileName) ); //create a scanner to read through the file
		
		sc.nextLine(); //skip past the first line since that input is unnecessary to add to the array
			
		for( int i= 0; sc.hasNext(); i++ ) {	
			String line= sc.nextLine(); //read the next line
			Scanner lineSc =	new Scanner(line).useDelimiter(","); //separate the name and age using the comma
			
			String s1= lineSc.next();
			String s2= lineSc.next();
			
			FlashCard f= new FlashCard(s1, s2);
						
			flashCards[i]= f;
			
			//System.out.println(flashCards[i].toString());
		}//end while loop
		
		return flashCards;
	}
   
   public static String getUserInput(String answer, Scanner userSc){
      answer= userSc.nextLine();
      answer= cleanUserInput(answer);
      return answer;
   }
   
   public static String cleanUserInput(String answer){
      answer= answer.toLowerCase(); //adjusts for typing in uppercase most of the time
      answer= answer.trim(); //adjusts for extra whitespace most of the time
      return answer;
   }
}