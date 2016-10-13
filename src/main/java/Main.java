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

    void eoln(Scanner input) throws APException{
    	if(input.hasNext()){
    		throw new APException("there is still something here...");
    	}
    }
    
    void character (Scanner input, char c) throws APException{
        if (!nextCharIs(input, c)){
    	throw new APException("");
        }
        nextChar(input);
    }
    
    boolean nextIsAdditiveOperator(Scanner input){
    	return nextCharIs(input, '+') || nextCharIs(input, '|') || nextCharIs(input, '-'); 
    }
    
    boolean nextIsMultiplicativeOperator(Scanner input){
    	return nextCharIs(input, '*');
    }
    
    void program(Scanner input) throws APException{
    	while(input.hasNext()){ //Reads the whole file? 
    		statement(input);
    	}
    }
    
    void statement(Scanner input) throws APException{
    	String statement=input.nextLine();
    	Scanner statementScanner=new Scanner(statement);
    	if(nextCharIsLetter(statementScanner)){
    		assignment(statementScanner);
    	}else if(nextCharIs(statementScanner, '?')){
    		printStatement(statementScanner);
    	}else if(nextCharIs(statementScanner, '/')){
    		comment(statementScanner);
    	}else{
    		throw new APException("Misformed statement. A statement has to begin with a '?', '/' or an identifier\n");
    	}
    }
    
    void assignment(Scanner input) throws APException{
    	Identifier identifier=identifier(input);
    	character(input, '=');
    	expression(input);
    	eoln(input);
    }
    
    void printStatement(Scanner input) throws APException{
    	character(input, '?');
    	expression(input);
    	eoln(input);
    }
    
    void comment(Scanner input) throws APException{
    	character(input, '/');
    	input.nextLine();
    	eoln(input);
    }
    
    Identifier identifier(Scanner input) throws APException{
    	StringBuffer identifier = new StringBuffer();
    	
    	if(nextCharIsLetter(input)){
    		identifier.append(letter(input));
    	}else{
    		throw new APException("identifier has to start with a letter");
    	}
    	while(nextCharIsLetter(input) || nextCharIsDigit(input)){
    		if(nextCharIsLetter(input)){
    			identifier.append(letter(input));
    		}else{
    			identifier.append(number(input));
    		}
    	}
    	return new Identifier(identifier.toString());
    }

    void expression(Scanner input) throws APException{
    	term(input);
    	while(nextIsAdditiveOperator(input)){
    		additiveOperator(input);
    		term(input);
    	}
    }
    
    void term(Scanner input) throws APException{
    	factor(input);
    	while(nextIsMultiplicativeOperator(input)){
    		multiplicativeOperator(input);
    		factor(input);
    	}
    }
    
    void factor(Scanner input) throws APException{
	    if(nextCharIsLetter(input)){
			identifier(input);
		}else if(nextCharIs(input, '(')){
			complexFactor(input);
		}else if(nextCharIs(input, '{')){
			set(input);
		}else{
			throw new APException("Misformed factor. A factor has to begin with an identifier, '(' or '{'\n");
		}
    }
    
    void complexFactor(Scanner input) throws APException{
    	character(input, '(');
    	expression(input);
    	character(input, ')');
    }
    
    void set(Scanner input) throws APException{
    	character(input, '{');
    	rowNaturalNumbers(input);
    	character(input, '}');
    }
    
    void rowNaturalNumbers(Scanner input) throws APException{
    	if(nextCharIsDigit(input)){
    		naturalNumber(input);
    		while(nextCharIs(input, ',')){
    			character(input, ',');
    			naturalNumber(input);
    		}
    	}
    }
    
    char additiveOperator(Scanner input) throws APException{
    	if(!nextCharIs(input, '+') && !nextCharIs(input, '|') && !nextCharIs(input, '-')){
    		throw new APException("");
    	}
    	return nextChar(input);
    }
    
    char multiplicativeOperator(Scanner input) throws APException{
    	if(!nextCharIs(input, '*')){
    		throw new APException("");
    	}
    	return nextChar(input);
    }
    
    int naturalNumber(Scanner input) throws APException{
    	return nextCharIs(input, '0') ? zero(input):positiveNumber(input);
    }
    
    int positiveNumber(Scanner input) throws APException{
    	StringBuffer result=new StringBuffer();
    	result.append(notZero(input));
    	while(nextCharIsDigit(input)){
    		result.append(number(input));
    	}
    	return Integer.parseInt(result.toString());
    }
    
    int number(Scanner input) throws APException{
    	return nextCharIs(input, '0') ? zero(input) : notZero(input);
    }
    
    int zero(Scanner input) throws APException{
    	if(!nextCharIs(input, '0')){
    		throw new APException("");
    	}
    	return Character.getNumericValue(nextChar(input));
    }
    
	int notZero(Scanner input) throws APException{
		if(!input.hasNext("[1-9]")){
			throw new APException("");
		}
		return Character.getNumericValue(nextChar(input));
	}
    
    char letter(Scanner input) throws APException{
    	if(!nextCharIsLetter(input)){
			throw new APException("");
		}
    	return nextChar(input);
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
    
    public static void main(String[] argv) {
        new Main().start();
    }
}
