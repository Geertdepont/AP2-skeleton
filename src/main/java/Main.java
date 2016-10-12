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
    
    void eoln(Scanner input) throws APException{
    	if(input.hasNext()){
    		throw new APException("there is still something here...");
    	}
    }
    
    
    void program(Scanner input) throws APException{
    	while(input.hasNext()){ //Reads the whole file? 
    		statement(input);
    	}
    }
    
    void statement(Scanner input) throws APException{
    	if(nextCharIsLetter(input)){
//    		assignment(new Scanner(input.nextLine()));
    	}else if(nextCharIs(input,'?')){
//    		printStatement(input);
    	}else if(nextCharIs(input,'/')){
//    		comment(input);
    	}else{
    		throw new APException("something");
    	}
    }
    
    void assignment(Scanner input) throws APException{
    	//identifier(input);
    	if(nextCharIs(input,'=')){
    		nextChar(input);
    	}else{
    		throw new APException("character after identifier should be an equal sign");
    	}
    	//expression(input);
    	eoln(input);
    	
    }
    
    void printStatement(Scanner input) throws APException{
    	if(nextCharIs(input,'?')){
    		nextChar(input);
    	}else{
    		throw new APException("? missing");
    	}
    	//expression(input);
    	eoln(input);
    }
    
    void comment(Scanner input) throws APException{
    	if(nextCharIs(input,'/')){
    		nextChar(input);
    	}else{
    		throw new APException("? missing");
    	}
    	input.nextLine();
    	eoln(input);
    }
    
    
    Identifier identifier(Scanner input) throws APException{
    	StringBuffer identifier = new StringBuffer();
    	
    	if(nextCharIsLetter(input)){
//    		identifier.append(letter(input));
    	}else{
    		throw new APException("identifier has to start with a letter");
    	}
    	while(nextCharIsLetter(input) || nextCharIsDigit(input)){
  //  		identifier.append(letter(input));
    	}
    	return new Identifier(identifier.toString());
    }

    void expression(Scanner input)throws APException{
//    	term(input)
    	while(nextIsAdditiveOperator(input)){
    		additiveOperator(input);
    		//term(input);
    	}
    }
    
    void term(Scanner input)throws APException{
//    	factor(input);
//		 
    	while(nextIsMultiplicativeOperator(input)){
    		multiplicativeOperator(input);
    		//factor(input);
    	}
    }
    
    void factor(Scanner input)throws APException{
	    if(nextCharIsLetter(input)){
	//		identifier(input)
		}
		else if(nextCharIs(input, '(')){
	//		complex factor(input)
		}
		else if(nextCharIs(input,'{')){
	//		set(input)
		}
		else{
			throw new APException("not a factor");
		}
    }
    
    
    void additiveOperator(Scanner input){
    	
    }
    
    void multiplicativeOperator(Scanner input){
    	
    }
    
    boolean nextIsAdditiveOperator(Scanner input){
    	return nextCharIs(input, '+') || nextCharIs(input, '|') || nextCharIs(input, '-'); 
    }
    
    boolean nextIsMultiplicativeOperator(Scanner input){
    	return nextCharIs(input, '*');
    }
    
    
    
    public static void main(String[] argv) {
        new Main().start();
    }
}
