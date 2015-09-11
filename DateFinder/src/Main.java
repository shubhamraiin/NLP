/*
  Assignment-1
  Name:Shubham Rai
  Netid: scr130130

*/

/*This is the main class of the assignment. We need to provide an input file so that the program 
 can run successfully and print all the dates that matches the pattern provided in DateRecognizer Class. 
*/
public class Main {

	 @SuppressWarnings("static-access")
	public static void main(String[] args) {
	     
	        
	        DateRecognizer dr = new DateRecognizer();
	        String input = null;
	        String H = args[0];
	        input = Read.readFile(H);
	        dr.DateFinder(input, 1);
	        dr.DateFinder(input, 2);
	        dr.DateFinder(input, 3);
	        dr.DateFinder(input, 4);
	        dr.DateFinder(input, 5);
	        dr.DateFinder(input, 6);
	        dr.DateFinder(input, 7);
	        dr.DateFinder(input, 8);
	        dr.DateFinder(input, 9);
	       
	    }

}
