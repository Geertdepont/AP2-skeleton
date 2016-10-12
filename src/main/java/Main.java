import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	// Method to read 1 character.
	char nextChar (Scanner in) {
		in.useDelimiter("");
		return in.next().charAt(0);
	}
	// Method to check if the next character to be read when
	// calling nextChar() is equal to the provided character.
	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c+""));
	}
	// Method to check if the next character to be read when
	// calling nextChar() is a digit.
	boolean nextCharIsDigit (Scanner in) {
		return in.hasNext("[0-9]");
	}
	// Method to check if the next character to be read when
	// calling nextChar() is a letter.
	boolean nextCharIsLetter (Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

    private void start() {
        // Create a scanner on System.in
        
        // While there is input, read line and parse it.
    	Scanner programScanner=new Scanner(System.in);
    	try{
    		program(programScanner);
    	}catch(APException e){
    		throw new Error(e);
    	}
    }
    
    void program(Scanner input) throws APException{
    	while(input.hasNext()){
    		statement(input);
    	}
    }
    
    void statement(Scanner input) throws APException{
    	if(nextCharIsLetter(input)){
//    		assignment(input);
    	}else if(nextCharIs(input,'?')){
//    		printStatement(input);
    	}else if(nextCharIs(input,'/')){
//    		comment(input);
    	}else{
    		throw new APException("something");
    	}
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
